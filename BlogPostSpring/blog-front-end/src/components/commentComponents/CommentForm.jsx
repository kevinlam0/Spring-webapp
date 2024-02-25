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
    const defaultHeight = '31px';
    const handleSubmit = (e) => {
        if (user === "Guest") {alert("You cannot add comments as a guest. Please log in!")}
        else {
            e.preventDefault();
            handleAddComment(comment);
            setComment(prevState => ({...prevState, content: ""}));
            if (textareaRef.current) {
                textareaRef.current.style.height = defaultHeight;
            }
        }   
    }

    const handleKeyDown = (e) => {
        if (e.key === 'Enter') { 
            if (user === "Guest") { e.preventDefault(); }
            if (comment.content.trim().length !== 0 ) {handleSubmit(e);}
            else { e.preventDefault() }
        }
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setComment(prevState => ({ ...prevState, [name]: value }))
        if (textareaRef.current) {
            textareaRef.current.style.height = `${textareaRef.current.scrollHeight}px`;
        }
        
    }
    if (textareaRef.current) { if (comment.content.length === 0) {textareaRef.current.style.height = defaultHeight;} }

    return (
        <div className='comment-form'>
            <form > 
                <textarea
                    ref={textareaRef}
                    type='text'
                    name='content'
                    value={comment.content}
                    onChange={handleChange}
                    placeholder='Add comment...' 
                    onKeyDown={handleKeyDown}
                    style={{ resize: 'none', height: defaultHeight }}
                />
            </form>
            
            { isTextAreaDisabled ? 
                <button id='comment-submit-off' onClick={handleSubmit} disabled={isTextAreaDisabled}>Comment</button>
                :
                <button id='comment-submit-on' onClick={handleSubmit} disabled={isTextAreaDisabled}>Comment</button>
            }
        </div>
    )
}