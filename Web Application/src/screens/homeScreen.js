import React from "react";
import "./homeScreen.css"
import 'react-pro-sidebar/dist/css/styles.css';
import { Link } from "react-router-dom";


const Home = () => {
    return (
        <div className="p-0 m-0">
                <meta charset="UTF-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1" />
                <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css" />
                
                <div className="m-0 p-0 "  id="home">
                    <img src={require("../assets/pics/PRSlogo.png").default} alt="Home" width="400" height="500" marginLeft="300"/>
                    <div className="w3-display-bottomleft w3-padding-large headings">
                        <div className="w3-xxlarge" style={{marginBottom:300 , marginLeft:400 ,color:"green"}}>PARKING RESERVATION SYSTEM</div>
                    </div>
                </div>

                <br /><br /><br /> <br /><br /><hr />

                <div class="w3-row p-10" id="about">
                    <div class="w3-col m6  w3-hide-small">
                    <img src={require("../assets/pics/parkprob.jpg").default} alt="Table Setting" width="500" height="500" style={{marginTop:60}}/>
                    </div>

                    <div class="w3-col m6 w3-padding-large">
                    <h1 class="w3-center">Parking Issues</h1><br />
                    <p class="w3-large">Parking is one of the key components of transportation programs all over the world. With
                                        the increase of vehicle ownership in recent times, parking has become a conflicting and
                                        confusing situation for a lot of people. An average automobile is in motion for a small
                                        percentage of time. During the remaining time, it is either parked in a street, in a parking lot
                                        or in a house. Urban cities like Islamabad are facing a lot of parking issues and their local
                                        development authorities are planning to build parking plazas for public ease.
                                        Though parking in a parking lot may sound easy, it has its own couple of issues such as
                                        finding an empty parking space, booking a parking spot beforehand etc. This can cause
                                        discomfort for the driver and they might want to just skip this whole ordeal. Thus, work
                                        needs to be done to make this process a whole lot easier for the public.
                    </p>
                    </div>
                </div>
                
                <hr />
                
                <div class="w3-row w3-padding-64" id="menu">
                    <div class="w3-col l6 w3-padding-large">
                        <h1 class="w3-center">Parking Solution</h1><br />
                        <p class="w3-large">The problem of parking in a parking lot can be resolved by proper spot divisions, automatic
                                            booking and un-booking of parking spots and making a system that is easier for the public to
                                            understand. Hence, we present to you an online parking reservation system which resolves all the issues
                                            faced by the public in parking lots. It is a reservation system which gives real time
                                            information about the parking spots to the user, lets them book a spot beforehand and
                                            automatically un-books a spot once the vehicle leaves. All of the bookings will be done on
                                            the license plate of the vehicle which will be captured by the surveillance cameras at the
                                            parking lot via image recognition, thus removing all the hassle for the public and making it
                                            easier for them to use
                        </p>  
                    </div>
                    
                    <div class="w3-col l6 w3-padding-large">
                        <img src={require("../assets/pics/parksol.jpg").default}  alt="Table Setting" width="500" height="500" />
                    </div>
                </div>
                
            
        </div>
    );
};

export default Home;


/*
<div className="w3-top float-end " style={{marginTop:20, marginLeft:700, paddingRight:25 , width:300 , position:"fixed" , zIndex:99999}}>
                    <div className="w3-bar w3-white">
                        
                        <a href="#home" className="w3-bar-item w3-button">Home</a>
                        <a href="#about" className="w3-bar-item w3-button">Problem</a>
                        <a href="#menu" className="w3-bar-item w3-button">Solution</a>
                       
                    </div>
                </div>
*/