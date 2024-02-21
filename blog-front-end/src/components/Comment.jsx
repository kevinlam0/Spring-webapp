import React, {useState} from 'react';
import { ReplyList } from './ReplyList';

export const Comment = ({ comment_obj, handleAddReply, handleLiking, handleUnlike }) => {
  const [showReplyInput, setShowReplyInput] = useState(false);
  const [reply, setReply] = useState({
    content: "",
    name: "anonymous"
  })
  const [liked, setLiked] = useState(false);

  const isTextAreaDisabled = reply.content.trim().length === 0;

  const toggleReplyInput = () => {
    setShowReplyInput(prevState => !prevState);
  };

  const toggleLikebutton = () => {
    setLiked(prevState => !prevState);
  };

  const handleReplyChange = (e) => {
    const { value } = e.target;
    setReply(prevState => ({ ...prevState, ['content']: value }))
}

  const handleReplySubmit = (e) => {
    e.preventDefault();
    handleAddReply(comment_obj.id, reply);
    setShowReplyInput(false);
    setReply(prevState => ({ ...prevState, ['content']: '' }))
  };

  const handleLikeSubmit = (e) => {
    e.preventDefault();
    if (!liked) { handleLiking(comment_obj.id); }
    toggleLikebutton();
  }

  const handleUnlikeSubmit = (e) => {
    e.preventDefault();
    if (liked) { handleUnlike(comment_obj.id); }
    toggleLikebutton();
  }

  return (
    <div className="comment">
      <strong>{comment_obj.name}:</strong> {comment_obj.content} {comment_obj.submission.slice(0, 10)} {comment_obj.likes}
      {
        liked ? 
          (<button onClick={handleUnlikeSubmit} disabled={!liked}>Unlike</button>)
        :
          (<button onClick={handleLikeSubmit} disabled={liked}>Like</button>)
      }
      {showReplyInput && (
        <div>
          <input type="text" value={reply.content} onChange={handleReplyChange} />
          <button onClick={handleReplySubmit} disabled={isTextAreaDisabled}>Submit Reply</button>
          <button onClick={toggleReplyInput}>Cancel</button>
        </div>
      )}
      {!showReplyInput && (<button onClick={toggleReplyInput}>Reply</button>)}
      <ReplyList reply_list={comment_obj.replies}/>
    </div>
  );
};
