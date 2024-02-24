import React, { useState, useRef } from 'react';
import { useUser } from '../../context/UserContext';
export const CommentForm = ({ handleAddComment }) => {

    const { user } = useUser();
    const textareaRef = useRef(null);
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
            if (textareaRef.current) {
                textareaRef.current.style.height = 'auto';
            }
        }   
    }

    const handleKeyDown = (e) => {
        if (e.key === 'Enter') { handleSubmit(e); }
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setComment(prevState => ({ ...prevState, [name]: value }))
        if (textareaRef.current) {
            textareaRef.current.style.height = 'auto';
            textareaRef.current.style.height = `${textareaRef.current.scrollHeight}px`;
        }
    }

    return (
        <form onSubmit={handleSubmit}> 
            <textarea
                ref={textareaRef}
                type='text'
                name='content'
                autoFocus
                value={comment.content}
                onChange={handleChange}
                placeholder='Enter Comment...' 
                onKeyDown={handleKeyDown}
                style={{ resize: 'none' }}
            />
            <button type="submit" disabled={isTextAreaDisabled}>Submit</button>
        </form>
    )
}