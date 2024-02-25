import React, {useState, useRef} from 'react';
import { ReplyList } from './ReplyList';
import { useUser } from '../../context/UserContext';
import { FaRegThumbsUp } from "react-icons/fa";
import { FaThumbsUp } from "react-icons/fa";
import { CgProfile } from "react-icons/cg";
import "../../styles/ReplySection.css"
import { FiTrash2 } from "react-icons/fi";

export const Comment = ({ comment_obj, handleAddReply, handleLiking, handleUnlike, handleDeleteItem, handleReplyLike, handleUnReplyLike }) => {
    const [showReplyInput, setShowReplyInput] = useState(false);
    const { user } = useUser();
    const textareaRef = useRef(null);
    const [likedBy] = useState(comment_obj.likedBy || []);
    const [reply, setReply] = useState({
        content: "",
        name: user
    })
    const [liked, setLiked] = useState(likedBy.includes(user.toLowerCase()));
    
    const isTextAreaDisabled = reply.content.trim().length === 0;
    const defaultHeight = '31px';


    const toggleReplyInput = () => { 
        if (!showReplyInput && user === "Guest") { alert("You cannot reply as a guest. Please log in!")}
        else {
            if (showReplyInput) {setReply(prevState => ({ ...prevState, content: '' }))}
            setShowReplyInput(prevState => !prevState); }
    };
    const toggleLikebutton = () => { setLiked(prevState => !prevState); };

    const handleReplyChange = (e) => {
        const { value } = e.target;
        setReply(prevState => ({ ...prevState, content: value }))
        if (textareaRef.current) {
            textareaRef.current.style.height = `${textareaRef.current.scrollHeight}px`;
        }
    }
    const handleReplySubmit = (e) => {
        if (user === "Guest") { alert("You may not reply to comments as a guest please login first!")}
        else {
            e.preventDefault();
            handleAddReply(comment_obj.id, reply);
            setShowReplyInput(false);
            setReply(prevState => ({ ...prevState, content:"" }))
            if (textareaRef.current) {
                textareaRef.current.style.height = defaultHeight;
            }
        }
    };
    const handleKeyDown = (e) => {
        if (e.key === 'Enter') { 
            if (user === "Guest") { e.preventDefault(); }
            if (reply.content.trim().length !== 0 )handleReplySubmit(e); 
            else { e.preventDefault() }
        }
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
  if (textareaRef.current) { if (reply.content.length === 0) {textareaRef.current.style.height = defaultHeight;} }

  return (
    <div className="comment">

      <div className='icon-comment'>
        <CgProfile className='profile-icon-comment'/>
      </div>

      <div className='non-icon-comment'>

        <div className='comment-information'>
          <div className='information'>
            <strong>{comment_obj.name}:</strong> 
            <p>{comment_obj.submission.slice(0, 10)}</p>
          </div>
          <div>
          { comment_obj.name.toUpperCase() === user.toUpperCase() && comment_obj.name !== "Guest" &&
                  // <button id='delete-btn' onClick={() => handleDeleteItem(comment_obj.id, "comment")}>Delete</button>
                  <FiTrash2  id='delete-icon' style={{ cursor: 'pointer' }} onClick={() => handleDeleteItem(comment_obj.id, "comment")}/>
          }
          </div>
          

        </div>

        <div className='comment-content'>{comment_obj.content}</div>

        <div className='comment-actions'>
          
            <div className='like-portion'>
              { liked && user !== "Guest" ? 
                  <FaThumbsUp className='like-icon' onClick={handleUnlikeSubmit}/>
                :
                  <FaRegThumbsUp className='like-icon' onClick={handleLikeSubmit}/>
              }

              <p>{comment_obj.likes}</p>
              {!showReplyInput && (<button classname='action' onClick={toggleReplyInput}>Reply</button>)}
            </div>
            
            {showReplyInput && (
                <div className='reply-input-container'>
                    <CgProfile className='profile-icon-reply'/>
                    <textarea
                        ref={textareaRef}
                        autoFocus
                        type='text'
                        name='content'
                        value={reply.content}
                        onChange={handleReplyChange}
                        placeholder='Enter Reply...' 
                        onKeyDown={handleKeyDown}
                        style={{ resize: 'none', height: defaultHeight }}
                    />
                </div>
            
          )}
            {showReplyInput && 
                <div className='reply-input-actions'>
                    <button onClick={toggleReplyInput}>Cancel</button>
                    { isTextAreaDisabled ? 
                    <button id='comment-submit-off' onClick={handleReplySubmit} disabled={isTextAreaDisabled}>Reply</button>
                    :
                    <button id='comment-submit-on' onClick={handleReplySubmit} disabled={isTextAreaDisabled}>Reply</button>
                    }
                </div>
            }
        </div>
        
        <div className='replies'>
          <ReplyList 
            reply_list={comment_obj.replies} 
            handleDeleteItem={handleDeleteItem} 
            handleReplyLike={handleReplyLike} 
            comment_id={comment_obj.id}
            handleUnReplyLike={handleUnReplyLike}
          />
        </div>
      </div>
    </div>
  );
};
