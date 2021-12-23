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

    return (
      <>
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
                      <p className="card-category">Total Bookings</p>
                      <p className="card-category">All time</p>
                      <Card.Title as="h4">{totalBooking}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
              <Card.Footer>
                <hr></hr>
                <div className="stats ptr">
                  <i className="fas fa-redo mr-1"></i>
                  Update Now
                </div>
              </Card.Footer>
            </Card>


          </Col>
          <Col lg="3" sm="6">


          <Card className="card-stats">
              <Card.Body>
                <Row className="p-0"> 
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                    <i className="fas fa-parking text-warning"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Total Bookings</p>
                      <p className="card-category">Today</p>
                      <Card.Title as="h4">{todayBooking}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
              <Card.Footer>
                <hr></hr>
                <div className="stats ptr">
                  <i className="fas fa-redo mr-1"></i>
                  Update Now
                </div>
              </Card.Footer>
            </Card>


          </Col>
          <Col lg="3" sm="6">


          <Card className="card-stats">
              <Card.Body>
                <Row className="p-0"> 
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                    <i className="fas fa-car text-success"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Parking Spots</p>
                      <p className="card-category">Avaliable</p>
                      <Card.Title as="h4">{avaliableSpots}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
              <Card.Footer>
                <hr></hr>
                <div className="stats ptr">
                  <i className="fas fa-redo mr-1"></i>
                  Update Now
                </div>
              </Card.Footer>
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
                      <p className="card-category">Parking Spots</p>
                      <p className="card-category">Booked</p>
                      <Card.Title as="h4">{bookedSpots}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
              <Card.Footer>
                <hr></hr>
                <div className="stats ptr ">
                  <i className="fas fa-redo mr-1"></i>
                  Update Now
                </div>
              </Card.Footer>
            </Card>


          </Col>
        </Row>

<br /><br /><br /><br />

        <Row className="p-0" >

          <Col md="8">

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


          </Col>
          <Col md="4">
            <Card>
              <Card.Header>
                <Card.Title as="h4">Booking Batch-wise</Card.Title>
                <p className="card-category">Fall 2021</p>
              </Card.Header>
              <Card.Body>
                <div
                  className="ct-chart ct-perfect-fourth"
                  id="chartPreferences"
                >
                  <ChartistGraph
                    data={{
                      labels: ["30%", "20%", "40%" , "10%"],
                      series: [30, 20, 40 , 10] ,
                    }}
                    type="Pie"

                  />
                </div>

                  <div className="legend" height="100px">
                    <Row className="p-0 h-25" sm="4 ">
                      <Col><i className="fas fa-circle text-info"></i>18th  </Col>
                      <Col><i className="fas fa-circle text-danger"></i>19th  </Col>
                      <Col><i className="fas fa-circle text-warning"></i>20th  </Col>
                      <Col><i className="fas fa-circle text-success"></i>21st</Col>
                    </Row>
                  </div>
              </Card.Body>
              <Card.Footer>
                <hr></hr>
                <div className="stats">
                  <i className="fas fa-history"></i>
                  Updated 5 minutes ago
                </div>
              </Card.Footer>
            </Card>
          </Col>
        </Row>


        <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />

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
                        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,
                      ],
                      series: [
                        [
                          7,9,11,15,16,19,21,25,26,29,27,25,26,21,11,5,
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
                      height: "500px",
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
              <Card.Footer>
              <div className="legend">
                  <i className="fas fa-circle text-info"></i> Total booking per spot
                </div>
                <hr></hr>
                <div className="stats">
                  <i className="fas fa-history"></i>
                  Updated 7 minutes ago
                </div>
              </Card.Footer>
            </Card>
          </Col>
          
        </Row>
      </Container>
    </>
    );
  }


export default ChartsPage;
