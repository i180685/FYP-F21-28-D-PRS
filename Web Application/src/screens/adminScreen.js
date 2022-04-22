import React from "react";
import { useLocation, Route, Switch } from "react-router-dom";

import Sidebar from '../components/sidebar/sidebar';
import routes from "../routes";
import Navbar from "../components/navbar/navbar"
import AdminProfiles from "../components/admininfo/adminProfile";

import sdBarImg from "../assets/pics/green.jpg"

function Admin() {
  const [color] = React.useState("green");
  const location = useLocation();
  const mainPanel = React.useRef(null);



  const getRoutes = (routes) => {
    return routes.map((prop, key) => {
      if (prop.layout === "/admin") {
        return ( <>
          <Route
            path={prop.layout + prop.path}
            render={(props) => <prop.component {...props} />}
            key={key}
          />
          </>
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
          <div className="content" >
            {getRoutes(routes)}
            <Switch>
            <Route path="/admin/account" component={AdminProfiles}/>
            </Switch>
          </div>
        </div>
      </div>
    </>
  );
}

export default Admin;
