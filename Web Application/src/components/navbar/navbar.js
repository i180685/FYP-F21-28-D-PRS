import React from "react";
import { Link ,NavLink} from "react-router-dom";
import { Navbar, Container, Nav,  Button, Form, InputGroup } from "react-bootstrap";

function Header() {
  
  const mobileSidebarToggle = (e) => {
    e.preventDefault();
    document.documentElement.classList.toggle("nav-open");
    var node = document.createElement("div");
    node.id = "bodyClick";
    node.onclick = function () {
      this.parentElement.removeChild(this);
      document.documentElement.classList.toggle("nav-open");
    };
    document.body.appendChild(node);
  };

  return (
    <Navbar bg="light" className="sticky-top py-0 m-0 p-0" >

      <Container fluid>

        <div className="d-flex justify-content-center align-items-center ml-2 ml-lg-0">
          <Button
            variant="dark"
            className="d-lg-none btn-fill d-flex justify-content-center align-items-center rounded-circle p-2"
            onClick={mobileSidebarToggle}
          > 
            <i className="fas fa-ellipsis-v"></i>
          </Button>
        </div>

        <Navbar.Toggle aria-controls="basic-navbar-nav" className="mr-2">
          <span className="navbar-toggler-bar burger-lines"></span>
          <span className="navbar-toggler-bar burger-lines"></span>
          <span className="navbar-toggler-bar burger-lines"></span>
        </Navbar.Toggle>

        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="nav mr-auto" navbar>
             <Nav.Item>
              <Nav.Link
                className="m-0"
                href="#pablo"
                onClick={(e) => e.preventDefault()}
              >
                <InputGroup >
                  <InputGroup.Text id="basic-addon1"><i className="fas fa-search" /> </InputGroup.Text>
                    <Form.Control
                      placeholder="Search"
                      aria-label="Search"
                      aria-describedby="basic-addon1"
                    />
                </InputGroup>
              </Nav.Link>
            </Nav.Item>
          </Nav>
          
          <Nav className="ms-auto" navbar>

            <Nav.Item>
              <Nav.Link
                className="m-0"
                href="#pablo"
                onClick={(e) => e.preventDefault()}
              >
                 <NavLink
                    to="/admin/account"
                  >
                    <i class="fas fa-user fa-2x" />
                  </NavLink>
                 
                <span className="d-lg-none ml-1"></span>
              </Nav.Link>
            </Nav.Item>
           
            <Nav.Item>
              <Nav.Link
                className="m-0"
                href="#pablo"
                onClick={(e) => e.preventDefault()}
              > 
                <Link to="/signin">
                  < i class="fas fa-sign-out-alt fa-2x" ></i>   
                </Link>
                <span className="d-lg-none ml-1"></span>
              </Nav.Link>
            </Nav.Item>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Header;


/*
 <Dropdown as={Nav.Item}>
              <Dropdown.Toggle
                aria-expanded={false}
                aria-haspopup={true}
                as={Nav.Link}
                data-toggle="dropdown"
                id="navbarDropdownMenuLink"
                variant="default"
                className="m-0"
              >
                <span className="no-icon">Dropdown</span>
              </Dropdown.Toggle>
              <Dropdown.Menu aria-labelledby="navbarDropdownMenuLink">
                <Dropdown.Item
                  href="#pablo"
                  onClick={(e) => e.preventDefault()}
                >
                  Action
                </Dropdown.Item>
                <Dropdown.Item
                  href="#pablo"
                  onClick={(e) => e.preventDefault()}
                >
                  Another action
                </Dropdown.Item>
                <Dropdown.Item
                  href="#pablo"
                  onClick={(e) => e.preventDefault()}
                >
                  Something
                </Dropdown.Item>
                <Dropdown.Item
                  href="#pablo"
                  onClick={(e) => e.preventDefault()}
                >
                  Something else here
                </Dropdown.Item>
                <div className="divider"></div>
                <Dropdown.Item
                  href="#pablo"
                  onClick={(e) => e.preventDefault()}
                >
                  Separated link
                </Dropdown.Item>
              </Dropdown.Menu>
            </Dropdown>
*/