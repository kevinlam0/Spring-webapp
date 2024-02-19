import './App.css';
import {useEffect, useState} from "react";
import { Header } from "./components/Header";
import { OpeningPictures } from "./components/OpeningPictures";
import { Comments } from './components/Comments';
function App() {

  return (
    <div>
      <Header/>
      <OpeningPictures/>
      <Comments/>
    </div>
  );
}

export default App;
