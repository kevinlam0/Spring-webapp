export const useCommentActions = () => {
    const addComment = async function (comment) {
        try {
            const response = await fetch('http://localhost:8080/blogpost/comments', { 
                method: 'POST', 
                headers: {'Content-Type': 'application/json'}, 
                body: JSON.stringify(comment) 
            });
            if (!response.ok) { 
                if (response.status === 464) { alert("Comment is too long. Please reduce word count") }
            }
        }
        catch (error) { console.error("Error creating comment: ", error); }
    }

    const deleteItem = async (itemId, itemType) => {
        try {
          const response = await fetch('http://localhost:8080/blogpost/comments', {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ id: itemId, type: itemType }) 
          });
    
          if (!response.ok) { 
            if (response.status === 461) { alert("You tried deleting something that is not a comment nor reply.") }
            else if (response.status === 462) { alert("There is not an item with this ID you're deleting") }
            throw new Error(''); }
          console.log("Comment Deletion successful");
        }
        catch(error) {console.error('Error deleting item: ', error);}
    }

    const addReply = async (comment_id, reply) => {
        try {
            const response = await fetch(`http://localhost:8080/blogpost/comments/${comment_id}/reply`, { 
                method: 'POST', 
                headers: {'Content-Type': 'application/json'}, 
                body: JSON.stringify(reply) 
            });
            if (!response.ok) { 
                if (response.status === 464) { alert("Comment is too long. Please reduce word count") }
            }
            else console.log("Reply Creation successful");
        }
        catch (error) { console.error("Error creating reply: ", error); }
    }

    const addLike = async (comment_id, username) => {
        try {
            const response = await fetch(`http://localhost:8080/blogpost/comments/${comment_id}/like/${username}`, { 
                method: 'POST', 
                headers: {'Content-Type': 'application/json'}
            });
            if (!response.ok) { 
                if (response.status === 463) { alert("There was a problem with liking comment.") }
                throw new Error(''); }
            console.log("Like successful");
        }
        catch (error) { console.error("Error liking comment: ", error); }
    }

    const unlike = async (comment_id, username) => {
        try {
            const response = await fetch(`http://localhost:8080/blogpost/comments/${comment_id}/unlike/${username}`, { 
                method: 'POST', 
                headers: {'Content-Type': 'application/json'}
            });
            if (!response.ok) { 
                if (response.status === 463) { alert("There was a problem with unliking comment.") }
                throw new Error(''); 
            }
            console.log("Unlike successful");
        }
        catch (error) { console.error("Error unliking comment: ", error); }
    }

    const addReplyLike = async (reply_id, username) => {
        try {
            const response = await fetch(`http://localhost:8080/blogpost/comments/reply/${reply_id}/like/${username}`, { 
                method: 'POST', 
                headers: {'Content-Type': 'application/json'}
            });
            if (!response.ok) { 
                if (response.status === 463) { alert("There was a problem with liking reply.") }
                throw new Error(''); 
            }
            console.log("Like successful");
        }
        catch (error) { console.error("Error liking reply: ", error); }
    }

    const unReplyLike = async (reply_id, username) => {
        try {
            const response = await fetch(`http://localhost:8080/blogpost/comments/reply/${reply_id}/unlike/${username}`, { 
                method: 'POST', 
                headers: {'Content-Type': 'application/json'}
            });
            if (!response.ok) { 
                if (response.status === 463) { alert("There was a problem with unliking reply.") }
                throw new Error(''); 
            }
            console.log("Unlike successful");
        }
        catch (error) { console.error("Error unliking reply: ", error); }
    }
    return { addComment, deleteItem, addReply, addLike, unlike, addReplyLike, unReplyLike };
}