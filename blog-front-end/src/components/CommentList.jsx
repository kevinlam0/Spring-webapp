import React from "react";
import {useEffect, useState} from "react";
import { Comment } from "./Comment";

export const CommentList = () => {
	const [comments, setComments] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/blogpost/comments')
      .then(response => response.json())
      .then(data => setComments(data))
      .catch(error => console.error('Error fetching users:', error));
  }, []);

	return (
		<ul>
      {
        comments.map(comment => (
          <li key={comment.id}>
            <Comment 
              author={comment.name} 
              content={comment.content} 
              date={comment.date} 
              likes={comment.likes}
            />
          </li>
        ))
      }
    </ul>
	)
}