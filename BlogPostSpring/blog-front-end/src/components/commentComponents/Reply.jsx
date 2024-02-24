import React, { useState } from "react";
import { useUser } from '../../context/UserContext';
import { CgProfile } from "react-icons/cg";
import { FaRegThumbsUp } from "react-icons/fa";
import { FaThumbsUp } from "react-icons/fa";

export const Reply = ({reply_object, comment_id, handleDeleteItem, handleReplyLike, handleUnReplyLike }) => {
    
    const { user } = useUser();
    const [likedBy] = useState(reply_object.likedBy || []);
    const toggleLikebutton = () => {
        setLiked(prevState => !prevState);
      };
    const [liked, setLiked] = useState(likedBy.includes(user.toLowerCase()));

    const handleLikeSubmit = (e) => {
      if (user === "Guest") {alert("You cannot like replies as a guest please sign in!")}
      else {
        e.preventDefault();
        if (!liked) { handleReplyLike(reply_object.id, user); }
        toggleLikebutton();
      }
        
    }
    
      const handleUnlikeSubmit = (e) => {
        if (user === "Guest") {alert("You cannot unlike replies as a guest please sign in!")}
        else {
          e.preventDefault();
          if (liked) { handleUnReplyLike(reply_object.id, user); }
          toggleLikebutton();
        }
      }

    return (
        <div className="reply">

          <div className='icon-reply'>
            <CgProfile className='profile-icon-comment'/>
          </div>


          <div className="non-icon-reply">

            <div className='reply-information'>
              <strong>{reply_object.name}:</strong> 
              <p>{reply_object.submission.slice(0, 10)}</p>
            </div>

            <div className='reply-content'>{reply_object.content}</div>

            <div className='comment-actions'>

              {liked && user !== "Guest" ? 
                  <FaThumbsUp className='like-icon' onClick={handleUnlikeSubmit}/>
                :
                  <FaRegThumbsUp className='like-icon' onClick={handleLikeSubmit}/>
              }

              <p>{reply_object.likes}</p>

              { reply_object.name === user  && reply_object.name !== "Guest" &&
                <button onClick={() => handleDeleteItem(reply_object.id, "reply")}>Delete Reply</button>
              }
            </div>
          </div>
        </div>
    )
}