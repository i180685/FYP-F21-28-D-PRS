import React , {useState , useEffect} from "react";
import {db} from '../../firebase'
import { collection ,query , onSnapshot ,doc, deleteDoc, updateDoc} from "firebase/firestore";
import 'react-pro-sidebar/dist/css/styles.css';
import AddAdmin from "./addAdmin";
import AdminTable from "./adminTable";
import AdminData from "./adminData";

function AdminProfiles() {
  
    let [compBtn , setCompBtn] = useState(false);
    let [edtBtn , setEdtBtn] = useState(false);
    const [admin , setAdmins] = useState([])
   
    useEffect(() => {
        const q = query(collection(db , "admin"));
        const ss = onSnapshot(q ,  querySnapshot => {
            let arr = []
            querySnapshot.forEach(doc => {
                arr.push( {...doc.data(), id:doc.id });
            });
            setAdmins(arr);
        });
        return () => ss();
    } ,[]); 

      const handleDelete = async (id) => {
        await deleteDoc(doc(db, "admin", id));
      };

      let [em,setEm] =useState("");
      let [ps,setPs] =useState("");
      let [idd,setIdd] =useState("");
      var iddd;

      function editBtn (admin,id)  {
        setCompBtn(false);
        setEm(admin.email);
        setPs(admin.password); 
        setIdd(id);
        iddd = id;
        setEdtBtn(true);
      }

      const toggleComplete = async (idd) => {
      //   alert(idd + "     " + em + "   " + ps) 
          await updateDoc(doc(db, "admin", idd), { 
              email: em ,  
              password: ps
          });
        };

      function AddBtn() {
        setCompBtn(true);
        setEdtBtn(false);
      }

    return (

      <div>
      <div className="headings">
            ADMINS
        </div>
      <div class="table-wrapper">
        <table class="fl-table">       
            <thead>
                <tr>
                    <th>Email</th>
                    <th>Password</th>
                    <th></th>
                </tr>

            </thead>
        </table>
        {
          admin.map((admin) => (
              <AdminTable
                key={admin.id}
                admin={admin}
                editBtn={editBtn}
                handleDelete={handleDelete}
              />
          ))
        }
      </div>
            <button className="editButton" onClick={() => AddBtn() }>Add an Admin</button>
            {
              compBtn ? <AddAdmin /> : ""
            }
            {
              edtBtn ? <AdminData 
                email={em}
                password= {ps}
                id={idd}
                updateBtn={toggleComplete}
              /> : ""
            }
        </div>
    )
}

export default AdminProfiles;

