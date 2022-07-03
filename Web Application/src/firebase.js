// import {initializeApp,getApps,getApp} from "firebase/app";
// import {getFirestore} from "firebase/firestore"


// // TODO: Add SDKs for Firebase products that you want to use
// // https://firebase.google.com/docs/web/setup#available-libraries

// // Your web app's Firebase configuration
// const firebaseConfig = {
//   apiKey: "AIzaSyB49kxkNyhckDsLMQ9768EVfM8pgkZadEc",
//   authDomain: "fyp-prs.firebaseapp.com",
//   projectId: "fyp-prs",
//   storageBucket: "fyp-prs.appspot.com",
//   messagingSenderId: "491902969494",
//   appId: "1:491902969494:web:09981d37a4cace6da9c4b7"
// };

// Initialize Firebase
//const app = initializeApp(firebaseConfig);
//let db;
//if (getApps.length === 0 ){ db = getFirestore(initializeApp(firebaseConfig))} else  getApp();
//const db = getFirestore(initializeApp(firebaseConfig));
//export {db};

//export {db};


// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getFirestore } from "firebase/firestore";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyB49kxkNyhckDsLMQ9768EVfM8pgkZadEc",
  authDomain: "fyp-prs.firebaseapp.com",
  projectId: "fyp-prs",
  storageBucket: "fyp-prs.appspot.com",
  messagingSenderId: "491902969494",
  appId: "1:491902969494:web:09981d37a4cace6da9c4b7",
  measurementId: "G-1KJPVGF39Z"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
//const analytics = getAnalytics(app);
const db = getFirestore(app);
export {db};
//old firebase
// const firebaseConfig = {
//   apiKey: "AIzaSyCLK3Hkog4teXYHohfbZtmngHEMUb1r8-c",
//   authDomain: "f21-28-d-prs.firebaseapp.com",
//   projectId: "f21-28-d-prs",
//   storageBucket: "f21-28-d-prs.appspot.com",
//   messagingSenderId: "656155363458",
//   appId: "1:656155363458:web:1e4df176967e9f6ca7cc9b"
// };