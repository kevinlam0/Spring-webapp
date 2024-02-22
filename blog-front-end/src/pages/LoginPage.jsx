import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useUser } from '../context/UserContext';

export const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  const { handleLogin } = useUser();
  const goToHomePage = () => {
      navigate("/")
  }

  const handleLoginSubmit = () => {
    handleLogin( username, password );
  };

  return (
    <div>
      <h2>Login Page</h2>
      <div>
        <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
        <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
        <button onClick={handleLoginSubmit}>Login</button>
      </div>
      <button onClick={goToHomePage}>Click me</button>
    </div>
  );
}

