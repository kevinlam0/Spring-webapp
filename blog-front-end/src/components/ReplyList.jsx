import { Reply } from "./Reply";

export const ReplyList = ({reply_list}) => {
    return (
        <ul>
            { reply_list.map((reply) => ( <Reply reply_object={reply}/> )) }
        </ul>
        
    )
}