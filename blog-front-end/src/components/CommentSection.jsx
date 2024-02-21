import React, { useState, useEffect } from "react";
import { CommentList } from "./CommentList";
import { CommentForm } from "./CommentForm";
import { useNode } from "../hooks/useNode";

export const CommentSection = () => {
    const [commentData, setCommentData] = useState([]);
    const { addComment, deleteItem } = useNode();

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
        await addComment(commentData, comment);
        fetchData();
    };

    const handleDeleteItem = async (itemId, itemType) => {
        await deleteItem(itemId, itemType);
        const updatedComments = commentData.filter(comment => comment.id !== itemId);
        setCommentData(updatedComments);
    }

    return (
        <>
            <CommentList 
                comments={commentData}
                handleDeleteItem={handleDeleteItem}
            />
            <CommentForm 
                handleAddComment ={handleAddComment}
            />
        </>
    )
}