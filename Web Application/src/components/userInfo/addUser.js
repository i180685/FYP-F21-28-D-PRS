import { useState } from "react";
import { db } from "../../firebase";
import { collection, addDoc } from "firebase/firestore";
import "./userInfo.css"

const AddUser = () => {
    
  const [name , setName] = useState("")
  const [email , setEmail] = useState("")
  const [rollNo , setRollNo] = useState("")
  const [mobileNo , setMobileNo] = useState("")
  const [password , setPassword] = useState("")
  const [carName , setcarName] = useState("")
  const [carComp , setcarComp] = useState("")
  const [carNoplate , setcarNoplate] = useState("")
  const [spot , setSpot] = useState("")
  const [status , setStatus] = useState("")

  const handleSubmit = async (e) => {
    e.preventDefault();
    await addDoc(collection(db, "users"), {
      name,
      email,
      rollNo,
      mobileNo,
      password,
      carName,
      carComp,
      carNoplate,
      spot,
      status
    });
    setName('');
    setEmail('');
    setRollNo('');
    setMobileNo('');
    setPassword('');
    setcarName('');
    setcarComp('');
    setcarNoplate('');
    setSpot('');
    setStatus(0);
  };

  return (
      <div className="m-5">
    <form onSubmit={handleSubmit}>

      <div className="headings">
        ADD USER 
      </div>

        <div className='form-inputs'>
          <input
            className='form-input'
            type='text'
            name='name'
            placeholder='Enter user`s username'
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>

        <div className='form-inputs'>
          <input
            className='form-input'
            type='email'
            name='email'
            placeholder='Enter your email'
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        <div className='form-inputs'>
          <input
            className='form-input'
            type='text'
            name='rollNo'
            placeholder='Enter user`s rollNo in format :ixxxxxx'
            pattern="[i][0-9]{6}"
            value={rollNo}
            onChange={(e) => setRollNo(e.target.value)}
          />
        </div>

        <div className='form-inputs'>
          <input
            className='form-input'
            type='text'
            maxLength={12}
            name='mobileNo'
            placeholder='Enter user`s mobileNo in 03xx-xxxxxxx'
            pattern="[03]{2}[0-9]{2}[-][0-9]{7}"
            value={mobileNo}
            onChange={(e) => setMobileNo(e.target.value)}
          />
        </div>

        <div className='form-inputs'>
          <input
            className='form-input'
            type='text'
            name='password'
            placeholder='Enter user`s password'
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>

        <div className='form-inputs'>
          <input
            className='form-input'
            type='text'
            name='carName'
            placeholder='Enter user`s car Name'
            value={carName}
            onChange={(e) => setcarName(e.target.value)}
          />
        </div>

        <div className='form-inputs'>
          <input
            className='form-input'
            type='text'
            name='carComp'
            placeholder='Enter user`s car Company'
            value={carComp}
            onChange={(e) => setcarComp(e.target.value)}
          />
        </div>

        <div className='form-inputs'>
          <input
            className='form-input'
            type='text'
            name='carNoplate'
            placeholder='Enter user`s car`s License plate'
            value={carNoplate}
            onChange={(e) => setcarNoplate(e.target.value)}
          />
        </div>

        <div className='form-inputs'>
          <input
            className='form-input'
            type='text'
            name='spot'
            placeholder='Enter user`s spot'
            value={spot}
            onChange={(e) => setSpot(e.target.value)}
          />
        </div>

      <div >
        <button className="editButton">Add User</button>
      </div>
    </form>
    </div>
  );
};

export default AddUser;


/*
<fieldset >
          <legend className='form-label'></legend>
            <label></label>
            <input
              className='form-input-batch'
              type='number'
              name='batch'
              placeholder='Batch'
              value={batch}
              onChange={(e) => setBatch(e.target.value)}
            />
              <div className='campus'> - i -</div>
              <label></label>
            <input
              className='form-input-id'
              type='number'
              name='rollNo'
              placeholder='rollNo'
              value={rollNo}
              onChange={(e) => setRollNo(e.target.value)}
            />
          </fieldset>
*/