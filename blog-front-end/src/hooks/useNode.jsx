export const useNode = () => {
    const addComment = async function (comment) {
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
    }

    const deleteItem = async (itemId, itemType) => {
        try {
          const response = await fetch('http://localhost:8080/blogpost/comments', {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ id: itemId, type: itemType }) 
          });
    
          if (!response.ok) { throw new Error('Network response was not ok'); }
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
            if (!response.ok) { throw new Error('Network response was not ok'); }
            console.log("Reply Creation successful");
        }
        catch (error) { console.error("Error creating reply: ", error); }
    }

    const addLike = async (comment_id) => {
        try {
            const response = await fetch(`http://localhost:8080/blogpost/comments/${comment_id}/like`, { 
                method: 'POST', 
                headers: {'Content-Type': 'application/json'}
            });
            if (!response.ok) { throw new Error('Network response was not ok'); }
            console.log("Like successful");
        }
        catch (error) { console.error("Error liking comment: ", error); }
    }

    return { addComment, deleteItem, addReply, addLike };
    
}