import React, { useState } from "react";
import { useUser } from '../../context/UserContext';

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
        <div>
            <strong>{reply_object.name}:</strong> {reply_object.content} {reply_object.submission.slice(0, 10)} {reply_object.likes}
            {
                liked && user !== "Guest"? 
                (<button onClick={handleUnlikeSubmit} >Unlike</button>)
                :
                (<button onClick={handleLikeSubmit} >Like</button>)
            }

            { reply_object.name === user  && reply_object.name !== "Guest" &&
                <button onClick={() => handleDeleteItem(reply_object.id, "reply")}>Delete Reply</button>
            }

        </div>
    )
}