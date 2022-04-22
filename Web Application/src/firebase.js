import {initializeApp} from "firebase/app";
import {getFirestore} from "firebase/firestore"


// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyCLK3Hkog4teXYHohfbZtmngHEMUb1r8-c",
  authDomain: "f21-28-d-prs.firebaseapp.com",
  projectId: "f21-28-d-prs",
  storageBucket: "f21-28-d-prs.appspot.com",
  messagingSenderId: "656155363458",
  appId: "1:656155363458:web:1e4df176967e9f6ca7cc9b"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const db = getFirestore(app);
export {db};