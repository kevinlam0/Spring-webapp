export const useAccountActions = () => {
    const login = async ( username, password ) => {
        try {
            const response = await fetch('http://localhost:8080/blogpost/account/login', { 
                method: 'POST', 
                headers: {'Content-Type': 'application/json'}, 
                body: JSON.stringify({ username: username, password: password })
            });
            if (!response.ok) { 
                if (response.status === 436) {
                    alert("Password was incorrect. Please try again")
                }
                else if (response.status === 435) {
                    alert("There is no account with this username")
                }
                
                return false;
            }
            return true;
        }
        catch (error) { console.error("Error creating comment: ", error); }
    }
    return { login }
}