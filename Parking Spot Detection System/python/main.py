import yaml
import numpy as np
import cv2

#-----------------------------------------------------------------------------------------------------------------
#       Declaring Files
#-----------------------------------------------------------------------------------------------------------------
fn = r"../datasets/newRight.mp4"
fn_yaml = r"../datasets/rightSide.yml"
fn_out = r"../datasets/output.avi"
config = {'text_overlay': True,
          'parking_overlay': True,
          'parking_id_overlay': True,
          'parking_detection': True,
          'min_area_motion_contour': 60,
          'park_sec_to_wait': 80}

cap = cv2.VideoCapture(fn)

video_info = {'fps':    cap.get(cv2.CAP_PROP_FPS),
              'width':  int(cap.get(cv2.CAP_PROP_FRAME_WIDTH)),
              'height': int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT)),
              'fourcc': cap.get(cv2.CAP_PROP_FOURCC),
              'num_of_frames': int(cap.get(cv2.CAP_PROP_FRAME_COUNT))}

left_fn = r"../datasets/newLeft.mp4"
left_yaml = r"../datasets/leftSide.yml"

left_config = {'text_overlay': True,
          'parking_overlay': True,
          'parking_id_overlay': True,
          'parking_detection': True,
          'min_area_motion_contour': 60,
          'park_sec_to_wait': 80}
          
left_cap = cv2.VideoCapture(left_fn)

left_video_info = {'fps':    left_cap.get(cv2.CAP_PROP_FPS),
              'width':  int(left_cap.get(cv2.CAP_PROP_FRAME_WIDTH)),
              'height': int(left_cap.get(cv2.CAP_PROP_FRAME_HEIGHT)),
              'fourcc': left_cap.get(cv2.CAP_PROP_FOURCC),
              'num_of_frames': int(left_cap.get(cv2.CAP_PROP_FRAME_COUNT))}

left_cap.set(cv2.CAP_PROP_POS_FRAMES, 950)

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


with open(left_yaml, 'r') as stream:
    left_parking_data = yaml.load(stream, Loader=yaml.FullLoader)

left_parking_contours = []
left_parking_bounding_rects = []
left_parking_mask = []

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


for left_park in left_parking_data:
    left_points = np.array(left_park['points'])
    left_rect = cv2.boundingRect(left_points)
    left_points_shifted = left_points.copy()
    left_points_shifted[:,0] = left_points[:,0] - left_rect[0]
    left_points_shifted[:,1] = left_points[:,1] - left_rect[1]
    left_parking_contours.append(left_points)
    left_parking_bounding_rects.append(left_rect)
    left_mask = cv2.drawContours(np.zeros((left_rect[3], left_rect[2]), dtype=np.uint8), [left_points_shifted], contourIdx=-1, color=255, thickness=-1, lineType=cv2.LINE_8)
    left_mask = left_mask==255
    left_parking_mask.append(left_mask)

left_parking_status = [False]*len(left_parking_data)
left_parking_buffer = [None]*len(left_parking_data)

