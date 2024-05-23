// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getStorage } from 'firebase/storage';
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyDrjiW1VeHuOEFNrtWt0JJoko4563wslxY",
  authDomain: "swapit-eb176.firebaseapp.com",
  projectId: "swapit-eb176",
  storageBucket: "swapit-eb176.appspot.com",
  messagingSenderId: "1017429494098",
  appId: "1:1017429494098:web:a274067dff07b3bbd2d28b",
  measurementId: "G-Y2GYZVDREE"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const storage = getStorage(app);