import React, { useState, useEffect } from "react";
import { CommentList } from "./CommentList";
import { CommentForm } from "./CommentForm";
import { useCommentActions } from "../../hooks/useCommentActions";
import { useUser } from "../../context/UserContext";

export const CommentSection = () => {
    const [commentData, setCommentData] = useState([]);
    const { addComment, deleteItem, addReply, addLike, unlike, addReplyLike, unReplyLike } = useCommentActions();
    const [addCommentMode, setAddCommentMode] = useState(false);
    const { user } = useUser();

    const fetchData = async () => {
        await fetch('http://localhost:8080/blogpost/comments')
          .then(response => response.json())
          .then(data => setCommentData(data))
          .catch(error => console.error('Error fetching comments:', error));
    }

    useEffect(() => { fetchData(); }, []);

    const handleAddComment = async (comment) => {
        await addComment(comment);
        fetchData();
    }
    const handleDeleteItem = async (itemId, itemType) => {
        await deleteItem(itemId, itemType);
        fetchData();
    }
    const handleAddReply = async (comment_id, reply) => {
        await addReply(comment_id, reply);
        fetchData();
    }
    const handleAddLike = async (comment_id, username) => {
        await addLike(comment_id, username);
        fetchData();
    }
    const handleUnlike = async (comment_id, username) => {
        await unlike(comment_id, username);
        fetchData();
    }
    const handleReplyLike = async (reply_id, username) => {
        await addReplyLike(reply_id, username);
        fetchData();
    }
    const handleUnReplyLike = async (reply_id, username) => {
        await unReplyLike(reply_id, username);
        fetchData();
    }
    const toggleAddComment = () => {
        if (user === "Guest") {alert("You cannot add comments as a guest. Please log in!")}
        else {setAddCommentMode(!addCommentMode);}
    }

    return (
        <>
            <CommentForm handleAddComment ={handleAddComment} />
            {/* {
                addCommentMode && user !== "Guest" ?
                    <div>
                        <CommentForm handleAddComment ={handleAddComment} />
                        <button onClick={toggleAddComment}>Cancel</button>
                    </div>
                :
                    <button id='comment-main-btn' onClick={toggleAddComment}>Add comment</button>
            } */}
            <CommentList 
                comments={commentData}
                handleDeleteItem={handleDeleteItem}
                handleAddReply={handleAddReply}
                handleAddLike={handleAddLike}
                handleUnlike={handleUnlike}
                handleReplyLike={handleReplyLike}
                handleUnReplyLike={handleUnReplyLike}
            />
        </>
    )
}