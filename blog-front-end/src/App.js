import './App.css';
import {useEffect, useState} from "react";
import { Header } from "./components/Header";
import { OpeningPictures } from "./components/OpeningPictures";
function App() {
  const [comments, setComments] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/blogpost/comments')
      .then(response => response.json())
      .then(data => setComments(data))
      .catch(error => console.error('Error fetching users:', error));
  }, []);

  return (
    <div>
      <Header/>
      <OpeningPictures/>
      <ul>
        {comments.map(comment => (
            <li key={comment.id}>{comment.comment}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;
