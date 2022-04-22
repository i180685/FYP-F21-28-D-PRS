import React, { useState }  from "react";
import {
  Button,
  Card,
  Form,
  Container,
  Row,
  Col,
} from "react-bootstrap";


const AdminData = ({orgEmail,id,updateBtn}) => {

  //alert(orgEmail)
  let [email,setEmail] = useState('')
  let [password,setPassword] = useState('')
  const handleEmailChange = e => {
    setEmail(e.target.value)
    orgEmail = email
    //alert(e.target.value + "    "+ orgEmail + "    " + email ) 
  }
  const handlePasswordChange = e => {
    setPassword(e.target.value)
  }

  return (
    <>
      <Container fluid>
        <br />
        <Row className="p-0">
          <Col md="8">
            <Card>
              <Card.Header>
                <Card.Title as="h4">Edit Admin</Card.Title>
              </Card.Header>
              <Card.Body>
                <Form>
                    <Row className="p-0">
                        <Col md="12">
                            <Form.Group>
                                <label>Email</label>
                                <Form.Control
                                value={orgEmail}
                                placeholder="admins` email"
                                type="text"
                                onChange={handleEmailChange}
                                ></Form.Control>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row className="p-0">
                        <Col className="pl-1" md="6">
                            <Form.Group>
                                <label>Password</label>
                                <Form.Control
                                placeholder="admins` password"
                                type="text"
                                value={password}
                                onChange={handlePasswordChange}
                                ></Form.Control>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Button
                        className="btn-fill pull-right"
                        type="submit"
                        variant="info"
                        onClick={updateBtn(id)}
                    >Update Profile
                    </Button>
                    <div className="clearfix"/>
                </Form>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
}

export default AdminData;
