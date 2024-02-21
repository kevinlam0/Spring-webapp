export const useNode = () => {
    const addComment = async function (commentData, comment) {
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

    return { addComment, deleteItem};
    
}