import yaml
import numpy as np
import cv2

#-----------------------------------------------------------------------------------------------------------------
#       Declaring Files
#-----------------------------------------------------------------------------------------------------------------
fn = r"../datasets/speed.mp4"
fn_yaml = r"../datasets/final.yml"
fn_out = r"../datasets/output.avi"
config = {'text_overlay': True,
          'parking_overlay': True,
          'parking_id_overlay': True,
          'parking_detection': True,
          'min_area_motion_contour': 60,
          'park_sec_to_wait': 20}

cap = cv2.VideoCapture(fn)

video_info = {'fps':    cap.get(cv2.CAP_PROP_FPS),
              'width':  int(cap.get(cv2.CAP_PROP_FRAME_WIDTH)),
              'height': int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT)),
              'fourcc': cap.get(cv2.CAP_PROP_FOURCC),
              'num_of_frames': int(cap.get(cv2.CAP_PROP_FRAME_COUNT))}

#-----------------------------------------------------------------------------------------------------------------
#       Rescalling Window as per to required need
#-----------------------------------------------------------------------------------------------------------------
def rescale_frame(frame, percent):
    width = int(frame.shape[1] * percent/ 100)
    height = int(frame.shape[0] * percent/ 100)
    dim = (width, height)
    return cv2.resize(frame, dim, interpolation =cv2.INTER_AREA)


#-----------------------------------------------------------------------------------------------------------------
#       Configuration with firebase
#-----------------------------------------------------------------------------------------------------------------
import firebase_admin
from firebase_admin import credentials, firestore
cd = credentials.Certificate("firebase.json")
firebase_admin.initialize_app(cd)
datab = firestore.client()
usersref = datab.collection(u'users')
docs = usersref.stream()
users = []
docIds = {}
for doc in docs:
    users.append(doc.to_dict())
    docIds[doc.to_dict()['spot']] = doc.id


#-----------------------------------------------------------------------------------------------------------------
#       Opening the spots poinys/coordinates file
#-----------------------------------------------------------------------------------------------------------------
with open(fn_yaml, 'r') as stream:
    parking_data = yaml.load(stream, Loader=yaml.FullLoader)

parking_contours = []
parking_bounding_rects = []
parking_mask = []


#-----------------------------------------------------------------------------------------------------------------
#       Converting those points into axis on the canvas
#-----------------------------------------------------------------------------------------------------------------
for park in parking_data:
    points = np.array(park['points'])
    rect = cv2.boundingRect(points)
    points_shifted = points.copy()
    points_shifted[:,0] = points[:,0] - rect[0]
    points_shifted[:,1] = points[:,1] - rect[1]
    parking_contours.append(points)
    parking_bounding_rects.append(rect)
    mask = cv2.drawContours(np.zeros((rect[3], rect[2]), dtype=np.uint8), [points_shifted], contourIdx=-1, color=255, thickness=-1, lineType=cv2.LINE_8)
    mask = mask==255
    parking_mask.append(mask)

parking_status = [False]*len(parking_data)
parking_buffer = [None]*len(parking_data)


