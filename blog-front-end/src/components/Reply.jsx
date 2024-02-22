import React, { useState } from "react";
import { useUser } from '../context/UserContext';

export const Reply = ({reply_object, comment_id, handleDeleteItem, handleReplyLike, handleUnReplyLike }) => {
    const [liked, setLiked] = useState(false);
    const { user } = useUser();
    const toggleLikebutton = () => {
        setLiked(prevState => !prevState);
      };

    const handleLikeSubmit = (e) => {
        e.preventDefault();
        if (!liked) { handleReplyLike(comment_id, reply_object.id); }
        toggleLikebutton();
      }
    
      const handleUnlikeSubmit = (e) => {
        e.preventDefault();
        if (liked) { handleUnReplyLike(comment_id, reply_object.id); }
        toggleLikebutton();
      }

    return (
        <div>
            <strong>{reply_object.name}:</strong> {reply_object.content} {reply_object.submission.slice(0, 10)} {reply_object.likes}
            {
                liked ? 
                (<button onClick={handleUnlikeSubmit} disabled={!liked}>Unlike</button>)
                :
                (<button onClick={handleLikeSubmit} disabled={liked}>Like</button>)
            }

            { reply_object.name === user  && reply_object.name !== "Guest" &&
                <button onClick={() => handleDeleteItem(reply_object.id, "reply")}>Delete Reply</button>
            }

        </div>
    )
}