import React, { useState } from 'react';
import { useUser } from '../context/UserContext';
import NavBar from '../NavBar';
import "../styles/Login.css"

export const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { handleLogin, handleRegister } = useUser();

  const handleKeyDown = (e) => {
    if (e.key === 'Enter') {
      handleLogin( username, password )
    }
  }

  return (
    <>
      <NavBar/>
      <div className='login-page-container'>
        <div className="login-form-container">
          <div className='login-form'>
            <h3>Welcome</h3>
            <input type="text" autoFocus onKeyDown={handleKeyDown} placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
            <input type="password" onKeyDown={handleKeyDown} placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
            <div className='login-button-container'>
              <button id='login-btn' onClick={() => handleLogin(username, password)}>Login</button>
              <span className="button-spacing"></span>
              <button onClick={() => handleRegister(username, password)}>Register</button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

