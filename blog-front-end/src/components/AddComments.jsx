import React, { useState, useContext } from 'react';
export const CommentForm = () => {
    const [comment, setComment] = useState( '' )

    const handleSubmit = (e) => {
        e.preventDefault();
        fetch('http://localhost:8080/blogpost/comments', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(comment),
        })
        .then(response => response.json())
        .then(data => console.log("user created"))
        .catch(error => console.error("Error creating comment: ", error))
    }

    // const handleChange = (e) => {
    //     const { comment, value } = e.target;
    //     setComment(prevComment => ({ ...prevComment, }))
    // }
    return (
        <form onSubmit={handleSubmit}>
            <input
                type='text'
                name='name'
                value={comment}
                onChange={(e) => setComment(e.target.value)}
                placeholder='Enter Comment...' />
            <button type="submit">Submit</button>
        </form>
    )
}