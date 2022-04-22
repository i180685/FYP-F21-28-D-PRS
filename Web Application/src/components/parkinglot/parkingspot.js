import React, { useEffect, useState } from "react";
import {db} from '../../firebase'
import { collection ,query , onSnapshot ,doc, updateDoc, deleteDoc,} from "firebase/firestore";
import { Card, Col } from "react-bootstrap";


const ParkingSpot = ({currentSpot , spotClick}) => {

    const [users , setUsers] = useState([])
    
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

    var inparking = false
    const [Uname , setUName] = useState("")
    users.map((user) => (
        user.spot === currSpot 
        ?   user.status === 1
            ? exists = true
            :   user.status === 2 
                ?inparking=true :null
        :   null
    ) ) 
    return (
        <div>
            {
                exists 
                    ?   <div className="columnBookedSpot ptr" onClick={()=>spotClick(true,false,currentSpot,Uname)} >{currentSpot}</div> 
                    :   inparking
                        ?    <div className="columninParkingSpot ptr" onClick={()=>spotClick(true,false,currentSpot,Uname)} >{currentSpot}</div>  
                        :   <div className="columnUnbookedSpot ptr" onClick={()=>spotClick(false,true,currentSpot,Uname)} >{currentSpot}</div>
            }
        </div>
    );
};

export default ParkingSpot;