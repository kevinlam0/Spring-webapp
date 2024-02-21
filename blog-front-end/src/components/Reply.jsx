import React from "react";

export const Reply = ({reply_object}) => {
    return (
        <div>
            <strong>{reply_object.name}:</strong> {reply_object.content} {reply_object.submission} {reply_object.likes}
        </div>
    )
}