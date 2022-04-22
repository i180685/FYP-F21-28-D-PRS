import { useState } from "react";
import { db } from "../../firebase";
import { collection, addDoc } from "firebase/firestore";

const AddAdmin = () => {
    
  const [email , setEmail] = useState("")
  const [password , setPassword] = useState("")

  const handleSubmit = async (e) => {
    e.preventDefault();
    await addDoc(collection(db, "admin"), {
   
      email,
      password
    });
    setEmail('');
    setPassword('');
  };

  return (
      <div className="m-5">
        <form onSubmit={handleSubmit}>
            <div className="headings">
                ADD Admin 
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
                    name='password'
                    placeholder='Enter user`s password'
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
            </div>
            <div >
                <button className="editButton">Add User</button>
            </div>
        </form>
    </div>
  );
};

export default AddAdmin;