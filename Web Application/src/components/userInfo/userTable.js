import React from "react";

const UserTable = ({ user, editBtn, handleDelete }) => {

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