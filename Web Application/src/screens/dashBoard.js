import React  , {useState , useEffect}  from "react";
import ChartistGraph from "react-chartist";
import {
  Badge,
  Button,
  Card,
  Navbar,
  Nav,
  Table,
  Container,
  Row,
  Col,
  Form,
  OverlayTrigger,
  Tooltip,
} from "react-bootstrap";
import "./dashBoard.css"
import {db} from '../firebase'
import { collection ,query , onSnapshot ,doc, updateDoc, deleteDoc,} from "firebase/firestore";

function ChartsPage() {
  
  
  const [data , setData] = useState([])
   
  useEffect(() => {
      const q = query(collection(db , "data"));
      const ss = onSnapshot(q ,  querySnapshot => {
          let arr = []
          querySnapshot.forEach(doc => {
              arr.push( {...doc.data(), id:doc.id });
          });
          setData(arr);
      });
      return () => ss();
  } ,[]); 

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

    var totalBooking;
    var todayBooking;
    var avaliableSpots;
    var bookedSpots;

    data.map((dt) => (  
      totalBooking = dt.allTimeBooking ,
      todayBooking = dt.todayBooking ,
      avaliableSpots = dt.avaliableSpots ,
      bookedSpots = dt.bookedSpots 
     )) 

     let userBookedcount = 0
     let userOnitswaycount = 0
     let usertotalBookingscount = 0
     let totaluserscount = 0
     users.map((user) => (
       user.status === 1
       ?  userBookedcount = userBookedcount + 1
       :  user.status === 2
          ?  userOnitswaycount = userOnitswaycount + 1
          :  null 
     ))
     users.map((user) => (
      totaluserscount = totaluserscount + 1
    ))

    let totalusersunbooked = totaluserscount - userBookedcount - userOnitswaycount
    usertotalBookingscount = userBookedcount + userOnitswaycount

    let adminBookedcount = 0
    let adminReservedcount = 0
    let totaladmincount = 0
    spots.map((spot) => (
      spot.reserved === "1"
      ?   adminReservedcount = adminReservedcount +1
      :   spot.booked === "1"
          ?   adminBookedcount = adminBookedcount +1
          :   null
    )) 
    totaladmincount = adminBookedcount + adminReservedcount

    let totalunavailablespots = totaladmincount + usertotalBookingscount
    let spotsavailablecount = 20 - totalunavailablespots

    return (
      <>
      <div className="headings-spot">
        REPORTS
      </div>
      <Container fluid>


        <Row className="p-0">


          <Col lg="3" sm="6">
         
            <Card className="card-stats">
              <Card.Body>
                <Row className="p-0"> 
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                    <i className="fas fa-parking text-primary"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Total Parking</p>
                      <p className="card-category">Spots</p>
                      <Card.Title as="h4">20</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>


          </Col>
          <Col lg="3" sm="6">


          <Card className="card-stats">
              <Card.Body>
                <Row className="p-0"> 
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                    <i className="fas fa-parking text-success"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Parking Spots</p>
                      <p className="card-category">Available</p>
                      <Card.Title as="h4">{spotsavailablecount}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>


          </Col>
          <Col lg="3" sm="6">


          <Card className="card-stats">
              <Card.Body>
                <Row className="p-0"> 
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                    <i className="fas fa-parking text-danger"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Parking Spots</p>
                      <p className="card-category">Booked</p>
                      <Card.Title as="h4">{totalunavailablespots}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>



          </Col>
          <Col lg="3" sm="6">


            
          <Card className="card-stats">
              <Card.Body>
                <Row className="p-0"> 
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                    <i className="fas fa-user text-primary"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Total User </p>
                      <p className="card-category">Registered</p>
                      <Card.Title as="h4">{totaluserscount}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>


          </Col>
        </Row>

        <Row className="p-0" style={{marginTop:40}}>
          <Col lg="3" sm="6">
            <Card className="card-stats">
              <Card.Body>
                <Row className="p-0"> 
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                    <i className="fas fa-car text-primary"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Total Users Spots</p>
                      <p className="card-category">Booked</p>
                      <Card.Title as="h4">{usertotalBookingscount}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>


          </Col>
          <Col lg="3" sm="6">


          <Card className="card-stats">
              <Card.Body>
                <Row className="p-0"> 
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                    <i className="fas fa-car text-warning"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">User Bookings</p>
                      <p className="card-category">On its way</p>
                      <Card.Title as="h4">{userOnitswaycount}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>


          </Col>
          <Col lg="3" sm="6">


          <Card className="card-stats">
              <Card.Body>
                <Row className="p-0"> 
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                    <i className="fas fa-car text-danger"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">User Bookings</p>
                      <p className="card-category">Vehicle Parked</p>
                      <Card.Title as="h4">{userBookedcount}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>



          </Col>
          <Col lg="3" sm="6">
            <Card className="card-stats">
              <Card.Body>
                <Row className="p-0"> 
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                    <i className="fas fa-user text-danger"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Total Users</p>
                      <p className="card-category">Booked or on their way</p>
                      <Card.Title as="h4">{usertotalBookingscount}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>
          </Col>
        </Row>

        <Row className="p-0" style={{marginTop:40}}>
          <Col lg="3" sm="6">
            <Card className="card-stats">
              <Card.Body>
                <Row className="p-0"> 
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                    <i className="fas fa-lock text-primary"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Total Admin Spots</p>
                      <p className="card-category">Booked</p>
                      <Card.Title as="h4">{totaladmincount}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>


          </Col>
          <Col lg="3" sm="6">
          <Card className="card-stats">
              <Card.Body>
                <Row className="p-0"> 
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                    <i className="fas fa-lock text-danger"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Admin Bookings</p>
                      <p className="card-category">Spot Booked</p>
                      <Card.Title as="h4">{adminBookedcount}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>


          </Col>
          <Col lg="3" sm="6">


          <Card className="card-stats">
              <Card.Body>
                <Row className="p-0"> 
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                    <i className="fas fa-lock text-warning"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Admin Bookings</p>
                      <p className="card-category">Reserved Spots</p>
                      <Card.Title as="h4">{adminReservedcount}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>



          </Col>
          <Col lg="3" sm="6">


            
          <Card className="card-stats">
              <Card.Body>
                <Row className="p-0"> 
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                    <i className="fas fa-user text-success"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Total Users</p>
                      <p className="card-category">Spot not booked</p>
                      <Card.Title as="h4">{totalusersunbooked}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>


          </Col>
        </Row>

<br /><br />

        <Row className="p-0" >

          {/* <Col md="8">

            <Card>
              <Card.Header>
                <Card.Title as="h4">Booking Spots Trends</Card.Title>
                <p className="card-category">All Time</p>
              </Card.Header>
              <Card.Body>
                <div className="ct-chart" id="chartHours">
                  <ChartistGraph
                    data={{
                      labels: [
                        "8AM",
                        "9AM",
                        "10AM",
                        "11AM",
                        "12PM",
                        "1PM",
                        "2PM",
                        "3PM",
                        "4PM",
                        "5PM",
                      ],
                      series: [
                        [17, 19, 24, 27, 36, 45, 40, 21, 17, 11],
                      ],
                    }}
                    type="Line"
                    options={{
                      low: 0,
                      high: 50,
                      showArea: false,
                      height: "245px",
                      axisX: {
                        showGrid: false,
                      },
                      lineSmooth: true,
                      showLine: true,
                      showPoint: true,
                      fullWidth: true,
                      chartPadding: {
                        right: 50,
                      },
                    }}
                    responsiveOptions={[
                      [
                        "screen and (max-width: 640px)",
                        {
                          axisX: {
                            labelInterpolationFnc: function (value) {
                              return value[0];
                            },
                          },
                        },
                      ],
                    ]}
                  />
                </div>
              </Card.Body>
              <Card.Footer>
                <div className="legend">
                  <i className="fas fa-circle text-info"></i> Total spots booking per time 
                </div>
                <hr></hr>
                <div className="stats">
                  <i className="fas fa-history"></i>
                  Updated 3 minutes ago
                </div>
              </Card.Footer>
            </Card>


          </Col> */}
          <Col md="4">
            <Card>
              <Card.Header>
                <Card.Title as="h4">Total Bookings</Card.Title>
              </Card.Header>
              <Card.Body>
                <div
                  className="ct-chart ct-perfect-fourth"
                  id="chartPreferences"
                >
                  <ChartistGraph
                    data={{
                      labels: [" " , " ", " " , " " ],
                      series: [ 0, totalunavailablespots,0 , spotsavailablecount] ,
                    }}
                    type="Pie"

                  />
                </div>
                <div style={{marginTop:-50,height:50}} >
                    <Row className="p-0 h-5 w-300" sm="2" style={{borderColor:'blue',border:1}}>
                      <Col><i className="fas fa-circle" style={{color:'lightgreen'}}></i>Reserved: {spotsavailablecount}  </Col>
                      <Col style={{paddingLeft:60}}><i className="fas fa-circle text-danger"></i>Booked: {totalunavailablespots}  </Col>
                    </Row>
                  </div>
              </Card.Body>
            </Card>
          </Col>
          <Col md="4">
            <Card>
              <Card.Header>
                <Card.Title as="h4">User Bookings</Card.Title>
              </Card.Header>
              <Card.Body>
                <div
                  className="ct-chart ct-perfect-fourth"
                  id="chartPreferences"
                >
                  <ChartistGraph
                    data={{
                      labels: [" ", " ", " " , " "],
                      series: [0, userBookedcount, userOnitswaycount , 0] ,
                    }}
                    type="Pie"
                  />
                </div>
                <div style={{marginTop:-50,height:50}} >
                    <Row className="p-0 h-5 w-300" sm="2" style={{borderColor:'blue',border:1}}>
                      <Col><i className="fas fa-circle" style={{color:'orange'}}></i>On it's way: {userOnitswaycount}  </Col>
                      <Col style={{paddingLeft:60}}><i className="fas fa-circle text-danger"></i>Booked: {userBookedcount}  </Col>
                    </Row>
                  </div>
              </Card.Body>
            </Card>
          </Col>
          <Col md="4">
            <Card>
              <Card.Header>
                <Card.Title as="h4">Admin Bookings</Card.Title>
              </Card.Header>
              <Card.Body>
                <div
                  className="ct-chart ct-perfect-fourth"
                  id="chartPreferences"
                >
                  <ChartistGraph
                    data={{
                      labels: [" ", " ", " " , " "," "," "," "],
                      series: [adminBookedcount, 0, 0 , 0 , 0 , 0 ,adminReservedcount] ,
                    }}
                    type="Pie"

                  />
                </div>
                  <div style={{marginTop:-50,height:50}} >
                    <Row className="p-0 h-5 w-300" sm="2" style={{borderColor:'blue',border:1}}>
                      <Col><i className="fas fa-circle" style={{color:'gray'}}></i>Reserved: {adminReservedcount}  </Col>
                      <Col style={{paddingLeft:60}}><i className="fas fa-circle"style={{color:'skyblue'}}></i>Booked: {adminBookedcount}  </Col>
                    </Row>
                  </div>
              </Card.Body>
            </Card>
          </Col>
        </Row>


        <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />

        <Row className="p-0" >
          <Col >
            <Card style={{ height: '45rem' }}>
              <Card.Header>
                <Card.Title as="h4">Bookings per Spot</Card.Title>
                <p className="card-category">All Time</p>
              </Card.Header>
              <Card.Body>
                <div className="ct-chart" id="chartActivity">
                  <ChartistGraph
                    data={{
                      labels: [
                        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
                      ],
                      series: [
                        [
                          7,9,11,15,16,19,21,25,26,29,27,25,26,21,11,5,9,7,17,21,
                        ],
                      ],
                    }}
                    type="Bar"
                    options={{
                      seriesBarDistance: 10,
                      reverseData:true,
                      horizontalBars: true ,
                      stackBars: true,
                      low:0,
                      high:30,
                      divisor:5,
                      
                      axisX: {
                        position: 'start',
                        onlyInteger: true,
                      },
                      axisY: {
                        position: 'start'
                      },
                      height: "580px",
                    }}
                    responsiveOptions={[
                      [
                        "screen and (max-width: 640px)",
                        {
                          seriesBarDistance: 5,
                          axisX: {
                            labelInterpolationFnc: function (value) {
                              return value[0];
                            },
                          },
                        },
                      ],
                    ]}
                  />
                </div>
              </Card.Body>
            </Card>
          </Col>
          
        </Row>
      </Container>
    </>
    );
  }


export default ChartsPage;
