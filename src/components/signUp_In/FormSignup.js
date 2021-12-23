import React from 'react';
import validate from './validateInfo';
import useForm from './useForm';
import './Form.css';
import { Link } from 'react-router-dom';

const FormSignup = ({ submitForm }) => {
  const { handleChange, handleSubmit, values, errors } = useForm(
    submitForm,
    validate
  );

  //function handleSubmit1() {
    
 // }

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
          Create your account by filling out the information below.
        </h1>
        <div className='form-inputs'>
          <input
            className='form-input'
            type='text'
            name='username'
            placeholder='Enter your username'
            value={values.username}
            onChange={handleChange}
          />
          {errors.username && <p>{errors.username}</p>}
        </div>
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
          <fieldset >
            <label></label>
            <input
              className='form-input-batch'
              type='number'
              name='batch'
              placeholder='Batch'
              value={values.batch}
              onChange={handleChange} />
              <div className='campus'> - i -</div>
              <label></label>
            <input
              className='form-input-id'
              type='number'
              name='id'
              placeholder='ID'
              value={values.id}
              onChange={handleChange}/>
          </fieldset>
          {errors.rollNo && <p>{errors.rollNo}</p>}
        </div>
        <div className='form-inputs'>
          <input
            className='form-input'
            type='number'
            name='mobile'
            placeholder='Enter your mobile No'
            value={values.mobile}
            onChange={handleChange}
          />
          {errors.mobile && <p>{errors.mobile}</p>}
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
        <div className='form-inputs'>
          <input
            className='form-input'
            type='password'
            name='password2'
            placeholder='Confirm your password'
            value={values.password2}
            onChange={handleChange}
          />
          {errors.password2 && <p>{errors.password2}</p>}
        </div>
        <button className='form-input-btn' type='submit'>
          Sign up
        </button>
        <span className='form-input-login'>
          Already have an account? 
          <Link to="/signin" > SignIn </Link> 
        </span>
      </form>
    </div>
    </div>
  );
};

export default FormSignup;