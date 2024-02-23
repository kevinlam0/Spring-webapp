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
        const minLength = 5;
        if (username.trim() === "" || password.trim() === "") {
            alert("You cannot have a blank username or password.")
            return false
        }

        if (password.trim().length < minLength) {
            alert(`Please create a password of at least ${minLength} characters.`)
            return false
        }

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
                else if (response.status === 438) {
                    alert("Username cannot be longer than 20 characters.")
                }
                else if (response.status === 439) {
                    alert(`Please create a password of at least ${minLength} characters.`)
                }
                return false;
            }
            return true;
        }
        catch (error) { console.error("Error registering account: ", error); }
        
    }
    
    return { login, register }


}