#-----------------------------------------------------------------------------------------------------------------
#       Running the video 
#-----------------------------------------------------------------------------------------------------------------
video_cur_frame = 0
video_cur_pos = 0
while(cap.isOpened()):
    spot = 0
    occupied = 0
    video_cur_pos +=1 
    video_cur_frame +=1
    ret, frame = cap.read()
    if ret == False:
        print("Capture Error")
        break

    frame_blur = cv2.GaussianBlur(frame.copy(), (5,5), 3)
    frame_gray = cv2.cvtColor(frame_blur, cv2.COLOR_BGR2GRAY)
    frame_out = frame.copy()


    #-----------------------------------------------------------------------------------------------------------------
    #       Parking detection algorithm
    #-----------------------------------------------------------------------------------------------------------------  
    if config['parking_detection']:
        for ind, park in enumerate(parking_data):
            points = np.array(park['points'])
            rect = parking_bounding_rects[ind]
            roi_gray = frame_gray[rect[1]:(rect[1]+rect[3]), rect[0]:(rect[0]+rect[2])] # crop roi for faster calculation
            points[:,0] = points[:,0] - rect[0] # shift contour to roi
            points[:,1] = points[:,1] - rect[1]
            #print(np.std(roi_gray), np.mean(roi_gray))
            status = np.std(roi_gray) < 30 and np.mean(roi_gray) > 50
            #If detected a change in parking status, save the current time
            if status != parking_status[ind] and parking_buffer[ind]==None:
                parking_buffer[ind] = video_cur_pos
            #If status is still different than the one saved and counter is open
            elif status != parking_status[ind] and parking_buffer[ind]!=None:
                if video_cur_pos - parking_buffer[ind] > config['park_sec_to_wait']:
                    parking_status[ind] = status
                    parking_buffer[ind] = None
            #If status is still same and counter is open
            elif status == parking_status[ind] and parking_buffer[ind]!=None:
                #if video_cur_pos - parking_buffer[ind] > config['park_sec_to_wait']:
                parking_buffer[ind] = None


    #-----------------------------------------------------------------------------------------------------------------
    #       Display on the parking spot
    #----------------------------------------------------------------------------------------------------------------- 
    if config['parking_overlay']:
        for ind, park in enumerate(parking_data):
            points = np.array(park['points'])
            if parking_status[ind]:
                color = (0,255,0)
                spot = spot+1
            else:
                color = (0,0,255)
                occupied = occupied+1
            #qcv2.drawContours(frame_out, [points], contourIdx=-1, color=color, thickness=2, lineType=cv2.LINE_8)
            moments = cv2.moments(points)
            centroid = (int(moments['m10']/moments['m00'])-3, int(moments['m01']/moments['m00'])+3)
            cv2.putText(frame_out, str(park['id']), (centroid[0]-50, centroid[1]-90), cv2.FONT_HERSHEY_SIMPLEX, 6, color, 20, cv2.LINE_AA)
            if color == (0,0,255):
                for user in users:
                    if str(park['id']) == user['spot']:
                        cv2.putText(frame_out, user['name'], (centroid[0]-150, centroid[1]-40), cv2.FONT_HERSHEY_SIMPLEX, 2, (0,0,255), 5, cv2.LINE_AA)
                        cv2.putText(frame_out, user['rollNo'], (centroid[0]-150, centroid[1]+40), cv2.FONT_HERSHEY_SIMPLEX, 2, (0,0,255), 5, cv2.LINE_AA)
                        if video_cur_frame %100 == 0 and user['status'] == 0:
                            for docId in docIds:
                                if str(docId) == str(park['id']):
                                    print(docId)
                                    doc_ref = datab.collection(u'users').document(docIds[docId])
                                    doc_ref.update({ u'status': 1 })
            elif color == (0,255,0):
                for user in users:
                    if user['status'] == 1 and video_cur_frame %100 == 0:
                        for docId in docIds:
                                if str(docId) == str(park['id']):
                                    print(docId)
                                    doc_ref = datab.collection(u'users').document(docIds[docId])
                                    doc_ref.update({ u'status': 0 })


    #-----------------------------------------------------------------------------------------------------------------
    #      Display on canvas for frame rate and spots booked 
    #-----------------------------------------------------------------------------------------------------------------                             
    if config['text_overlay']:
        str_on_frame = "Frames: %d/%d" % (video_cur_frame, video_info['num_of_frames'])
        cv2.putText(frame_out, str_on_frame, (5,100), cv2.FONT_HERSHEY_SIMPLEX, 3, (0,128,255), 5, cv2.LINE_AA)
        str_on_frame = "Spot: %d Occupied: %d" % (spot, occupied)
        cv2.putText(frame_out, str_on_frame, (5,200), cv2.FONT_HERSHEY_SIMPLEX,3, (0,128,255), 5, cv2.LINE_AA)

    #-----------------------------------------------------------------------------------------------------------------
    #       Resizing the canvas to fit on the screen
    #----------------------------------------------------------------------------------------------------------------- 
    #cv2.imshow('Smart Parking Detection', frame_out)
    frame75 = rescale_frame(frame_out, percent=25)
    cv2.imshow('frame75', frame75)

    #-----------------------------------------------------------------------------------------------------------------
    #       Quit from the program
    #----------------------------------------------------------------------------------------------------------------- 
    k = cv2.waitKey(1)
    if k == ord('q'):
        break
    elif k == ord('c'):
        cv2.imwrite('frame%d.jpg' % video_cur_frame, frame_out)

cap.release()
cv2.destroyAllWindows()
