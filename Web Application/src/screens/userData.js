import React , {useState , useEffect} from "react";
import {db} from '../firebase'
import { collection ,query , onSnapshot ,doc, updateDoc, deleteDoc,} from "firebase/firestore";
// react-bootstrap components
import {
  Badge,
  Button,
  Card,
  Form,
  Navbar,
  Nav,
  Container,
  Row,
  Col,
} from "react-bootstrap";


const User = ({name,email,rollNo,password,mobileNo,carName,carComp,carNoplate,spot,id}) => {

  const toggleComplete = async (user) => {
    await updateDoc(doc(db, "users", user.id), { 
        name: user.name,
        email: user.email,
        password: user.password,
        rollNo: user.rollNo,
        mobileNo: user.mobileNo,
        carName: user.carName,
        carComp: user.carComp,
        carNoplate: user.carNoplate, 
    });
  };

  return (
    <>
      <Container fluid>

        <br />

        <Row className="p-0">

          <Col md="8">

            <Card>
              <Card.Header>
                <Card.Title as="h4">Edit Profile</Card.Title>
              </Card.Header>
              <Card.Body>
                <Form>
          


                  <Row className="p-0">
                    <Col md="12">
                      <Form.Group>
                        <label>Name : </label>
                        <Form.Control
                          value={name}
                          placeholder="Users` Name"
                          type="text"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Row className="p-0">
                    <Col md="12">
                      <Form.Group>
                        <label>Email</label>
                        <Form.Control
                          value={email}
                          placeholder="users` email"
                          type="text"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Row className="p-0">
                    <Col md="12">
                      <Form.Group>
                        <label>Mobile Number</label>
                        <Form.Control
                          value={mobileNo}
                          placeholder="users` Mobile Number"
                          type="text"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Row className="p-0">
                    <Col className="pr-1" md="6">
                      <Form.Group>
                        <label>Roll Number</label>
                        <Form.Control
                          value={rollNo}
                          placeholder="users` rollNo"
                          type="text"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                    <Col className="pl-1" md="6">
                      <Form.Group>
                        <label>Password</label>
                        <Form.Control
                          value={password}
                          disabled
                          placeholder="users` password"
                          type="text"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>

                  <hr/>
                  <Row className="p-0">
                    <Col md="12">
                      <Form.Group>
                        <label>Car Name</label>
                        <Form.Control
                          value={carName}
                          placeholder="Car Name of user"
                          type="text"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Row className="p-0">
                    <Col md="12">
                      <Form.Group>
                        <label>Car Company</label>
                        <Form.Control
                          value={carComp}
                          placeholder="Maker Company of users` car"
                          type="text"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Row className="p-0">
                    <Col className="pr-1" md="6">
                      <Form.Group>
                        <label>Car Number plate</label>
                        <Form.Control
                          value={carNoplate}
                          placeholder="Number plate of users` car"
                          type="text"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                    <Col className="pl-1" md="6">
                      <Form.Group>
                        <label>Spot Booked</label>
                        <Form.Control
                          value={spot}
                          disabled
                          placeholder="Spot Booked by the user"
                          type="text"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>





                  <Button
                    className="btn-fill pull-right"
                    type="submit"
                    variant="info"
                  >
                    Update Profile
                  </Button>
                  <div className="clearfix"></div>
                </Form>
              </Card.Body>
            </Card>
          </Col>



          <Col md="4">
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
                    <h5 className="title">{name}</h5>
                  </a>
                  <p className="description">{rollNo}</p>
                  <p className="description">{email}</p>
                </div>
                <p className="description text-center">
                  {}
                </p>
              </Card.Body>
              <hr></hr>
              <div className="button-container mr-auto ml-auto">
                <Button
                  className="btn-simple btn-icon"
                  href="#pablo"
                  onClick={(e) => e.preventDefault()}
                  variant="link"
                >
                  <i className="fab fa-facebook-square"></i>
                </Button>
                <Button
                  className="btn-simple btn-icon"
                  href="#pablo"
                  onClick={(e) => e.preventDefault()}
                  variant="link"
                >
                  <i className="fab fa-twitter"></i>
                </Button>
                <Button
                  className="btn-simple btn-icon"
                  href="#pablo"
                  onClick={(e) => e.preventDefault()}
                  variant="link"
                >
                  <i className="fab fa-google-plus-square"></i>
                </Button>
              </div>
            </Card>
          </Col>
        </Row>
        
      </Container>
    </>
  );
}

export default User;
