import React from "react";
import {useEffect, useState} from "react";

export const Comments = () => {
	const [comments, setComments] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/blogpost/comments')
      .then(response => response.json())
      .then(data => setComments(data))
      .catch(error => console.error('Error fetching users:', error));
  }, []);

	return (
		<ul>
			{comments.map(comment => ( <li key={comment.id}>{comment.comment}</li> ))}
    </ul>
	)
}