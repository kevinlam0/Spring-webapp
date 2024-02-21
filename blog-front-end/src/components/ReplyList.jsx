import React, { useEffect } from "react";
import { Reply } from "./Reply";

export const ReplyList = ({reply_list}) => {
    // useEffect(() => {
    //     fetch('http://localhost:8080/blogpost/comments')
    //       .then(response => response.json())
    //       .then(data => setComments(data))
    //       .catch(error => console.error('Error fetching users:', error));
    //   }, []);
    return (
        <ul>
            { reply_list.map((reply) => ( <Reply reply_object={reply}/>)) }
        </ul>
        
    )
}