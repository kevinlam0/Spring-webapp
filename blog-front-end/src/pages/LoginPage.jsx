import React from 'react';
import { useNavigate } from 'react-router-dom';

export const LoginPage = () => {
    const navigate = useNavigate();
    const goToHomePage = () => {
        navigate("/")
    }
  return (
    <div>
      <h2>Login Page</h2>
      {/* Your login form */}
      <button onClick={goToHomePage}>Click me</button>
    </div>
  );
}

