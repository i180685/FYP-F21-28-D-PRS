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


left_fn = r"../datasets/leftSide.mp4"
left_yaml = r"../datasets/leftSide.yml"

left_config = {'text_overlay': True,
          'parking_overlay': True,
          'parking_id_overlay': True,
          'parking_detection': True,
          'min_area_motion_contour': 60,
          'park_sec_to_wait': 20}
          
left_cap = cv2.VideoCapture(left_fn)

left_video_info = {'fps':    left_cap.get(cv2.CAP_PROP_FPS),
              'width':  int(left_cap.get(cv2.CAP_PROP_FRAME_WIDTH)),
              'height': int(left_cap.get(cv2.CAP_PROP_FRAME_HEIGHT)),
              'fourcc': left_cap.get(cv2.CAP_PROP_FOURCC),
              'num_of_frames': int(left_cap.get(cv2.CAP_PROP_FRAME_COUNT))}


right_fn = r"../datasets/rightSide.mp4"
right_yaml = r"../datasets/rightSide.yml"
right_config = {'text_overlay': True,
          'parking_overlay': True,
          'parking_id_overlay': True,
          'parking_detection': True,
          'min_area_motion_contour': 60,
          'park_sec_to_wait': 20}


right_cap = cv2.VideoCapture(right_fn)
right_video_info = {'fps':    cap.get(cv2.CAP_PROP_FPS),
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


with open(left_yaml, 'r') as stream:
    left_parking_data = yaml.load(stream, Loader=yaml.FullLoader)

left_parking_contours = []
left_parking_bounding_rects = []
left_parking_mask = []

with open(right_yaml, 'r') as stream:
    right_parking_data = yaml.load(stream, Loader=yaml.FullLoader)

right_parking_contours = []
right_parking_bounding_rects = []
right_parking_mask = []

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

for park in right_parking_data:
    right_points = np.array(park['points'])
    right_rect = cv2.boundingRect(right_points)
    right_points_shifted = right_points.copy()
    right_points_shifted[:,0] = right_points[:,0] - right_rect[0]
    right_points_shifted[:,1] = right_points[:,1] - right_rect[1]
    right_parking_contours.append(right_points)
    right_parking_bounding_rects.append(right_rect)
    right_mask = cv2.drawContours(np.zeros((right_rect[3], right_rect[2]), dtype=np.uint8), [right_points_shifted], contourIdx=-1, color=255, thickness=-1, lineType=cv2.LINE_8)
    right_mask = right_mask==255
    right_parking_mask.append(right_mask)

right_parking_status = [False]*len(right_parking_data)
right_parking_buffer = [None]*len(right_parking_data)

#-----------------------------------------------------------------------------------------------------------------
#       Running the video 
#-----------------------------------------------------------------------------------------------------------------
video_cur_frame = 0
video_cur_pos = 0
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
    right_ret, right_frame = right_cap.read()
    if ret == False and left_ret == False and right_ret == False:
        print("Capture Error")
        break

    frame_blur = cv2.GaussianBlur(frame.copy(), (5,5), 3)
    frame_gray = cv2.cvtColor(frame_blur, cv2.COLOR_BGR2GRAY)
    frame_out = frame.copy()

    left_frame_blur = cv2.GaussianBlur(left_frame.copy(), (5,5), 3)
    left_frame_gray = cv2.cvtColor(left_frame_blur, cv2.COLOR_BGR2GRAY)
    left_frame_out = left_frame.copy()

    right_frame_blur = cv2.GaussianBlur(right_frame.copy(), (5,5), 3)
    right_frame_gray = cv2.cvtColor(right_frame_blur, cv2.COLOR_BGR2GRAY)
    right_frame_out = right_frame.copy()

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

    
    if left_config['parking_detection']:
        for left_ind, left_park in enumerate(left_parking_data):
            left_points = np.array(left_park['points'])
            left_rect = left_parking_bounding_rects[left_ind]
            left_roi_gray = left_frame_gray[left_rect[1]:(left_rect[1]+left_rect[3]), left_rect[0]:(left_rect[0]+left_rect[2])] # crop roi for faster calculation
            left_points[:,0] = left_points[:,0] - left_rect[0] # shift contour to roi
            left_points[:,1] = left_points[:,1] - left_rect[1]
            #print(np.std(roi_gray), np.mean(roi_gray))
            left_status = np.std(left_roi_gray) < 30 and np.mean(left_roi_gray) > 50
            #If detected a change in parking status, save the current time
            if left_status != left_parking_status[left_ind] and left_parking_buffer[left_ind]==None:
                left_parking_buffer[left_ind] = video_cur_pos
            #If status is still different than the one saved and counter is open
            elif left_status != left_parking_status[left_ind] and left_parking_buffer[left_ind]!=None:
                if video_cur_pos - left_parking_buffer[left_ind] > left_config['park_sec_to_wait']:
                    left_parking_status[left_ind] = left_status
                    left_parking_buffer[left_ind] = None
            #If status is still same and counter is open
            elif left_status == left_parking_status[left_ind] and left_parking_buffer[left_ind]!=None:
                #if video_cur_pos - parking_buffer[ind] > config['park_sec_to_wait']:
                left_parking_buffer[left_ind] = None

    if right_config['parking_detection']:
        for ind, park in enumerate(right_parking_data):
            right_points = np.array(park['points'])
            right_rect = right_parking_bounding_rects[ind]
            right_roi_gray = right_frame_gray[right_rect[1]:(right_rect[1]+right_rect[3]), right_rect[0]:(right_rect[0]+right_rect[2])] # crop roi for faster calculation
            right_points[:,0] = right_points[:,0] - right_rect[0] # shift contour to roi
            right_points[:,1] = right_points[:,1] - right_rect[1]
            #print(np.std(roi_gray), np.mean(roi_gray))
            right_status = np.std(right_roi_gray) < 30 and np.mean(right_roi_gray) > 50
            #If detected a change in parking status, save the current time
            if right_status != right_parking_status[ind] and right_parking_buffer[ind]==None:
                right_parking_buffer[ind] = video_cur_pos
            #If status is still different than the one saved and counter is open
            elif right_status != right_parking_status[ind] and right_parking_buffer[ind]!=None:
                if video_cur_pos - right_parking_buffer[ind] > right_config['park_sec_to_wait']:
                    right_parking_status[ind] = right_status
                    right_parking_buffer[ind] = None
            #If status is still same and counter is open
            elif right_status == right_parking_status[ind] and right_parking_buffer[ind]!=None:
                #if video_cur_pos - parking_buffer[ind] > config['park_sec_to_wait']:
                right_parking_buffer[ind] = None


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
                                    doc_ref = datab.collection(u'users').document(docIds[docId])
                                    doc_ref.update({ u'status': 1 })
            elif color == (0,255,0):
                for user in users:
                    if user['status'] == 1 and video_cur_frame %100 == 0:
                        for docId in docIds:
                                if str(docId) == str(park['id']):
                                    print("car in " + docId)
                                    doc_ref = datab.collection(u'users').document(docIds[docId])
                                    doc_ref.update({ u'status': 0 })

    if left_config['parking_overlay']:
        for ind, park in enumerate(left_parking_data):
            left_points = np.array(park['points'])
            if left_parking_status[ind]:
                color = (0,255,0)
                left_spot = left_spot+1
            else:
                color = (0,0,255)
                left_occupied = left_occupied+1
            cv2.drawContours(left_frame_out, [left_points], contourIdx=-1, color=color, thickness=2, lineType=cv2.LINE_8)
            left_moments = cv2.moments(left_points)
            left_centroid = (int(left_moments['m10']/left_moments['m00'])-3, int(left_moments['m01']/left_moments['m00'])+3)
            cv2.putText(left_frame_out, str(park['id']), (left_centroid[0]-50, left_centroid[1]-90), cv2.FONT_HERSHEY_SIMPLEX, 6, color, 20, cv2.LINE_AA)
            if color == (0,0,255):
                for user in users:
                    if str(park['id']) == user['spot']:
                        cv2.putText(left_frame_out, user['name'], (left_centroid[0]-150, left_centroid[1]-40), cv2.FONT_HERSHEY_SIMPLEX, 2, (0,0,255), 5, cv2.LINE_AA)
                        cv2.putText(left_frame_out, user['rollNo'], (left_centroid[0]-150, left_centroid[1]+40), cv2.FONT_HERSHEY_SIMPLEX, 2, (0,0,255), 5, cv2.LINE_AA)
                        if video_cur_frame %100 == 0 and user['status'] == 0:
                            for docId in docIds:
                                if str(docId) == str(park['id']):
                                    doc_ref = datab.collection(u'users').document(docIds[docId])
                                    doc_ref.update({ u'status': 1 })
            elif color == (0,255,0):
                for user in users:
                    if user['status'] == 1 and video_cur_frame %100 == 0:
                        for docId in docIds:
                                if str(docId) == str(park['id']):
                                    print("car in " + docId)
                                    doc_ref = datab.collection(u'users').document(docIds[docId])
                                    doc_ref.update({ u'status': 0 })


    if right_config['parking_overlay']:
        for ind, park in enumerate(right_parking_data):
            right_points = np.array(park['points'])
            if right_parking_status[ind]:
                color = (0,255,0)
                right_spot = spot+1
            else:
                color = (0,0,255)
                right_occupied = occupied+1
            cv2.drawContours(right_frame_out, [right_points], contourIdx=-1, color=color, thickness=2, lineType=cv2.LINE_8)
            right_moments = cv2.moments(right_points)
            right_centroid = (int(right_moments['m10']/right_moments['m00'])-3, int(right_moments['m01']/right_moments['m00'])+3)
            cv2.putText(right_frame_out, str(park['id']), (right_centroid[0]-50, right_centroid[1]-90), cv2.FONT_HERSHEY_SIMPLEX, 6, color, 20, cv2.LINE_AA)
            if color == (0,0,255):
                for user in users:
                    if str(park['id']) == user['spot']:
                        cv2.putText(right_frame_out, user['name'], (right_centroid[0]-150, right_centroid[1]-40), cv2.FONT_HERSHEY_SIMPLEX, 2, (0,0,255), 5, cv2.LINE_AA)
                        cv2.putText(right_frame_out, user['rollNo'], (right_centroid[0]-150, right_centroid[1]+40), cv2.FONT_HERSHEY_SIMPLEX, 2, (0,0,255), 5, cv2.LINE_AA)
                        if video_cur_frame %100 == 0 and user['status'] == 0:
                            for docId in docIds:
                                if str(docId) == str(park['id']):
                                    doc_ref = datab.collection(u'users').document(docIds[docId])
                                    doc_ref.update({ u'status': 1 })
            elif color == (0,255,0):
                for user in users:
                    if user['status'] == 1 and video_cur_frame %100 == 0:
                        for docId in docIds:
                                if str(docId) == str(park['id']):
                                    print("car in " + docId)
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

    if left_config['text_overlay']:
        str_on_frame = "Frames: %d/%d" % (video_cur_frame, left_video_info['num_of_frames'])
        cv2.putText(left_frame_out, str_on_frame, (5,100), cv2.FONT_HERSHEY_SIMPLEX, 3, (0,128,255), 5, cv2.LINE_AA)
        str_on_frame = "Spot: %d Occupied: %d" % (spot, occupied)
        cv2.putText(left_frame_out, str_on_frame, (5,200), cv2.FONT_HERSHEY_SIMPLEX,3, (0,128,255), 5, cv2.LINE_AA)

    if right_config['text_overlay']:
        str_on_frame = "Frames: %d/%d" % (video_cur_frame, right_video_info['num_of_frames'])
        cv2.putText(right_frame_out, str_on_frame, (5,100), cv2.FONT_HERSHEY_SIMPLEX, 3, (0,128,255), 5, cv2.LINE_AA)
        str_on_frame = "Spot: %d Occupied: %d" % (spot, occupied)
        cv2.putText(right_frame_out, str_on_frame, (5,200), cv2.FONT_HERSHEY_SIMPLEX,3, (0,128,255), 5, cv2.LINE_AA)

    #-----------------------------------------------------------------------------------------------------------------
    #       Resizing the canvas to fit on the screen
    #----------------------------------------------------------------------------------------------------------------- 
    #cv2.imshow('Smart Parking Detection', frame_out)
    frame75 = rescale_frame(frame_out, percent=25)
    cv2.imshow('Spot Detection System', frame75)

    frameLeft = rescale_frame(left_frame_out, percent=15)
    cv2.imshow('Spot Detection System Left', frameLeft)

    frameRight = rescale_frame(right_frame_out, percent=15)
    cv2.imshow('Spot Detection System Right', frameRight)

    #-----------------------------------------------------------------------------------------------------------------
    #       Quit from the program
    #----------------------------------------------------------------------------------------------------------------- 
    k = cv2.waitKey(1)
    if k == ord('q'):
        break
    elif k == ord('c'):
        cv2.imwrite('frame%d.jpg' % video_cur_frame, frame_out)
        cv2.imwrite('frame%d.jpg' % video_cur_frame, left_frame_out)
        cv2.imwrite('frame%d.jpg' % video_cur_frame, right_frame_out)

cap.release()
cv2.destroyAllWindows()
