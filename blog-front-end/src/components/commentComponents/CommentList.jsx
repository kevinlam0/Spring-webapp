import React from "react";
import { Comment } from "./Comment";

export const CommentList = ({ comments, handleDeleteItem, handleAddReply, 
  handleAddLike, handleUnlike, handleReplyLike, handleUnReplyLike }) => {

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
          </div>
        ))
      }
    </ul>
	)
}