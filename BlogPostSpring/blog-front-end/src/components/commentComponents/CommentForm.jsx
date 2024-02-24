import React, { useState } from 'react';
import { useUser } from '../../context/UserContext';
export const CommentForm = ({ handleAddComment }) => {

    const { user } = useUser();
    const [comment, setComment] = useState({
        content: "",
        name: user
    })
    const isTextAreaDisabled = comment.content.trim().length === 0;

    const handleSubmit = (e) => {
        if (user === "Guest") {alert("You cannot add comments as a guest. Please log in!")}
        else {
            e.preventDefault();
            handleAddComment(comment);
            setComment(prevState => ({...prevState, content: ""}));
        }   
    }

    const handleKeyDown = (e) => {
        if (e.key === 'Enter') { handleSubmit(e); }
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setComment(prevState => ({ ...prevState, [name]: value }))
    }

    return (
        <form onSubmit={handleSubmit}> 
            <textarea
                type='text'
                name='content'
                autoFocus
                value={comment.content}
                onChange={handleChange}
                placeholder='Enter Comment...' 
                onKeyDown={handleKeyDown}
            />
            <button type="submit" disabled={isTextAreaDisabled}>Submit</button>
        </form>
    )
}