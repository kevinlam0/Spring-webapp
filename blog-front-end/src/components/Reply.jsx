import React from "react";

export const Reply = ({reply_object}) => {
    return (
        <div>
            <strong>{reply_object.name}:</strong> {reply_object.content} {reply_object.submission.slice(0, 10)} {reply_object.likes}
        </div>
    )
}