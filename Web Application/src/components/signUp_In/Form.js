import React/*, { useState }*/ from 'react';
import './Form.css';
import FormSignup from './FormSignup';
import FormSuccess from './FormSuccess';
import FormlogIn from './FormlogIn';
import {Switch , Route} from 'react-router-dom'
import Home from './Home';

const Form = () => {
  /*
  const [isSubmitted, setIsSubmitted] = useState(false);
  /*const [isLogged , setIsLogin] = useState(false);
  const {accountholder , setaccount} = useState(false);

  function submitForm() {
    setIsSubmitted(true);
  }
  function submitlogin() {
    setIsLogin(true);
  }
  function accountholder1() {
    setaccount(true);
  }  
  /*
  {!isLogged ? (
    <FormlogIn submitform={submitlogin} /> 
  ) : (
    <FormSuccess />
  )}

  {!isSubmitted ? (
          <FormSignup submitForm={submitForm} />
        ) : (
          <FormSuccess />
        )}
  */
  return (
    <>
      <div className='form-container'>
        <span className='close-btn'>×</span>
        <div className='form-content-left'>
          <img className='form-img' src='img/parking.png' alt='parking' />
        </div> 
        <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/signin" exact component={FormlogIn} />
          <Route path="/signup" exact component={FormSignup} />
          <Route path="/success" exact component={FormSuccess} />
        </Switch>
        
      </div>
    </>
  );
};

export default Form;