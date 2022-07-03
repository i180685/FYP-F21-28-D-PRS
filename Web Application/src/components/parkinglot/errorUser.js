import React, { useEffect, useState } from "react";
import {db} from '../../firebase'
import { collection ,query , onSnapshot ,doc, updateDoc, deleteDoc,} from "firebase/firestore";
import { Card, Col } from "react-bootstrap";

const ErrorUser = ({currentSpot }) => {

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
    
    let errorName, errorEmail, errorRollNo, errorMobile, errorCar, errorNoplate
    spots.map((spot) => (
        spot.spot === currSpot
        ?   (errorName=spot.errorName, errorCar=spot.errorCar, errorRollNo=spot.errorRollNo, errorMobile=spot.errorMobile, errorEmail=spot.errorEmail, errorNoplate=spot.errorNoplate )
        :   null
    )) 

    return(
        <div>
            <Col md="5"  className="float-end">
                <div className="headings-spot" style={{paddingLeft:50,color:"#ba55d3"}} >Spot Number {currSpot}</div>
                <hr/>
       
                    <div className="author">
                        <a href="#pablo" onClick={(e) => e.preventDefault()}>
                            <h5 className="headings"style={{paddingLeft:50,color:"#ba55d3"}}>Parked at wrong spot</h5>
                        </a>
                            <table className="f2-table" >
                                <tbody>
                                    <tr>
                                        <td> <p className="descriptionLeft right" style={{color:"#ba55d3"}}>Name :</p> </td>
                                        <td> <p className="description left">{errorName}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right" style={{color:"#ba55d3"}}>Roll No :</p> </td>
                                        <td> <p className="description left">{errorRollNo}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right" style={{color:"#ba55d3"}}>Email :</p> </td>
                                        <td> <p className="description left">{errorEmail}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right" style={{color:"#ba55d3"}}>Mobile No. :</p> </td>
                                        <td> <p className="description left" >{errorMobile}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right" style={{color:"#ba55d3"}}>Car Name:</p> </td>
                                        <td> <p className="description left">{errorCar}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right" style={{color:"#ba55d3"}}>Car No.plate :</p> </td>
                                        <td> <p className="description left">{errorNoplate}</p> </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    
            </Col>
        </div>
    );
};

export default ErrorUser;