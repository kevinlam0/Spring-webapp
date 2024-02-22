import React, { createContext, useState, useContext } from 'react';
import { useAccountActions } from "../hooks/useAccountActions";

const UserContext = createContext();

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const { login } = useAccountActions();

    const handleLogin = (username, password) => {
        login(username, password);
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