import React from 'react';
import validate from './validateInfo';
import useForm from './useForm';
import './Form.css';
import { Link, Redirect } from 'react-router-dom';

const FormlogIn = ({ notlogin }) => {

  function submitlogin() {
    this.props.history.push('/admin/home');

  }

  const { handleChange, handleSubmit, values, errors, rendersuccess , incorrect} = useForm(
    submitlogin,
    validate
  );
  


  return (
    <div className='loginform-container'>
        <span className='close-btn'>
          <Link to="/admin/home" > x </Link>
        </span>
        <div className='form-content-left'>
            <img
                  src={require("../../assets/pics/PRSlogo.png").default}
                  alt="..." 
                  className='login-img'
            />
        </div> 
    <div className='form-content-right'>
      <form onSubmit={handleSubmit} className='form' noValidate>
        <h1>
          Fill in the information below to login to your account.
        </h1>
        <div className='form-inputs'>
          <input
            className='form-input'
            type='email'
            name='email'
            placeholder='Enter your email'
            value={values.email}
            onChange={handleChange}
          />
          {errors.email && <p>{errors.email}</p>}
        </div>
        <div className='form-inputs'>
          <input
            className='form-input'
            type='password'
            name='password'
            placeholder='Enter your password'
            value={values.password}
            onChange={handleChange}
          />
          {errors.password && <p>{errors.password}</p>}
        </div>
        <button className='form-input-btn' type='submit' onClick={() => notlogin = false}>
          Login
        </button>
        { incorrect
          ? <p style={{color:'red'}}> Incorrect Credentials </p> 
          : null
          }
      </form>
    </div>
    {
      rendersuccess
      ? <Redirect to="/admin/home" />
      : null
    }

    </div>
  );
};

export default FormlogIn;