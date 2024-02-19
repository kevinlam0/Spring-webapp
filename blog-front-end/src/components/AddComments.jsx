import React, { useState, useContext } from 'react';
import { CommentSection } from 'replyke';

export const CommentForm = () => {
    const [comment, setComment] = useState({
        content: "",
        name: "anonymous"
    })
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/blogpost/comments', { 
                method: 'POST', 
                headers: {'Content-Type': 'application/json'}, 
                body: JSON.stringify(comment) 
            });
            if (!response.ok) { throw new Error('Network response was not ok'); }
            console.log("Comment Creation successful");
        }
        catch (error) { console.error("Error creating comment: ", error); }
        // fetch('http://localhost:8080/blogpost/comments', { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(comment) })
        // .then(response => response.json())
        // .then(data => console.log("user created"))
        // .catch(error => console.error("Error creating comment: ", error));
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
            <button type="submit">Submit</button>
        </form>
    )
}