import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './LoginPage.css';

const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/login/autologin', {
        username,
        password,
      });
      console.log('Login successful:', response.data);
      
      // Store the token in localStorage
      localStorage.setItem('token', response.data);

      
      const UserResponse = await axios.get(`http://localhost:8080/user/get-userbyusername/${username}`, {
          headers: { Authorization: `Bearer ${response.data}` },
        });
        // setExpenses(expensesResponse.data);
        console.log('Userdata:', UserResponse.data);
      
      // Store user data
      localStorage.setItem('user', JSON.stringify(UserResponse.data));

      // Redirect to the dashboard
      navigate('/dashboard');
    } catch (error) {
      console.error('There was an error logging in:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={username}
        onChange={(event) => setUsername(event.target.value)}
        placeholder="Username"
      />
      <br />
      <input
        type="password"
        value={password}
        onChange={(event) => setPassword(event.target.value)}
        placeholder="Password"
      />
      <br />
      <button type="submit">Login</button>
    </form>
  );
};

export default LoginForm;
