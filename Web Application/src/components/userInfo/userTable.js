import React from "react";
import { NavLink, Redirect } from "react-router-dom";
import "./userInfo.css"
import UserData from "../../screens/userData"
import { contains } from "@firebase/util";

const UserTable = ({ user, editBtn, handleDelete }) => {

  const  editBttn = async (user,id)  => {
    return(<>
      <h1>BOOOOOO</h1>
      console.log('bro');
      </>
      )
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
                      <button className="editButton" onClick={() => editBtn(user,user.id)}>Edit</button> 
                      <button className="deleteButton" onClick={() => handleDelete(user.id)}>Delete</button>
                  </td>
              </tr>
            </tbody>
        
        </table>
      
    </div>
  );
};

export default UserTable;