import cv2
import pytesseract

set = False

pytesseract.pytesseract.tesseract_cmd="C:/Program Files/Tesseract-OCR/tesseract.exe"

if set is False:
    plateCascade = cv2.CascadeClassifier(r'C:\Users\Dell\Desktop\FYP\haarcascade_russian_plate_number.xml')
    minArea = 500
    cap = cv2.VideoCapture(0)
    count = 0
    while 1:
        success, img = cap.read()
        imgGray = cv2.cvtColor(img, cv2.COLOR_BGR2BGRA)
        numberPlates = plateCascade.detectMultiScale(imgGray, 1.1, 4)

        for (x, y, w, h) in numberPlates:
            area = w * h
            if area > minArea:
                cv2.rectangle(img, (x, y), (x + w, y + h), (255, 0, 0), 2)  # making rectangle around plate
                cv2.putText(img, "NumberPlate", (x, y - 5), cv2.FONT_HERSHEY_COMPLEX, 1, (0, 0, 255),
                            2)  # putting text over detected plate
                imgRoi = img[y:y + h, x:x + w]  # displaying region of interest
                cv2.imshow("Region Of Interest", imgRoi)

        cv2.imshow("Window", img)

        if cv2.waitKey(1) & 0xFF == ord('s'):  # press s to save snapshot
            cv2.imwrite(r"C:\Users\Dell\Desktop\FYP\Images" + str(count) + ".png",
                        imgRoi)  # captured images will be stored here
            cv2.rectangle(img, (0, 200), (640, 300), (0, 255, 0), cv2.FILLED)
            cv2.putText(img, "Scan Saved", (15, 265), cv2.FONT_HERSHEY_COMPLEX, 2, (0, 0, 255),
                        2)  # scan ke baad nazar aye ga yeh
            cv2.imshow("Result", img)
            cv2.waitKey(500)
            count += 1
        if cv2.waitKey(1) & 0xFF == ord('q'):  # to quit
            set = True;
            break;



##################################################OCR Model###################################################################################################

