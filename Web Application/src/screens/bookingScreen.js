import React, { useEffect, useState } from "react";
import "./bookingScreen.css"
import {db} from '../firebase'
import { collection ,query , onSnapshot ,doc, updateDoc, deleteDoc,} from "firebase/firestore";
import { Card, Col } from "react-bootstrap";


const Booking = () => {

    const [showForm, setShowForm] = useState(false);
    const [showUser , setShowUser] = useState(false);

    const [spotNo , setSpotNo] = useState("")
    const [spotNo1 , setSpotNo1] = useState("")
    const [spotNo2 , setSpotNo2] = useState("")
    const [spotNo3 , setSpotNo3] = useState("")
    const [spotNo4 , setSpotNo4] = useState("")
    const [spotNo5 , setSpotNo5] = useState("")
    const [spotNo6 , setSpotNo6] = useState("")
    const [spotNo7 , setSpotNo7] = useState("")
    const [spotNo8 , setSpotNo8] = useState("")
    const [spotNo9 , setSpotNo9] = useState("")
    const [spotNo10 , setSpotNo10] = useState("")
    const [spotNo11 , setSpotNo11] = useState("")
    const [spotNo12 , setSpotNo12] = useState("")
    const [spotNo13 , setSpotNo13] = useState("")
    const [spotNo14 , setSpotNo14] = useState("")
    const [spotNo15 , setSpotNo15] = useState("")
    const [spotNo16 , setSpotNo16] = useState("")
    const [spotNo17 , setSpotNo17] = useState("")
    const [spotNo18 , setSpotNo18] = useState("")
    const [spotNo19 , setSpotNo19] = useState("")
    const [spotNo20 , setSpotNo20] = useState("")
    
    const [Noplate , setNoplate] = useState("")
    const [time , setTime] = useState("")

    const [Uname , setUName] = useState("")
    const [Uemail , setUEmail] = useState("")
    const [UrollNo , setURollNo] = useState("")
    const [UmobileNo , setUMobileNo] = useState("")
    const [UcarName , setUCarName] = useState("")
    const [UNoplate , setUNoplate] = useState("")

    var n = "aaa"

    const handleSubmit = async (e) => {
        e.preventDefault();
        var div=document.getElementById(spotNo);
        div.style.backgroundColor='#FF7F7F';
        document.getElementById("bookingForm").reset();
      };

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

        let setSpot = (spt) => {
            users.map((user) => (
                user.spot === spt ? 
                    setValues(user , spt)
                : null
            ) ) 
        }

        setSpotNo1("1");    setSpot(spotNo1);
        setSpotNo2("2");    setSpot(spotNo2);
        setSpotNo3("3");    setSpot(spotNo3);
        setSpotNo4("4");    setSpot(spotNo4);
        setSpotNo5("5");    setSpot(spotNo5);
        setSpotNo6("6");    setSpot(spotNo6);
        setSpotNo7("7");    setSpot(spotNo7);   
        setSpotNo8("8");    setSpot(spotNo8);
        setSpotNo9("9");    setSpot(spotNo9);
        setSpotNo10("10");    setSpot(spotNo10);
        setSpotNo11("11");    setSpot(spotNo11);
        setSpotNo12("12");    setSpot(spotNo12);
        setSpotNo13("13");    setSpot(spotNo13);
        setSpotNo14("14");    setSpot(spotNo14);
        setSpotNo15("15");    setSpot(spotNo15);
        setSpotNo16("16");    setSpot(spotNo16);
        setSpotNo17("17");    setSpot(spotNo17);
        setSpotNo18("18");    setSpot(spotNo18);
        setSpotNo19("19");    setSpot(spotNo19);
        setSpotNo20("20");    setSpot(spotNo20);       

        return () => ss();
        
    } ,[]); 

      const spots = []
      const [spt , setSpt] = useState([])

      const setValues = (user , st) => {
        //alert(st);
        setUName(user.name) ;
        setUEmail(user.email);
        setURollNo(user.rollNo);
        setUMobileNo(user.mobileNo);
        setUCarName(user.carName);
        setUNoplate(user.carNoplate);
        var div=document.getElementById(st);
        div.style.backgroundColor='#FF7F7F';
        setSpt(st);
      }

    const [bfind, setbfind] = useState(false);
    const sfind = (ss) => {
        let g = spots.find(v => v>ss) 
        /*alert(spots)
     /*   g === ss ?
            setbfind(true)
            : null*/
    } 

    let setSpot = (spt) => {
        users.map((user) => (
            user.spot === spt ? 
                setValues(user , spt)
            : null
        ) ) 
    }

    var sp;
    const getSpotNo =(e) => {
        setSpotNo(e.target.id);
        sp=e.target.id;
        var ch = document.getElementById(sp).style.backgroundColor;
        if ( ch === "rgb(255, 127, 127)" ) {
            setShowForm(false);
            setShowUser(true);
            alert("red")
            setSpot(spotNo);

        } else {
            setShowForm(true);
            setShowUser(false);
        }
        
        setSpot(spotNo);
    }

    /*
    var ch = document.getElementById(spotNo).style.backgroundColor;
        if ( ch === "rgb(255, 127, 127)" ) {
            setShowForm(false);
            setShowUser(true);

        } else {
            setShowForm(true);
            setShowUser(false);
        }
        */

    const  book = (e) => {
      //  alert("book");
        setSpotNo1("1");    setSpot(spotNo1);
        setSpotNo2("2");    setSpot(spotNo2);
        setSpotNo3("3");    setSpot(spotNo3);
        setSpotNo4("4");    setSpot(spotNo4);
        setSpotNo5("5");    setSpot(spotNo5);
        setSpotNo6("6");    setSpot(spotNo6);
        setSpotNo7("7");    setSpot(spotNo7);
        setSpotNo8("8");    setSpot(spotNo8);
        setSpotNo9("9");    setSpot(spotNo9);
        setSpotNo10("10");    setSpot(spotNo10);
        setSpotNo11("11");    setSpot(spotNo11);
        setSpotNo12("12");    setSpot(spotNo12);
        setSpotNo13("13");    setSpot(spotNo13);
        setSpotNo14("14");    setSpot(spotNo14);
        setSpotNo15("15");    setSpot(spotNo15);
        setSpotNo16("16");    setSpot(spotNo16);
        setSpotNo17("17");    setSpot(spotNo17);
        setSpotNo18("18");    setSpot(spotNo18);
        setSpotNo19("19");    setSpot(spotNo19);
        setSpotNo20("20");    setSpot(spotNo20);
     //   alert("end")
    }

    return (
        <div  onLoad={setSpot(7)}>
            <div className="parkinglot" onLoad={sfind("7")}>
                <div className="column-left">
                    <div className="columnBookedSpot ptr" id="10" onClick={getSpotNo}>10</div>
                    <div className="columnBookedSpot ptr" id="9"  onClick={getSpotNo}>9</div>
                    <div className="columnBookedSpot ptr" id="8"  onClick={getSpotNo}>8</div>
                    <div className="columnBookedSpot ptr" id="7"  onClick={getSpotNo}>7</div>
                    <div className="columnBookedSpot ptr" id="6"  onClick={getSpotNo}>6</div>
                    <div className="columnBookedSpot ptr" id="5"  onClick={getSpotNo}>5</div>
                    <div className="columnBookedSpot ptr" id="4"  onClick={getSpotNo}>4</div>
                    <div className="columnBookedSpot ptr" id="3"  onClick={getSpotNo}>3</div>
                    <div className="columnBookedSpot ptr" id="2"  onClick={getSpotNo}>2</div>
                    <div className="columnBookedSpot ptr" id="1"  onClick={getSpotNo}>1</div>
                </div>
                <div className="column-right">
                    <div className="columnBookedSpot ptr" id="11"  onClick={getSpotNo}>11</div>
                    <div className="columnBookedSpot ptr" id="12"  onClick={getSpotNo}>12</div>
                    <div className="columnBookedSpot ptr" id="13"  onClick={getSpotNo}>13</div>
                    <div className="columnBookedSpot ptr" id="14"  onClick={getSpotNo}>14</div>
                    <div className="columnBookedSpot ptr" id="15"  onClick={getSpotNo}>15</div>
                    <div className="columnBookedSpot ptr" id="16"  onClick={getSpotNo}>16</div>
                    <div className="columnBookedSpot ptr" id="17"  onClick={getSpotNo}>17</div>
                    <div className="columnBookedSpot ptr" id="18"  onClick={getSpotNo}>18</div>
                    <div className="columnBookedSpot ptr" id="19"  onClick={getSpotNo}>19</div>
                    <div className="columnBookedSpot ptr" id="20"  onClick={getSpotNo}>20</div>
                </div>
                <div className="door">
                </div>
            </div>
           
            <div className="headings stats" style={{paddingLeft:170 , position:"fixed" }} onClick={book} >
                PARKING LOT <br/> <i className="fas fa-redo ptr" style={{paddingLeft:60}}></i>
             </div>

            {
                showForm ?
                    <div className="bookingForm">
                        <form onSubmit={handleSubmit} id="bookingForm">
                            <div className="headings">Book a spot</div><br></br>
                                <div className="headings">Spot Number {spotNo}</div>
                                <div className='form-inputs'>
                                    <input
                                        className='form-input'
                                        type='text'
                                        name='Noplate'
                                        placeholder='Enter license plate'
                                        value={Noplate}
                                        onChange={(e) => setNoplate(e.target.value)}
                                    />
                                </div>
                                <div className='form-inputs'>
                                <input
                                    className='form-input'
                                    type='time'
                                    name='time'
                                    placeholder='Enter time'
                                    value={time}
                                    onChange={(e) => setTime(e.target.value)}
                                />
                                </div>
                            <div >
                                <button className="editButton">Book</button>
                            </div>
                        </form>
                    </div>
                : 
                    showUser ? 
                    <Col md="5" style={{ paddingRight: 120 , marginRight:30 }} className="float-end">
                        <div className="headings" style={{paddingLeft:80}} >{n}Spot Number {spotNo}</div>
                    <Card className="card-user"> 
                      <div className="card-image">
                        <img
                          alt="..."
                          src={
                            require("../assets/pics/profileBG.jfif")
                              .default
                          }
                        ></img>
                      </div>
                      
                      <Card.Body>
                        <div className="author">
                          <a href="#pablo" onClick={(e) => e.preventDefault()}>
                            <img
                              alt="..."
                              className="avatar border-gray"
                              src={
                                require("../assets/pics/personIcon.jpg")
                                  .default
                              }
                            ></img>
                            <h5 className="title">{Uname}</h5>
                          </a>
                          <p className="description">ROLL NO. :{UrollNo}</p>
                          <p className="description">EMAIL :{Uemail}</p>
                          <p className="description">MOBILE NO. : {UmobileNo}</p>
                          <p className="description">CAR NAME : {UcarName}</p>
                          <p className="description">CARS' NO. PLATE : {UNoplate}</p>
                        </div>
                        <p className="description text-center">
                          
                        </p>
                      </Card.Body>
                    </Card>
                  </Col>
                    : null
            }
        </div>
    );
};

export default Booking;