import React from "react";
import {useEffect, useState} from "react";
import { Comment } from "./Comment";

export const CommentList = () => {
	const [comments, setComments] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/blogpost/comments')
      .then(response => response.json())
      .then(data => setComments(data))
      .catch(error => console.error('Error fetching comments:', error));
  }, []);

  // const deleteItem = async (itemId, itemType) => {
  //   try {
  //     const response = await fetch('http://localhost:8080/blogpost/comments', {
  //       method: 'DELETE',
  //       headers: { 'Content-Type': 'application/json' },
  //       body: JSON.stringify({ id: itemId, type: itemType }) 
  //     });

  //     if (!response.ok) { throw new Error('Network response was not ok'); }
  //     console.log("Comment Deletion successful");
  //   }
  //   catch(error) {console.error('Error deleting item: ', error);};
  // }

	return (
		<ul>
      {
        comments.map(comment => (
          <div key={comment.id}>
            <li >
              <Comment 
                comment_obj = {comment}
              />
            </li>
          </div>
        ))
      }
    </ul>
	)
}