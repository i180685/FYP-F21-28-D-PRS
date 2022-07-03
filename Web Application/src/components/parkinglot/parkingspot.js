import React, { useEffect, useState } from "react";
import {db} from '../../firebase'
import { collection ,query , onSnapshot ,doc, updateDoc, deleteDoc,} from "firebase/firestore";
import { Card, Col } from "react-bootstrap";


const ParkingSpot = ({currentSpot , spotClick}) => {

    const [users , setUsers] = useState([])
    const [spots , setSpots] = useState([])
    
    var exists = false
    const currSpot = currentSpot.toString()

    useEffect(() => {
        const q = query(collection(db , "users"));
        const ss = onSnapshot(q ,  querySnapshot => {
            let arr = []
            querySnapshot.forEach(doc => {
                arr.push( {...doc.data(), id:doc.id });
            });
            setUsers(arr);
        });  
      return () => ss();
    } ,[]); 

    useEffect(() => {
        const q = query(collection(db , "spots"));
        const ss = onSnapshot(q ,  querySnapshot => {
            let arr = []
            querySnapshot.forEach(doc => {
                arr.push( {...doc.data(), id:doc.id });
            });
            setSpots(arr);
        });
      return () => ss();
    } ,[]);

    var inparking = false
    const [Uname , setUName] = useState("")
    users.map((user) => (
        user.spot === currSpot 
        ?   user.status === 1
            ?   exists = true
            :   user.status === 2 
                ?   inparking=true 
                :   null
        :   null
    ) ) 

    var adminBooked = false
    var adminReserved = false
    spots.map((spot) => (
        spot.spot === currSpot
        ?   spot.booked === "1" 
            ?   adminBooked = true
            :   spot.reserved === "1"
                ?   adminReserved = true
                :   null
        :   null
    ))

    var errorSpot = false
    spots.map((spot) => (
        spot.spot === currSpot
        ?   spot.errorUser === "1"
            ?   errorSpot=true 
            :   null
        :   null
    )) 
    return (
        <div>
            {
                exists 
                    ?   <div className="columnBookedSpot ptr" onClick={()=>spotClick(true,false,false,false,false,currentSpot,Uname)} >{currentSpot}</div> 
                    :   inparking
                        ?   <div className="columninParkingSpot ptr" onClick={()=>spotClick(true,false,false,false,false,currentSpot,Uname)} >{currentSpot}</div>  
                        :   adminBooked
                            ?   <div className="columnBookedAdmin ptr" onClick={()=>spotClick(false,false,false,true,false,currentSpot,Uname)} >{currentSpot}</div>
                            :   adminReserved
                                ?   <div className="columnReservedAdmin ptr" onClick={()=>spotClick(false,false,true,false,false,currentSpot,Uname)} >{currentSpot}</div>
                                :   errorSpot
                                    ?   <div className="columnErrorSpot ptr" onClick={()=>spotClick(false,false,false,false,true,currentSpot,Uname)} >{currentSpot}</div>
                                    :   <div className="columnUnbookedSpot ptr" onClick={()=>spotClick(false,true,false,false,false,currentSpot,Uname)} >{currentSpot}</div>
            }
        </div>
    );
};

export default ParkingSpot;