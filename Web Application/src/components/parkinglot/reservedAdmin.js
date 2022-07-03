import React, { useEffect, useState } from "react";
import {db} from '../../firebase'
import { collection ,query , onSnapshot ,doc, updateDoc, deleteDoc,} from "firebase/firestore";
import { Card, Col } from "react-bootstrap";

const ReservedAdmin = ({currentSpot , reserveBool}) => {

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

    var reason = ""

    spots.map((spot) => (
        spot.spot === currSpot 
        ?   (reason = spot.reserveReason) 
        :   null
    ) ) 

   const unReserve = (e) => {
        e.preventDefault();
        spots.map( (spot) => (
            currSpot === spot.spot ?
            updateDoc(doc(db, "spots", spot.id), { reserved: "0" ,reserveReason:""}) : null
        ) )
        reserveBool(false,true,false,false,currSpot)
    }

    return(
        <div>
            <Col md="5"  className="float-end">
                <div className="headings-spot" style={{paddingLeft:50,color:"#324960"}} >Spot Number {currSpot}</div>
                <hr/>
       
                    <div className="author">
                        <a href="#pablo" onClick={(e) => e.preventDefault()}>
                            <h5 className="headings"style={{paddingLeft:50,color:"#324960"}}>Admin Reserved Spot</h5>
                        </a>
                            <table className="f2-table" >
                                <tbody>
                                    <tr>
                                        <td> <p className="descriptionLeft right" style={{textAlign:"center" , color:"#324960"}}>Reason</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="description right" style={{textAlign:"center"}}>{reason}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <button className="editButton" onClick={unReserve} style={{background:"#324960"}}>Make Spot Available</button> </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
            </Col>
        </div>
    );
};

export default ReservedAdmin;