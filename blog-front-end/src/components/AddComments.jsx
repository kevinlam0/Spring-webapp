import React, { useState, useContext } from 'react';
export const CommentForm = () => {
    const [comment, setComment] = useState( '' )
    // const handleSubmit = (e) => {
    //     e.preventDefault();
    //     fetch('http://localhost:8080/blogpost/comments'), {
    //         method: 'POST',
    //         headers: 
    //     }
    // }

    const handleChange = (e) => {
        const { comment, value } = e.target;
        setComment(prevComment => ({ ...prevComment, }))
    }
    return (
        <form >
            <input
                type='text'
                name='name'
                value={comment}
                onChange={(e) => setComment(e.target.value)}
                placeholder='Enter Comment...' />
        </form>
    )
}