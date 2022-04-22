import React, { useEffect, useState } from "react";
import {db} from '../../firebase'
import { collection ,query , onSnapshot } from "firebase/firestore";
import ParkingSpot from "./parkingspot";
import BookedUser from "./bookedUser";

const Parkinglot = () => {

    const [users , setUsers] = useState([])

      
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

    var [showForm,setshowForm] = useState(false)
    var [showUser,setshowUser] = useState(false)
    var [formSpot,setformSpot] = useState(0)
    const [Uname , setUName] = useState("")
    function spotClicked(booked,available,chosenSpot,n) {
        setshowUser(booked)
        setshowForm(available)
        setformSpot(chosenSpot)
        setUName(n)
    }
    const adminBook = (e) => {
        e.preventDefault();
        // var ParkingSpot = document.getElementById(formSpot);
        // ParkingSpot.style.backgroundColor='#FF7F7F';
    }

    return (
        <div>
            <div className="parkinglot">
                <div className="column-left">
                    <ParkingSpot currentSpot={10} id="10" spotClick={spotClicked}/>
                    <ParkingSpot currentSpot={9} id="9" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={8} id="8" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={7} id="7" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={6} id="6" spotClick={spotClicked} />                 
                    <ParkingSpot currentSpot={5} id="5" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={4} id="4" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={3} id="3" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={2} id="2" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={1} id="1" spotClick={spotClicked} />
                </div>
                <div className="column-right">
                    <ParkingSpot currentSpot={11} id="11" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={12} id="12" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={13} id="13" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={14} id="14" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={15} id="15" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={16} id="16" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={17} id="17" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={18} id="18" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={19} id="19" spotClick={spotClicked} />
                    <ParkingSpot currentSpot={20} id="20" spotClick={spotClicked} />
                </div>
                <div className="door" />
            </div>
        {
            showForm
                ?   <div className="bookingForm" >
                        <form id="bookingForm">
                            <div className="headings">Book a spot</div><br></br>
                                <div className="headings">Spot Number {formSpot}</div>
                                <div className='form-inputs'>
                                    <input
                                        className='form-input'
                                        type='text'
                                        name='Noplate'
                                        placeholder='Enter license plate'
                                    />
                                </div>
                                <div className='form-inputs'>
                                <input
                                    className='form-input'
                                    type='time'
                                    name='time'
                                    placeholder='Enter time'                            
                                />
                                </div>
                            <div >
                                <button className="editButton" onClick={adminBook}>Book</button>
                            </div>
                        </form>
                    </div>
                :   showUser
                        ?   <BookedUser currentSpot={formSpot} name={Uname}/>
                        :   null
        }
        </div>
    );
};

export default Parkinglot;