import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import FormSuccess from './FormSuccess';

const useForm = (callback, validate) => {
  const [values, setValues] = useState({
    username: '',
    email: '',
    batch:'',
    id:'',
    rollNo:'',
    mobile:'',
    password: '',
    password2: ''
  });
  const [errors, setErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [isLogged, setIsLogin] = useState(false);

  const handleChange = e => {
    const { name, value } = e.target;
    setValues({
      ...values,
      [name]: value
    });
  };

  const handleSubmit = e => {
    e.preventDefault();
    setErrors(validate(values))
    setIsLogin(true)
    setIsSubmitting(true)
    
  };

  useEffect(
    () => {
      if (Object.keys(errors).length === 0 && isLogged && isSubmitting ) {
        callback();
      }
    },
  );

  function rendersuccess() {
    <Link to="/"><FormSuccess /></Link>
  }

  return  {handleChange, handleSubmit, values, errors ,rendersuccess };
    //  render*<FormSuccess />);
};

export default useForm;