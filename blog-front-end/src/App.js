import './App.css';
import {useEffect, useState} from "react";
import { Header } from "./components/Header";
import { OpeningPictures } from "./components/OpeningPictures";
import { Comments } from './components/Comments';
import { CommentForm } from './components/AddComments'
function App() {

  return (
    <div>
      <Header/>
      <OpeningPictures/>
      <Comments/>
      <CommentForm/>
    </div>
  );
}

export default App;
