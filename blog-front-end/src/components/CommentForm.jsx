import React, { useState, useContext } from 'react';

export const CommentForm = ({ handleAddComment }) => {
    const [comment, setComment] = useState({
        content: "",
        name: "anonymous"
    })
    const isTextAreaDisabled = comment.content.trim().length === 0;

    const handleSubmit = async (e) => {
        e.preventDefault();
        handleAddComment(comment);
        setComment(prevState => ({...prevState, ['content']: ""}));
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setComment(prevState => ({ ...prevState, [name]: value }))
    }

    return (
        <form onSubmit={handleSubmit}> 
            <input
                type='text'
                name='content'
                value={comment.content}
                onChange={handleChange}
                placeholder='Enter Comment...' />
            <button type="submit" disabled={isTextAreaDisabled}>Submit</button>
        </form>
    )
}