import React, { useState, useContext } from 'react';
import { useUser } from '../context/UserContext';
export const CommentForm = ({ handleAddComment }) => {

    const { user } = useUser();
    const [comment, setComment] = useState({
        content: "",
        name: user
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