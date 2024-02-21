import React, { useState, useEffect } from "react";
import { CommentList } from "./CommentList";
import { CommentForm } from "./CommentForm";
import { useNode } from "../hooks/useNode";

export const CommentSection = () => {
    const [commentData, setCommentData] = useState([]);
    const { addComment, deleteItem, addReply, addLike, unlike } = useNode();

    const fetchData = async () => {
        await fetch('http://localhost:8080/blogpost/comments')
          .then(response => response.json())
          .then(data => setCommentData(data))
          .catch(error => console.error('Error fetching comments:', error));
    }

    useEffect(() => {
        fetchData();
      }, []);

    const handleAddComment = async (comment) => {
        await addComment(comment);
        fetchData();
    };

    const handleDeleteItem = async (itemId, itemType) => {
        await deleteItem(itemId, itemType);
        const updatedComments = commentData.filter(comment => comment.id !== itemId);
        setCommentData(updatedComments);
    }

    const handleAddReply = async (comment_id, reply) => {
        await addReply(comment_id, reply);
        fetchData();
    }

    const handleAddLike = async (comment_id) => {
        await addLike(comment_id);
        fetchData();
    }
    const handleUnlike = async (comment_id) => {
        await unlike(comment_id);
        fetchData();
    }

    return (
        <>
            <CommentList 
                comments={commentData}
                handleDeleteItem={handleDeleteItem}
                handleAddReply={handleAddReply}
                handleAddLike={handleAddLike}
                handleUnlike={handleUnlike}
            />
            <CommentForm 
                handleAddComment ={handleAddComment}
            />
        </>
    )
}