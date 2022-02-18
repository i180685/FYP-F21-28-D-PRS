import React from 'react';
import validate from './validateInfo';
import useForm from './useForm';
import './Form.css';
import { Link } from 'react-router-dom';

const FormlogIn = ({ submitlogin }) => {
  const { handleChange, handleSubmit, values, errors } = useForm(
    submitlogin,
    validate
  );

  return (
    <div className='form-container'>
        <span className='close-btn'>
          <Link to="/" > x </Link>
        </span>
        <div className='form-content-left'>
          <img className='form-img' src='img/PRSlogo.png' alt='parking' />
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
        <button className='form-input-btn' type='submit'>
          Login
        </button>
        <span className='form-input-login'>
          Need an account?
          <Link to="/signup" > SignUp</Link>
        </span>
      </form>
    </div>
    </div>
  );
};

export default FormlogIn;