import { Reply } from "./Reply";

export const ReplyList = ({reply_list, handleDeleteItem, handleReplyLike, handleUnReplyLike}) => {
    return (
        <ul>
            { 
                reply_list.map((reply) => ( 
                    <div key={reply.id}>
                        <li>
                            <Reply 
                                reply_object={reply} 
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