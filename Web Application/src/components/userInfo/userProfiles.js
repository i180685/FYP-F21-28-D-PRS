import React , {useState , useEffect} from "react";
import {db} from '../../firebase'
import { collection ,query , onSnapshot ,doc, updateDoc, deleteDoc,} from "firebase/firestore";
import "./userInfo.css"
import 'react-pro-sidebar/dist/css/styles.css';
import AddUser from "./addUser";
import UserTable from "./userTable";
import UserData from "../../screens/userData";

function UserProfiles() {
  
    let [compBtn , setCompBtn] = useState(false);
    let [edtBtn , setEdtBtn] = useState(false);
    const [users , setUsers] = useState([])
   
    useEffect(() => {
        const q = query(collection(db , "users"));
        const ss = onSnapshot(q ,  querySnapshot => {
            let arr = []
            querySnapshot.forEach(doc => {
                arr.push( {...doc.data(), id:doc.id });
            });
            setUsers(arr);
        });
        return () => ss();
    } ,[]); 

    const toggleComplete = async (user) => {
        await updateDoc(doc(db, "users", user.id), { 
            name: user.name,
            email: user.email,
            password: user.password,
            rollNo: user.rollNo,
            mobileNo: user.mobileNo,
            carName: user.carName,
            carComp: user.carComp,
            carNoplate: user.carNoplate, 
        });
      };

      const handleDelete = async (id) => {
        await deleteDoc(doc(db, "users", id));
        alert("User Deleted");
      };

      let [nam , setNam] = useState("");
      let [em,setEm] =useState("");
      let [rl,setRl] =useState("");
      let [ps,setPs] =useState("");
      let [mn,setMn] =useState("");
      let [cr,setCr] =useState("");
      let [crc,setCrc] =useState("");
      let [cno,setCno] =useState("");
      let [idd,setIdd] =useState("");
      let [spt,setSpt] =useState("");

      function editBtn (user,id)  {
        setCompBtn(false);
        setNam( user.name);
        setEm(user.email);
        setPs(user.password);
        setRl(user.rollNo);
        setMn( user.mobileNo);
        setCr( user.carName);
        setCrc( user.carComp);
        setCno(user.carNoplate);
        setIdd(id);
        setSpt(user.spot)
        setEdtBtn(true);
      }

      function AddBtn() {
        setCompBtn(true);
        setEdtBtn(false);
      }

    return (

             <div>
              <div className="headings">
                    USER'S PERSONAL DATA
                </div>
             <div class="table-wrapper">
                <table class="fl-table">       
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Roll NO.</th>
                            <th>Email</th>
                            <th>Mobile No.</th>
                            <th></th>
                        </tr>
        
                    </thead>
                </table>
        {users.map((user) => (
     <>
          <UserTable
            key={user.id}
            user={user}
            editBtn={editBtn}
            handleDelete={handleDelete}
          />
        </>))}
            </div>
            <button className="editButton" onClick={() => AddBtn() }>Add a User</button>
            {
              compBtn ? <AddUser /> : ""
            }
            {
              edtBtn ? <UserData 
                name={nam} 
                email={em}
                password= {ps}
                rollNo= {rl}
                mobileNo= {mn}
                carName= {cr}
                carComp= {crc}
                carNoplate= {cno}
                id={idd}
                spot={spt}
              /> : ""
            }
        </div>
    )
}

export default UserProfiles;

