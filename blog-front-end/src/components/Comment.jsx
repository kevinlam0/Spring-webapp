import React, {useState} from 'react';
import { ReplyList } from './ReplyList';

export const Comment = ({ comment_obj, handleAddReply }) => {
  const [showReplyInput, setShowReplyInput] = useState(false);
  // const [replyContent, setReplyContent] = useState('');
  const [reply, setReply] = useState({
    content: "",
    name: "anonymous"
})

  const toggleReplyInput = () => {
    setShowReplyInput(prevState => !prevState);
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

  return (
    <div className="comment">
      <strong>{comment_obj.name}:</strong> {comment_obj.content} {comment_obj.submission} {comment_obj.likes}
      {showReplyInput && (
        <div>
          <input type="text" value={reply.content} onChange={handleReplyChange} />
          <button onClick={handleReplySubmit}>Submit Reply</button>
          <button onClick={toggleReplyInput}>Cancel</button>
        </div>
      )}
      {/* <button onClick={() => deleteItem(comment_obj.id, "comment")}>Delete</button> */}
      {!showReplyInput && (<button onClick={toggleReplyInput}>Reply</button>)}
      <ReplyList reply_list={comment_obj.replies}/>
    </div>
  );
};
