import React, { useState } from "react";
import UserData from "../../screens/userData";

const UserTable = ({ user, editBtn, handleDelete }) => {

  let [infobool,setInfobool] = useState(false);
  function infoBtn () {
    infobool ? setInfobool(false) : setInfobool(true);
  }

  return (
    <div>        
        <table class="fl-table">
            <tbody>
              <tr>
                  <td>{user.name}</td>
                  <td>{user.rollNo}</td>
                  <td>{user.email}</td>
                  <td>{user.mobileNo}</td>
                  <td>
                      {/* <button className="editButton" onClick={() => editBtn(user,user.id)}>Edit</button>  */}
                      <button className="editButton" onClick={() => infoBtn()}>Info</button> 
                      <button className="deleteButton" onClick={() => handleDelete(user.id)}>Delete</button>
                  </td>
              </tr>
            </tbody>
        
        </table>
        {infobool ? 
              <UserData  
                name={user.name} 
                email={user.email}
                password= {user.password}
                rollNo= {user.rollNo}
                mobileNo= {user.mobileNo}
                carName= {user.carName}
                carComp= {user.carComp}
                carNoplate= {user.carNoplate}
                spot={user.spot}
                status={user.status}
              /> 
              : null}
        
    </div>
  );
};

export default UserTable;