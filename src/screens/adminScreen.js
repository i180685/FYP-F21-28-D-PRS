import React from "react";
import { useLocation, Route, Switch } from "react-router-dom";

import Sidebar from '../components/sidebar/sidebar';

import routes from "../routes";
import Booking from "./bookingScreen";
import Home from "./homeScreen";
import Navbar from "../components/navbar/navbar"

import sdBarImg from "../assets/pics/sidebarPic.jpg"


function Admin() {
  const [Image,setImage] = React.useState(sdBarImg);
  const [color, setColor] = React.useState("black");
  const location = useLocation();
  const mainPanel = React.useRef(null);

  const getRoutes = (routes) => {
    return routes.map((prop, key) => {
      if (prop.layout === "/admin") {
        return (
          <Route
            path={prop.layout + prop.path}
            render={(props) => <prop.component {...props} />}
            key={key}
          />
        );
      } 
      
      else {
        return null;
      }
    });
  };

  React.useEffect(() => {
    document.documentElement.scrollTop = 0;
    document.scrollingElement.scrollTop = 0;
    mainPanel.current.scrollTop = 0;
    if (
      window.innerWidth < 993 &&
      document.documentElement.className.indexOf("nav-open") !== -1
    ) {
      document.documentElement.classList.toggle("nav-open");
      var element = document.getElementById("bodyClick");
      element.parentNode.removeChild(element);
    }
  }, [location]);
  return (
    <>
      <div className="wrapper">
        <Sidebar color={color} image={sdBarImg} routes={routes} />
        <div className="main-panel" ref={mainPanel}>
          <Navbar />
          <div className="content">
            {getRoutes(routes)}
          </div>
        </div>
      </div>
    </>
  );
}

export default Admin;
