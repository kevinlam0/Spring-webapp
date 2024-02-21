import React from 'react';
import { ReplyList } from './ReplyList';

export const Comment = ({ comment_obj }) => {

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
    <div className="comment">
      <strong>{comment_obj.name}:</strong> {comment_obj.content} {comment_obj.submission} {comment_obj.likes}
      {/* <button onClick={() => deleteItem(comment_obj.id, "comment")}>Delete</button> */}
      <ReplyList reply_list={comment_obj.replies}/>
    </div>
  );
};
