import React, { useEffect, useState } from "react";
import {db} from '../../firebase'
import { collection ,query , onSnapshot ,doc, updateDoc} from "firebase/firestore";
import { Col } from "react-bootstrap";

const BookAdmin = ({currentSpot , bookBool}) => {

    const [spots , setSpots] = useState([])
    const currSpot = currentSpot.toString()

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

    var noplate = ""
    var time = ""

    spots.map((spot) => (
        spot.spot === currSpot 
        ?   (time = spot.time , noplate = spot.carNoplate) 
        :   null
    ) ) 

   const unBook = (e) => {
        e.preventDefault();
        spots.map( (spot) => (
            currSpot === spot.spot ?
            updateDoc(doc(db, "spots", spot.id), { booked: "0" ,carNoplate:"", time:""}) : null
        ) )
        bookBool(false,true,false,false,currSpot)
    }

    return(
        <div>
            <Col md="5"  className="float-end">
                <div className="headings-spot" style={{paddingLeft:50,color:"#1e90ff"}} >Spot Number {currSpot}</div>
                <hr/>
       
                    <div className="author">
                        <a href="#pablo" onClick={(e) => e.preventDefault()}>
                            <h5 className="headings"style={{paddingLeft:50,color:"#1e90ff"}}>Admin Booked Spot</h5>
                        </a>
                            <table className="f2-table" >
                                <tbody>
                                    <tr>
                                        <td> <p className="descriptionLeft" style={{color:"#1e90ff"}}>Car Noplate</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="description">{noplate}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft" style={{color:"#1e90ff"}}>Booked Time</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="description">{time} minutes</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <button className="editButton" onClick={unBook} style={{background:"#1e90ff"}} colSpan={0}>UnBook Spot</button> </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    
            </Col>
        </div>
    );
};

export default BookAdmin;