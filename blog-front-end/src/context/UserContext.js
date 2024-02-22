import React, { createContext, useState, useContext } from 'react';
import { useAccountActions } from "../hooks/useAccountActions";
import { useNavigate } from 'react-router-dom';

const UserContext = createContext();

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState("Guest");
    const { login, register } = useAccountActions();
    const navigate = useNavigate();

    const handleLogin = async (username, password) => {
        const success = await login(username, password);
        if (success) { 
            setUser(username);
            console.log("Logged in successfully");
            navigate("/")
        }
    }

    const handleLogout = async () => {
        setUser("Guest");
    }

    const handleRegister = async (username, password) => {
        const success = await register(username, password)
        if (success) {
            console.log("Registering account successful");
            setUser(username);
            navigate("/")
        }
    }

    return (
        <UserContext.Provider value={{ user, setUser, handleLogout, handleLogin, handleRegister }}>
            {children}
        </UserContext.Provider>
    )
}

export const useUser = () => useContext(UserContext);