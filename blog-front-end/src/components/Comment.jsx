import React, {useState} from 'react';
import { ReplyList } from './ReplyList';
import { useUser } from '../context/UserContext';

export const Comment = ({ comment_obj, handleAddReply, handleLiking, handleUnlike, handleDeleteItem, handleReplyLike, handleUnReplyLike }) => {
  const [showReplyInput, setShowReplyInput] = useState(false);
  const { user } = useUser();
  const [likedBy, setLikedBy] = useState(comment_obj.likedBy || []);
  const [reply, setReply] = useState({
    content: "",
    name: user
  })
  const [liked, setLiked] = useState(likedBy.includes(user.toLowerCase()));
  
  const isTextAreaDisabled = reply.content.trim().length === 0;

  const toggleReplyInput = () => { 
    if (user === "Guest") { alert("You may not reply to comments as a guest please login first!")}
    else {setShowReplyInput(prevState => !prevState); }
  };

  const toggleLikebutton = () => { setLiked(prevState => !prevState); };

  const handleReplyChange = (e) => {
    const { value } = e.target;
    setReply(prevState => ({ ...prevState, ['content']: value }))
}

const handleKeyDown = (e) => {
  if (e.key === 'Enter') {
    if (user === "Guest") { alert("You may not reply to comments as a guest please login first!")}
    else {
      e.preventDefault();
      handleAddReply(comment_obj.id, reply);
      setShowReplyInput(false);
      setReply(prevState => ({ ...prevState, ['content']: '' }))
    }
  }
}

  const handleReplySubmit = (e) => {
    if (user === "Guest") { alert("You may not reply to comments as a guest please login first!")}
    else {
      e.preventDefault();
      handleAddReply(comment_obj.id, reply);
      setShowReplyInput(false);
      setReply(prevState => ({ ...prevState, ['content']: '' }))
    }
  };

  const handleLikeSubmit = (e) => {
    if (user === "Guest") { alert("You may not like comments as a guest please login first!")}
    else {
      e.preventDefault();
      if (!liked) { handleLiking(comment_obj.id, user); }
      toggleLikebutton();
    }
  }

  const handleUnlikeSubmit = (e) => {
    if (user === "Guest") { alert("You may not unlike comments as a guest please login first!")}
    else { 
      e.preventDefault();
      if (liked) { handleUnlike(comment_obj.id, user); }
      toggleLikebutton();
    }
  }

  return (
    <div className="comment">
      <strong>{comment_obj.name}:</strong> {comment_obj.content} {comment_obj.submission.slice(0, 10)} {comment_obj.likes}
      {
        liked && user !== "Guest"? 
          (<button onClick={handleUnlikeSubmit} >Unlike</button>)
        :
          (<button onClick={handleLikeSubmit} >Like</button>)
      }

      { comment_obj.name.toUpperCase() === user.toUpperCase() && comment_obj.name !== "Guest" &&
        <button onClick={() => handleDeleteItem(comment_obj.id, "comment")}>Delete Comment</button>
      }

      {showReplyInput && (
        <div>
          <input type="text" autoFocus value={reply.content} onChange={handleReplyChange} onKeyDown={handleKeyDown}/>
          <button onClick={handleReplySubmit} disabled={isTextAreaDisabled}>Submit Reply</button>
          <button onClick={toggleReplyInput}>Cancel</button>
        </div>
      )}
      
      {!showReplyInput && (<button onClick={toggleReplyInput}>Reply</button>)}
      <ReplyList 
        reply_list={comment_obj.replies} 
        handleDeleteItem={handleDeleteItem} 
        handleReplyLike={handleReplyLike} 
        comment_id={comment_obj.id}
        handleUnReplyLike={handleUnReplyLike}
      />
    </div>
  );
};
