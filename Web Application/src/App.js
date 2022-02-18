import React from "react";
import { Route, Switch, Redirect, BrowserRouter } from "react-router-dom";
import "./App.css";
import "./Home.css";
import FormlogIn from "./components/signUp_In/FormlogIn";
import FormSignup from "./components/signUp_In/FormSignup";
import FormSuccess from "./components/signUp_In/FormSuccess";
import Home from "./screens/homeScreen";
import { ProSidebar, Menu, MenuItem, SubMenu } from "react-pro-sidebar";
import UserProfiles from "./components/userInfo/userProfiles";
import Booking from "./screens/bookingScreen";
import Sidebar from "./components/sidebar/sidebar.js";
import routes from "./routes";

import AdminScreen from "./screens/adminScreen";

import "bootstrap/dist/css/bootstrap.min.css";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import "./assets/files/animate.min.css"
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"></link>
function App() {
  return (
    <>
      <title>PRS</title>

      <BrowserRouter>
        <Switch>
          <Route path="/admin" render={(props) => <AdminScreen {...props} />} />
          <Redirect for="/" to="/admin/home" />
        </Switch>
      </BrowserRouter>
    </>
  );
}

export default App;
