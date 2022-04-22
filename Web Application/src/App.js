import React, { useEffect } from "react";
import { Route, Switch, Redirect, BrowserRouter } from "react-router-dom";
import "./App.css";
import "./Home.css";
import FormlogIn from "./components/signUp_In/FormlogIn";
import AdminScreen from "./screens/adminScreen";

import "bootstrap/dist/css/bootstrap.min.css";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import "./assets/files/animate.min.css"
import logo from "./assets/pics/PRSlogo.png"
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"></link>


function App() {
  
  useEffect( () => {
    document.title="Parking Reservation System"
    document.logo = logo
  })

  return (
    <>
      <BrowserRouter>
        <Switch>
          <Route path="/admin" render={(props) => <AdminScreen {...props} />} />
          <Redirect for="/" to="/signin"  />
        </Switch>
        <Switch>
          <Route path="/signin" component={FormlogIn} />
        </Switch>
      </BrowserRouter>


    </>
  );
}

export default App;
