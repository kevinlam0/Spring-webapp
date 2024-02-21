import React from "react";
import { Comment } from "./Comment";

export const CommentList = ({ comments, handleDeleteItem, handleAddReply, handleAddLike, handleUnlike, handleReplyLike, handleUnReplyLike }) => {
	// const [comments, setComments] = useState([]);

  // useEffect(() => {
  //   fetch('http://localhost:8080/blogpost/comments')
  //     .then(response => response.json())
  //     .then(data => setComments(data))
  //     .catch(error => console.error('Error fetching comments:', error));
  // }, []);

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

    // const updatedComments = comments.filter(comment => comment.id !== itemId);
    // setComments(updatedComments);
  // }

	return (
		<ul>
      {
        comments.map(comment => (
          <div key={comment.id}>
            <li >
              <Comment 
                comment_obj = {comment}
                handleAddReply={handleAddReply}
                handleLiking={handleAddLike}
                handleUnlike={handleUnlike}
                handleDeleteItem={handleDeleteItem}
                handleReplyLike={handleReplyLike}
                handleUnReplyLike={handleUnReplyLike}
              />
            </li>
            {/* <button onClick={() => handleDeleteItem(comment.id, "comment")}>Delete Comment</button> */}
            {/* <button onClick={() => handleAddReply(comment.id, "comment")}>Reply</button> */}
          </div>
        ))
      }
    </ul>
	)
}