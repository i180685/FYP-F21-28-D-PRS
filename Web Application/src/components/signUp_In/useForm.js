import { useState, useEffect } from 'react';
import { Link, Redirect } from 'react-router-dom';
import {db} from '../../firebase'
import { collection ,query , onSnapshot ,doc, updateDoc, deleteDoc,} from "firebase/firestore";

const useForm = (callback, validate) => {

  const [values, setValues] = useState({
    email: '',
    password: ''
  });

  const [errors, setErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [isLogged, setIsLogin] = useState(false);
  const [isAuthorized, setIsAuthorized] = useState(false);

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
    checkCredentials(email , password)
    setIsLogin(true)
    setIsSubmitting(true)
  };

  const checkCredentials = (email,password) => {
    if(email === values.email && password === values.password) {
      setIsAuthorized(true)
    }

  }

 var [incorrect,setIncorrect] = useState(false);
  var [rendersuccess , setRS] = useState(false);
  useEffect(
    () => {
      if (Object.keys(errors).length === 0 && isLogged && isSubmitting && isAuthorized) {
        setRS(true);
      } else if (Object.keys(errors).length === 0 && isLogged && isSubmitting && !isAuthorized) {
        setIncorrect(true)
      }
    },
  );

  const [admin , setAdmin] = useState([])
  useEffect(() => {
    const q = query(collection(db , "admin"));
    const ss = onSnapshot(q ,  querySnapshot => {
        let arr = []
        querySnapshot.forEach(doc => {
            arr.push( {...doc.data(), id:doc.id });
        });
        setAdmin(arr);
    });
    return () => ss();
} ,[]); 

  var email;
  var password;

  admin.map((adm) => (
    email = adm.email ,
    password = adm.password
  ))


  return  {handleChange, handleSubmit, values, errors ,rendersuccess ,incorrect};
    //  render*<FormSuccess />);
};

export default useForm;