import { Reply } from "./Reply";

export const ReplyList = ({reply_list, handleDeleteItem}) => {
    return (
        <ul>
            { 
                reply_list.map((reply) => ( 
                    <div key={reply.id}>
                        <li>
                            <Reply reply_object={reply}/>
                        </li>
                        <button onClick={() => handleDeleteItem(reply.id, "reply")}>Delete Reply</button>
                    </div>
                )) 
            }
        </ul>
        
    )
}