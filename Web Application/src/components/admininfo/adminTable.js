import React from "react";

const AdminTable = ({ admin, editBtn, handleDelete }) => {

  return (
    <div>        
        <table class="fl-table">
            <tbody>
              <tr>
                  <td>{admin.email}</td>
                  <td>{admin.password}</td>
                  <td>
                      <button className="editButton" onClick={() => editBtn(admin,admin.id)}>Edit</button> 
                      <button className="deleteButton" onClick={() => handleDelete(admin.id)}>Delete</button>
                  </td>
              </tr>
            </tbody>
        
        </table>
      
    </div>
  );
};

export default AdminTable;