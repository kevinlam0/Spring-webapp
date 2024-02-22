import React, { createContext, useState, useContext } from 'react';
import { useAccountActions } from "../hooks/useAccountActions";
import { useNavigate } from 'react-router-dom';

const UserContext = createContext();

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState("Guest");
    const { login } = useAccountActions();
    const navigate = useNavigate();

    const handleLogin = async (username, password) => {
        const success = await login(username, password);
        if (success) { 
            setUser(username);
            console.log("Logged in successfully");
            navigate("/")
        }
    }

    const logout = () => {
        setUser(null);
    }

    const register = (userData) => {

    }

    return (
        <UserContext.Provider value={{ user, setUser, handleLogin }}>
            {children}
        </UserContext.Provider>
    )
}

export const useUser = () => useContext(UserContext);