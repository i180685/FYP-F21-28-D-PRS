# Parking Spot Detection System
Parking spot detection system using openCV image processing and computer vision in python3.

We have use Python3 and OpenCV in this project.
The YAML Library is used to plot the X & Y cordinates on the video.

To run this program use the python/main.py and for X & Y cordinates use dataset/final.yml

## Working

As soon as the program starts, a video starts running with the predefined spots. These spots are imported from the final.yml file with contains the coordinates on the screen. 

The model detects which spots are booked and with spots are available and color codes it with red for booked and green for available.

A firebase database is also connected with the model and it imports the required information from it and displays it on the screen. The users name and roll number is displayed for those spots which are booked and the user has parked their car in the respective spot.


<img src="/python/frame45.jpg">

##
As soon as the car leaves the spot, and is not in the spot for 20 frames, then the model makes that spot avaible for booking

<img src="/python/frame258.jpg">

##
Once a car who has booked the spot parks in their designated spot, then the model again detects that the car is parke, color codes it to red and shows the required information

<img src="/python/frame886.jpg">