#-----------------------------------------------------------------------------------------------------------------
#       Running the video 
#-----------------------------------------------------------------------------------------------------------------
video_cur_frame = 0
video_cur_pos = 0
errorcolor = []
while(cap.isOpened()):
    spot = 0
    left_spot=0
    right_spot = 0

    occupied = 0
    left_occupied = 0
    right_occupied = 0

    video_cur_pos +=1 
    video_cur_frame +=1


    ret, frame = cap.read()
    left_ret, left_frame = left_cap.read()
    if ret == False or left_ret==False:
        print("Capture Error")
        break

   
    frame_blur = cv2.GaussianBlur(frame.copy(), (5,5), 3)
    frame_gray = cv2.cvtColor(frame_blur, cv2.COLOR_BGR2GRAY)
    frame_out = frame.copy()

    left_frame_blur = cv2.GaussianBlur(left_frame.copy(), (5,5), 3)
    left_frame_gray = cv2.cvtColor(left_frame_blur, cv2.COLOR_BGR2GRAY)
    left_frame_out = left_frame.copy()

    #-----------------------------------------------------------------------------------------------------------------
    #       Parking detection algorithm
    #-----------------------------------------------------------------------------------------------------------------  
    if config['parking_detection']:
        for ind, park in enumerate(parking_data):
            points = np.array(park['points'])
            rect = parking_bounding_rects[ind]
            roi_gray = frame_gray[rect[1]:(rect[1]+rect[3]), rect[0]:(rect[0]+rect[2])] 
            points[:,0] = points[:,0] - rect[0] 
            points[:,1] = points[:,1] - rect[1]
            status = np.std(roi_gray) < 30 and np.mean(roi_gray) > 50    
            if status != parking_status[ind] and parking_buffer[ind]==None:
                parking_buffer[ind] = video_cur_pos
            elif status != parking_status[ind] and parking_buffer[ind]!=None:
                if video_cur_pos - parking_buffer[ind] > config['park_sec_to_wait']:
                    parking_status[ind] = status
                    parking_buffer[ind] = None
            elif status == parking_status[ind] and parking_buffer[ind]!=None:
                parking_buffer[ind] = None

    
    if left_config['parking_detection']:
        for left_ind, left_park in enumerate(left_parking_data):
            left_points = np.array(left_park['points'])
            left_rect = left_parking_bounding_rects[left_ind]
            left_roi_gray = left_frame_gray[left_rect[1]:(left_rect[1]+left_rect[3]), left_rect[0]:(left_rect[0]+left_rect[2])]
            left_points[:,0] = left_points[:,0] - left_rect[0] 
            left_points[:,1] = left_points[:,1] - left_rect[1]
            left_status = np.std(left_roi_gray) < 30 and np.mean(left_roi_gray) > 50
            if left_status != left_parking_status[left_ind] and left_parking_buffer[left_ind]==None:
                left_parking_buffer[left_ind] = video_cur_pos
            elif left_status != left_parking_status[left_ind] and left_parking_buffer[left_ind]!=None:
                if video_cur_pos - left_parking_buffer[left_ind] > left_config['park_sec_to_wait']:
                    left_parking_status[left_ind] = left_status
                    left_parking_buffer[left_ind] = None
            elif left_status == left_parking_status[left_ind] and left_parking_buffer[left_ind]!=None:
                left_parking_buffer[left_ind] = None

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
            moments = cv2.moments(points)
            centroid = (int(moments['m10']/moments['m00'])-3, int(moments['m01']/moments['m00'])+3)
            if park['id'] == "ERROR":
                cv2.putText(frame_out, str(park['id']), (centroid[0]-50, centroid[1]-30), cv2.FONT_HERSHEY_SIMPLEX, 1, (128,0,128), 2, cv2.LINE_AA)
                cv2.drawContours(frame_out, [points], contourIdx=-1, color=(128,0,128), thickness=2, lineType=cv2.LINE_8)
            else:
                cv2.putText(frame_out, str(park['id']), (centroid[0]-10, centroid[1]-10), cv2.FONT_HERSHEY_SIMPLEX, 1, color, 1, cv2.LINE_AA)
                cv2.drawContours(frame_out, [points], contourIdx=-1, color=color, thickness=1, lineType=cv2.LINE_8)
            if video_cur_frame %1000 == 0 :
                usersref = datab.collection(u'users')
                docs = usersref.stream()
                users = []
                docIds = {}
                ch = 0
                for doc in docs:
                    users.append(doc.to_dict())
                    docIds[doc.to_dict()['spot']] = doc.id
                if color == (0,0,255):      #red
                    for user in users:
                        if str(park['id']) == user['spot']:
                            ch = 1
                            cv2.putText(frame_out, user['name'], (centroid[0]-10, centroid[1]-4), cv2.FONT_HERSHEY_SIMPLEX, 1, (0,0,255), 1, cv2.LINE_AA)
                            cv2.putText(frame_out, user['rollNo'], (centroid[0]-10, centroid[1]+4), cv2.FONT_HERSHEY_SIMPLEX, 1, (0,0,255), 1, cv2.LINE_AA)
                            if (user['status'] == 0 or user['status'] == 2) :
                                for docId in docIds:
                                    if str(docId) == str(park['id']):
                                        doc_ref = datab.collection(u'users').document(docIds[docId])
                                        doc_ref.update({ 'status': 1 })
                    if ch == 0:
                        print("error: " + str(park['id']))
                    ch = 0

                elif color == (0,255,0):        #green
                    for user in users:   
                        if  video_cur_frame %1000 == 0 :    
                            if user['status'] == 1:
                                for docId in docIds:
                                    if str(docId) == str(park['id']):
                                        doc_ref = datab.collection(u'users').document(docIds[docId])
                                        doc_ref.update({ u'status': 0 })
                                        doc_ref.update({u'spot':"0"})

    if left_config['parking_overlay']:
        for ind, park in enumerate(left_parking_data):
            left_points = np.array(park['points'])
            if left_parking_status[ind]:
                color = (0,255,0)
                left_spot = left_spot+1
            else:
                color = (0,0,255)
                left_occupied = left_occupied+1
            left_moments = cv2.moments(left_points)
            left_centroid = (int(left_moments['m10']/left_moments['m00'])-3, int(left_moments['m01']/left_moments['m00'])+3)
            if park['id'] in errorcolor:
                cv2.drawContours(left_frame_out, [left_points], contourIdx=-1, color=(128,0,128), thickness=3, lineType=cv2.LINE_8)
                cv2.putText(left_frame_out, str(park['id']), (left_centroid[0]-10, left_centroid[1]-10), cv2.FONT_HERSHEY_SIMPLEX, 1, (128,0,128), 2, cv2.LINE_AA)
            else:
                cv2.drawContours(left_frame_out, [left_points], contourIdx=-1, color=color, thickness=1, lineType=cv2.LINE_8)
                cv2.putText(left_frame_out, str(park['id']), (left_centroid[0]-10, left_centroid[1]-10), cv2.FONT_HERSHEY_SIMPLEX, 1, color, 1, cv2.LINE_AA)
            if video_cur_frame %1000 == 0 :
                usersref = datab.collection(u'users')
                docs = usersref.stream()
                users = []
                docIds = {}
                ch = 0
                for doc in docs:
                    users.append(doc.to_dict())
                    docIds[doc.to_dict()['spot']] = doc.id
                if color == (0,0,255):      #red
                    for user in users:
                        cv2.putText(frame_out, "Wrong Vehicle Parked", (centroid[0]-150, centroid[1]-40), cv2.FONT_HERSHEY_SIMPLEX, 2, (0,0,255), 5, cv2.LINE_AA)
                        cv2.putText(frame_out, user['name'], (centroid[0]-250, centroid[1]-80), cv2.FONT_HERSHEY_SIMPLEX, 2, (0,0,255), 5, cv2.LINE_AA)
                        if str(park['id']) == user['spot']:
                            ch = 1
                            cv2.putText(left_frame_out, user['name'], (left_centroid[0]-10, left_centroid[1]-4), cv2.FONT_HERSHEY_SIMPLEX, 1, (0,0,255), 1, cv2.LINE_AA)
                            cv2.putText(left_frame_out, user['rollNo'], (left_centroid[0]-10, left_centroid[1]+4), cv2.FONT_HERSHEY_SIMPLEX, 1, (0,0,255), 1, cv2.LINE_AA)
                            if (user['status'] == 0 or user['status'] == 2) :
                                for docId in docIds:
                                    if str(docId) == str(park['id']):
                                        doc_ref = datab.collection(u'users').document(docIds[docId])
                                        doc_ref.update({ 'status': 1 })
                    if ch == 0:
                        print("error: " + str(park['id']))
                        errorcolor.append(park['id'])
                        erroruser = []
                        for user in users:
                            if user['spot'] != "0" and user['status'] == 0:
                                erroruser = user
                        spotsref = datab.collection(u'spots')
                        spotdocs = spotsref.stream()
                        spots = []
                        spotdocIds = {}
                        ch = 0
                        for spotdoc in spotdocs:
                            spots.append(spotdoc.to_dict())
                            spotdocIds[spotdoc.to_dict()['spot']] = spotdoc.id
                        for spotdoc in spotdocIds:
                            if str(spotdoc) == str(park['id']):
                                doc_ref = datab.collection(u'spots').document(spotdocIds[spotdoc])
                                doc_ref.update({ 'errorRollNo': erroruser['rollNo'] })
                                doc_ref.update({ 'errorName': erroruser['name'] })
                                doc_ref.update({ 'errorMobile': erroruser['mobileNo'] })
                                doc_ref.update({ 'errorEmail': erroruser['email'] })
                                doc_ref.update({ 'errorCar': erroruser['carName'] })
                                doc_ref.update({ 'errorNoplate': erroruser['carNoplate'] })
                                doc_ref.update({ 'errorUser': "1" })
                    ch = 0

                elif color == (0,255,0):        #green
                    for user in users:   
                        if user['status'] == 1 and user['status'] != 2:
                            for docId in docIds:
                                if str(docId) == str(park['id']) and str(docId) == user['spot']:
                                    doc_ref = datab.collection(u'users').document(docIds[docId])
                                    doc_ref.update({ u'status': 0 })
                    spotsref = datab.collection(u'spots')
                    spotdocs = spotsref.stream()
                    spots = []
                    spotdocIds = {}
                    ch = 0
                    for spotdoc in spotdocs:
                        spots.append(spotdoc.to_dict())
                        spotdocIds[spotdoc.to_dict()['spot']] = spotdoc.id
                    for spotdoc in spotdocIds:
                            if str(spotdoc) == str(park['id']):
                                doc_ref = datab.collection(u'spots').document(spotdocIds[spotdoc])
                                doc_ref.update({ 'errorRollNo': "" })
                                doc_ref.update({ 'errorName': "" })
                                doc_ref.update({ 'errorMobile': "" })
                                doc_ref.update({ 'errorEmail': "" })
                                doc_ref.update({ 'errorCar': "" })
                                doc_ref.update({ 'errorNoplate': "" })
                                doc_ref.update({ 'errorUser': "" })
                                    
    #-----------------------------------------------------------------------------------------------------------------
    #      Display on canvas for frame rate and spots booked 
    #-----------------------------------------------------------------------------------------------------------------                             
    if config['text_overlay']:
        str_on_frame = "Frames: %d/%d" % (video_cur_frame, video_info['num_of_frames'])
        cv2.putText(frame_out, str_on_frame, (5,25), cv2.FONT_HERSHEY_SIMPLEX, 1, (0,128,255), 1, cv2.LINE_AA)
        str_on_frame = "Spot: %d Occupied: %d" % (spot, occupied)
        cv2.putText(frame_out, str_on_frame, (5,50), cv2.FONT_HERSHEY_SIMPLEX, 1, (0,128,255), 1, cv2.LINE_AA)

    if left_config['text_overlay']:
        str_on_frame = "Frames: %d/%d" % (video_cur_frame, left_video_info['num_of_frames'])
        cv2.putText(left_frame_out, str_on_frame, (5,25), cv2.FONT_HERSHEY_SIMPLEX, 1, (0,128,255), 1, cv2.LINE_AA)
        str_on_frame = "Spot: %d Occupied: %d" % (spot, occupied)
        cv2.putText(left_frame_out, str_on_frame, (5,50), cv2.FONT_HERSHEY_SIMPLEX,1, (0,128,255), 1, cv2.LINE_AA)

    #-----------------------------------------------------------------------------------------------------------------
    #       Resizing the canvas to fit on the screen
    #----------------------------------------------------------------------------------------------------------------- 
    frame75 = rescale_frame(frame_out, percent=100)
    cv2.imshow('Spot Detection System Right', frame75)

    frameLeft = rescale_frame(left_frame_out, percent=100)
    cv2.imshow('Spot Detection System Left', frameLeft)

    #-----------------------------------------------------------------------------------------------------------------
    #       Quit from the program
    #----------------------------------------------------------------------------------------------------------------- 
    k = cv2.waitKey(1)
    if k == 32:
        cv2.waitKey()
    if k == ord('q'):
        break
    elif k == ord('c'):
        cv2.imwrite('frame%d.jpg' % video_cur_frame, frame_out)

cap.release()
cv2.destroyAllWindows()
