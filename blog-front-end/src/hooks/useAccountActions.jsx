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

    const register = async ( username, password ) => {
        try {
            const response = await fetch('http://localhost:8080/blogpost/account/register', { 
                    method: 'POST', 
                    headers: {'Content-Type': 'application/json'}, 
                    body: JSON.stringify({ username: username, password: password })
            });
            if (!response.ok) { 
                if (response.status === 437) {
                    alert("This username is already taken")
                }
                if (response.status === 438) {
                    alert("Username cannot be longer than 20 characters.")
                }
                return false;
            }
            return true;
        }
        catch (error) { console.error("Error registering account: ", error); }
        
    }
    return { login, register }


}