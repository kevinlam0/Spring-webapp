import React, { useState } from 'react';
import { useUser } from '../context/UserContext';
import NavBar from '../NavBar';

export const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { handleLogin, handleRegister } = useUser();

  const handleLoginSubmit = () => {
    handleLogin( username, password );
  };

  const handleRegisterAccount = () => {
    handleRegister( username, password )
  }
  const handleKeyDown = (e) => {
    if (e.key === 'Enter') {
      handleLogin( username, password )
    }
  }

  return (
    <div>
      <NavBar/>
      <h2>Login Page</h2>
      <div>
        <input type="text" autoFocus placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
        <input type="password" onKeyDown={handleKeyDown} placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
        <button onClick={handleLoginSubmit}>Login</button>
        <button onClick={handleRegisterAccount}>Register</button>
      </div>
    </div>
  );
}

