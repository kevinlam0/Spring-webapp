import './App.css';
import {useEffect, useState} from "react";

// async renderComments() {
//     const response = await fetch('/blogpost/comments');
//
// }
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
        <h2>Something</h2>
      <ul>
        {comments.map(comment => (
            <li key={comment.id}>{comment.comment}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;
