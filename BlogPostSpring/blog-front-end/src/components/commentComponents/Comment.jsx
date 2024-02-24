import React, {useState} from 'react';
import { ReplyList } from './ReplyList';
import { useUser } from '../../context/UserContext';
import { FaRegThumbsUp } from "react-icons/fa";
import { FaThumbsUp } from "react-icons/fa";
import { CgProfile } from "react-icons/cg";

export const Comment = ({ comment_obj, handleAddReply, handleLiking, handleUnlike, handleDeleteItem, handleReplyLike, handleUnReplyLike }) => {
  const [showReplyInput, setShowReplyInput] = useState(false);
  const { user } = useUser();
  const [likedBy] = useState(comment_obj.likedBy || []);
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
    setReply(prevState => ({ ...prevState, content: value }))
}
  const handleReplySubmit = (e) => {
    if (user === "Guest") { alert("You may not reply to comments as a guest please login first!")}
    else {
      e.preventDefault();
      handleAddReply(comment_obj.id, reply);
      setShowReplyInput(false);
      setReply(prevState => ({ ...prevState, content: '' }))
    }
  };
  const handleKeyDown = (e) => {
    if (e.key === 'Enter') { handleReplySubmit(e); }
  }
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
      <CgProfile className='profile-icon-comment'/>
      <div className='non-icon-comment'>
      <div className='comment-information'>
        <strong>{comment_obj.name}:</strong> 
        <p>{comment_obj.submission.slice(0, 10)}</p>
      </div>
      <div className='comment-content'>{comment_obj.content}</div>
      <div className='comment-actions'>
        {
          liked && user !== "Guest"? 
            <FaThumbsUp className='like-icon' onClick={handleUnlikeSubmit}/>
          :
            <FaRegThumbsUp className='like-icon' onClick={handleLikeSubmit}/>
        }
        <p>{comment_obj.likes}</p>
        {!showReplyInput && (<button classname='action' onClick={toggleReplyInput}>Reply</button>)}
        {showReplyInput && (
          <div>
            <input type="text" autoFocus value={reply.content} onChange={handleReplyChange} onKeyDown={handleKeyDown}/>
            <button onClick={handleReplySubmit} disabled={isTextAreaDisabled}>Submit Reply</button>
            <button onClick={toggleReplyInput}>Cancel</button>
          </div>
        )}
        { comment_obj.name.toUpperCase() === user.toUpperCase() && comment_obj.name !== "Guest" &&
          <button onClick={() => handleDeleteItem(comment_obj.id, "comment")}>Delete Comment</button>
      }
      </div>
      <ReplyList 
        reply_list={comment_obj.replies} 
        handleDeleteItem={handleDeleteItem} 
        handleReplyLike={handleReplyLike} 
        comment_id={comment_obj.id}
        handleUnReplyLike={handleUnReplyLike}
      />
      </div>
    </div>
  );
};
