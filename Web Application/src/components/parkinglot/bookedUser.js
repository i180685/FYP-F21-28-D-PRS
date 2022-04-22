import React, { useEffect, useState } from "react";
import {db} from '../../firebase'
import { collection ,query , onSnapshot ,doc, updateDoc, deleteDoc,} from "firebase/firestore";
import { Card, Col } from "react-bootstrap";

const BookedUser = ({currentSpot , name}) => {

    const [users , setUsers] = useState([])
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

    var Uname = ""
    var Uemail = ""
    var UrollNo = ""
    var UmobileNo = ""
    var UcarName = ""
    var UNoplate = ""

    users.map((user) => (
        user.spot === currSpot 
        ?   (Uname = user.name, Uemail = user.email , UrollNo = user.rollNo , UmobileNo = user.mobileNo , UcarName = user.carName , UNoplate = user.carNoplate) 
        :   null
    ) ) 

    return(
        <div>
            <Col md="5"  className="float-end">
                <div className="headings" style={{paddingLeft:100}} >Spot Number {currSpot}</div>
                <Card className="card-user"> 
                    
                    <Card.Body>
                    <div className="author">
                        <a href="#pablo" onClick={(e) => e.preventDefault()}>
                            <br /><br/><br/><br /><br/>
                            <h5 className="headings">{Uname}</h5>
                        </a>
                            <table className="f2-table" >
                                <tbody>
                                    <tr>
                                        <td> <p className="descriptionLeft right">Roll No :</p> </td>
                                        <td> <p className="description left">{UrollNo}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right">Email :</p> </td>
                                        <td> <p className="description left">{Uemail}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right">Mobile No. :</p> </td>
                                        <td> <p className="description left">{UmobileNo}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right">Car Name:</p> </td>
                                        <td> <p className="description left">{UcarName}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right">Car No.plate :</p> </td>
                                        <td> <p className="description left">{UNoplate}</p> </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </Card.Body>
                </Card>
            </Col>
        </div>
    );
};

export default BookedUser;