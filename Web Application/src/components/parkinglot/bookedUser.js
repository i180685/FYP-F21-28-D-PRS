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
    var Ustatus = ""
    var Upayment = ""

    users.map((user) => (
        user.spot === currSpot 
        ?   (Uname = user.name, Uemail = user.email , UrollNo = user.rollNo , UmobileNo = user.mobileNo , UcarName = user.carName, Upayment = user.payment, Ustatus = user.status , UNoplate = user.carNoplate) 
        :   null
    ) ) 

    return(
        <div>
            <Col md="5"  className="float-end">
                <div className="headings-spot" style={{paddingLeft:50,color:"#FF7F7F"}} >Spot Number {currSpot}</div>
                <hr/>
       
                    <div className="author">
                        <a href="#pablo" onClick={(e) => e.preventDefault()}>
                            <h5 className="headings"style={{paddingLeft:100,color:"#FF7F7F"}}>{Uname}</h5>
                        </a>
                            <table className="f2-table" >
                                <tbody>
                                    <tr>
                                        <td> <p className="descriptionLeft right" style={{color:"#FF7F7F"}}>Roll No :</p> </td>
                                        <td> <p className="description left">{UrollNo}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right" style={{color:"#FF7F7F"}}>Email :</p> </td>
                                        <td> <p className="description left">{Uemail}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right" style={{color:"#FF7F7F"}}>Mobile No. :</p> </td>
                                        <td> <p className="description left">{UmobileNo}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right" style={{color:"#FF7F7F"}}>Car Name:</p> </td>
                                        <td> <p className="description left">{UcarName}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right" style={{color:"#FF7F7F"}}>Car No.plate :</p> </td>
                                        <td> <p className="description left">{UNoplate}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right" style={{color:"#FF7F7F"}}>Payment :</p> </td>
                                        <td> <p className="description left">Rs: {Upayment}</p> </td>
                                    </tr>
                                    <tr>
                                        <td> <p className="descriptionLeft right" style={{color:"#FF7F7F"}}>Status :</p> </td>
                                        <td> { Ustatus === 2 
                                                ? <p className="description left">On its way</p> 
                                                : <p className="description left">Vehicle parked</p> 
                                            } </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    
            </Col>
        </div>
    );
};

export default BookedUser;