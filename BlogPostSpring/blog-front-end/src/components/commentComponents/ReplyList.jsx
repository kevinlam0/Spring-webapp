import { Reply } from "./Reply";

export const ReplyList = ({reply_list, handleDeleteItem, handleReplyLike, handleUnReplyLike}) => {
    return (
        <ul>
            { 
            reply_list.map((reply) => ( 
                <li key={reply.id}>
                    <div className="reply-container">
                    <Reply 
                        reply_object={reply} 
                        handleDeleteItem={handleDeleteItem} 
                        handleReplyLike={handleReplyLike}
                        handleUnReplyLike={handleUnReplyLike}
                    />
                    </div>
                </li>
            )) 
            }
        </ul>
        
    )
}