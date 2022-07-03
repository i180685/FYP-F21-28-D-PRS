# Project Overview:


### Problem Statement: 

Parking is one of the key components of transportation programs all over the world. With the increase of vehicle ownership in recent times, parking has become a conflicting and confusing situation for a lot of people. An average automobile is in motion for a small percentage of time. During the remaining time, it is either parked in a street, in a parking lot or in a house. Urban cities like Islamabad are facing a lot of parking issues and their local development authorities are planning to build parking plazas for public ease.
Though parking in a parking lot may sound easy, it has its own couple of issues such as finding an empty parking space, booking a parking spot beforehand etc. This can cause discomfort for the driver and they might want to just skip this whole ordeal. Thus, work needs to be done to make this process a whole lot easier for the public. 

### Problem Solution:

The problem of parking in a parking lot can be resolved by proper spot divisions, automatic booking and un-booking of parking spots and making a system that is easier for the public to understand.
Hence, we present to you an online parking reservation system which resolves all the issues faced by the public in parking lots. It is a reservation system which gives real time information about the parking spots to the user, lets them book a spot beforehand and automatically un-books a spot once the vehicle leaves. All of the bookings will be done on the license plate of the vehicle which will be captured by the surveillance cameras at the parking lot via image recognition, thus removing all the hassle for the public and making it easier for them to use.

### Goals/Objectives:

* To design an online vehicle reservation system.
* Effective management of existing parking lots.
* Quick way to book a parking spot beforehand.
* Monitoring of parking space and updated induction of vacant parking spots.
* Hassle free and time efficient parking system. 




### Project Scope:

The main purpose of our project will be to facilitate the process of parking in a parking lot for the ease of the public. The mobile application which the user will use has a user-friendly interface having a blueprint of the parking lot showing which spots are already reserved and which spots are available. The parking lot itself will be subdivided into sections for people with disabilities, spots reserved for the whole month and for day to day parking. Upon clicking on the available spots, the user will be given an option to book that spot for themselves and once booked, no other person can park the car at your spot. 
The admin will have their own interfaces with additional functionalities such as removing a spot, adding new spots to the parking lot, surveillance of the parking lot etc. They will also have a user catalogue which will contain all the information of the registered users, and the admin will have the option to delete or add a new user. A security guard will also be an actor in our system and they would be responsible for reserving spots for those individuals who are not registered in the system but wish to park in the parking lot. Both, the admin and the security guard will also have the option of CCTV surveillance of the whole lot. 
The cancellation of a booking, updating the available spots as well as the surveillance will not be done manually by the admin, but will be done directly by the system via surveillance cameras. The surveillance cameras will capture images on which image processing will be done to identify the license plate. Upon extracting the license plate, the system will check whether that vehicle is parked on its designated spot or not. If not, an alert will be sent to the respective vehicle's driver to move it and park it at the right spot. When leaving a spot, the user does not have to manually enter in their app to un-book their spot, rather the system will again perform image recognition on the vehicle and deduce that the vehicle has left the spot and will automatically make it available for other users to book.  





### List of Features:

* The system can identify a license plate from an image.
* It can also recognize the registration number from the number plate.
* The system automatically changes the status of a spot from reserved to parked once the respective vehicle enters the parking lot.
* The system also automatically changes the status from parked to available once the respective vehicle leaves the parking lot.
* The system alerts the user if they are parked incorrectly(not in their spot etc.).
* The system updates the available parking spots on the app in real time.


### Functional Requirements:

* Authentication: Every user must register on the app using a valid email address. After that the user will get an email on the provided email address to verify the email address. After that, When the user will login using that email address, the system will authenticate its credentials and allow the user if successful and give error if unsuccessful. 
* To Edit the given information, the user must login the app using valid credentials.
* System DataBase: All the information taken using input fields in the app, related to the user while creating an account, must be stored in the Database.
* User must successfully login to the app, to add/remove his/her vehicle.
* A user Must/can Reserve only a Single spot.
* Only Admin Can Add/ remove parking spots.
* Only Admin can Book/ unbook multiple spots. 
* Only Admin can access any user information from Database.

### Non-Functional Requirements:

* Users must have a Stable Good Internet connection to use the app.
* Admin Must have a PC/Laptop which supports internet and web browsing.
* Admin Must have a Stable Good Internet connection to use the Web Portal.
* In terms of performance, the system will be highly efficient and effective. 
* In terms of Availability,The system will be available 24/7.
* In terms of usability, the particular system will be of higher quality. It is acceptable that the users will perform tasks through the user-friendly interface.
* In terms of data integrity, this particular system will maintain consistency and accuracy of the collected and stored data.
