import React, { useEffect, useState } from "react";
import {db} from '../../firebase'
import { collection ,query , onSnapshot, updateDoc, doc } from "firebase/firestore";
import ParkingSpot from "./parkingspot";
import BookedUser from "./bookedUser";
import ReservedAdmin from "./reservedAdmin";
import BookAdmin from "./bookAdmin.js";
import ErrorUser from "./errorUser";

const Parkinglot = () => {

    const [users , setUsers] = useState([])
    const [spots , setSpots] = useState([])
      
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

    var [showForm,setshowForm] = useState(false)
    var [showUser,setshowUser] = useState(false)
    var [showReserved,setshowReserved] = useState(false)
    var [showAdminBook,setshowAdminBook] = useState(false)
    var [showError,setshowError] = useState(false)
    var [formSpot,setformSpot] = useState("0")

    const [Uname , setUName] = useState("")
    function spotClicked(booked,available,reserved,adminBook,errorSpot,chosenSpot,n) {
        setshowUser(booked)
        setshowForm(available)
        setformSpot(chosenSpot)
        setshowReserved(reserved)
        setshowAdminBook(adminBook)
        setshowError(errorSpot)
        setUName(n)
    }
    function unreserveSpot(booked,available,reserved,adminBook,chosenSpot) {
        setshowUser(booked)
        setshowForm(available)
        setformSpot(chosenSpot)
        setshowReserved(reserved)
        setshowAdminBook(adminBook)
    }
    function unBookAdminSpot(booked,available,reserved,adminBook,chosenSpot) {
        setshowUser(booked)
        setshowForm(available)
        setformSpot(chosenSpot)
        setshowReserved(reserved)
        setshowAdminBook(adminBook)
    }

    const adminBook = (e) => {
        e.preventDefault();
        spots.map( (spot) => (
            formSpot.toString() === spot.spot ?
            updateDoc(doc(db, "spots", spot.id), { booked: "1", carNoplate:carNo, time:time }) : null
        ) )
        setshowUser(false)
        setshowForm(false)
        setshowReserved(false)
        setshowAdminBook(true)
    }

    const adminReserve = (e) => {
        e.preventDefault();
        spots.map( (spot) => (
            formSpot.toString() === spot.spot ?
            updateDoc(doc(db, "spots", spot.id), { reserved: "1" ,reserveReason:reason}) : null
        ) )
        setshowUser(false)
        setshowForm(false)
        setshowAdminBook(false)
        setshowReserved(true)
    }

    let [reason,setReason]=useState("");
    let [carNo,setCarno]=useState("");
    let [time,setTime]=useState("");
    function onReserveReason(e) {
        setReason(e.target.value)
    };
    function onNoplate(e) {
        setCarno(e.target.value)
    };
    function onTime(e) {
        setTime(e.target.value)
    };

    return (
        <div>
            <div className="headings-spot">
            PARKING LOT
        </div>
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
            <div className="legend">
            <   div className="green-legend" /> available <br/>
                <div className="red-legend" /> booked <br/>
                <div className="orange-legend" /> on its way <br/>
                <div className="black-legend" /> reserved<br/>
                <div className="blue-legend" /> booked by admin <br/>
                <div className="purple-legend" /> error spot
            </div>
        {
            showForm
                ?  
                    <div className="bookingForm" style={{marginTop:0}}>
                    <div className="headings-spot" >Spot Number {formSpot}</div> <hr/>
                        <form id="bookingForm">
                            <div className="headings">Book a spot</div>
                                <div className='form-inputs'>
                                    <input
                                        className='form-input'
                                        type='text'
                                        name='Noplate'
                                        placeholder='Enter license plate'
                                        onChange={onNoplate}
                                    />
                                </div>
                                <div className='form-inputs'>
                                <input
                                    className='form-input'
                                    type='text'
                                    name='time'
                                    placeholder='Enter time in minutes'  
                                    onChange={onTime}                          
                                />
                                </div>
                            <div >
                                <button className="editButton" onClick={adminBook}>Book</button>
                            </div>
                        </form>
                        <form id="bookingForm">
                            <div className="headings">Reserve a spot</div>
                                <div className='form-inputs'>
                                    <input
                                        className='form-input'
                                        type='text'
                                        name='Noplate'
                                        placeholder='Enter Reason'
                                        onChange={onReserveReason}
                                    />
                                </div>
                            <div >
                                <button className="editButton" onClick={adminReserve}>Reserve</button>
                            </div>
                        </form>
                    </div>
                    
                :   showUser
                        ?   <BookedUser currentSpot={formSpot} name={Uname}/>
                        :   showReserved
                            ?   <ReservedAdmin currentSpot={formSpot} reserveBool={unreserveSpot}/>
                            :   showAdminBook
                                ?   <BookAdmin currentSpot={formSpot} bookBool={unBookAdminSpot}/>
                                :   showError
                                    ?   <ErrorUser currentSpot={formSpot} />
                                    :   <div className="headings-spot" style={{marginLeft:600, marginTop:100}}> Choose a spot </div>
        }
        </div>
    );
};

export default Parkinglot;