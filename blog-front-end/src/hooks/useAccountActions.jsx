export const useAccountActions = () => {
    const login = async ( username, password ) => {
        try {
            console.log(JSON.stringify({ username: username, password: password }))
            const response = await fetch('http://localhost:8080/blogpost/account/login', { 
                method: 'POST', 
                headers: {'Content-Type': 'application/json'}, 
                body: JSON.stringify({ username: username, password: password })
            });
            if (!response.ok) { 
                const errorData = await response.json();
                console.error('Error: ')
            }
            console.log("Logged in successfully");
        }
        catch (error) { console.error("Error creating comment: ", error); }
    }
    return { login }
}