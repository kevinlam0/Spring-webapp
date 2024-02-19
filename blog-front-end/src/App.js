import './App.css';
import {useEffect, useState} from "react";
import { Header } from "./components/Header";
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
      <ul>
        {comments.map(comment => (
            <li key={comment.id}>{comment.comment}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;
