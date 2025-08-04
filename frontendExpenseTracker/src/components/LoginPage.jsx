import React, { useState } from 'react';
import './LoginPage.css';
// import axios from 'axios';

import LoginForm from './LoginForm';
import SignUpForm from './SignUpForm';

const LoginPage = () => {
  const [mode, setMode] = useState('login');

  const handleModeChange = (newMode) => {
    setMode(newMode);
  };

  return (
    <section className="login-page">
      <header>
        <div className="button-container">
          <button onClick={() => handleModeChange('login')}>Login</button>
          <button onClick={() => handleModeChange('signup')}>Sign Up</button>
        </div>
      </header>
      {mode === 'login' ? (
        <LoginForm />
      ) : (
        <SignUpForm />
      )}
    </section>
  );
};

export default LoginPage;
