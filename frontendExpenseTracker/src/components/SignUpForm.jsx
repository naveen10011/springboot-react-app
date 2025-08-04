import React, { useState } from 'react';
import axios from 'axios';
import './LoginPage.css';

const SignUpForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [email, setEmail] = useState('');
  const [fullName, setFullName] = useState('');
  const [phone, setPhone] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    if (password !== confirmPassword) {
      setMessage('Passwords do not match');
      return;
    }

    axios.post('http://localhost:8080/user/create-user', {
      username: username,
      password: password,
      email: email,
      fullName: fullName,
      mobile: phone
    })
    .then(response => {
      console.log('Signup successful:', response.data);
      setMessage('Signup successful!');
      // Clear form fields
      setUsername('');
      setPassword('');
      setConfirmPassword('');
      setEmail('');
      setFullName('');
      setPhone('');
      // Handle successful signup (e.g., redirect to login)
    })
    .catch(error => {
      console.error('There was an error signing up:', error);
      setMessage('There was an error signing up, please try again.');
    });
  };

  return (
    <div className="signup-container">
      <form onSubmit={handleSubmit}>
        {/* <h2>Sign Up</h2> */}
        {message && <p>{message}</p>}
        <input
          type="text"
          value={username}
          onChange={(event) => setUsername(event.target.value)}
          placeholder="Username"
          required
        />
        <br />
        <input
          type="password"
          value={password}
          onChange={(event) => setPassword(event.target.value)}
          placeholder="Password"
          required
        />
        <br />
        <input
          type="password"
          value={confirmPassword}
          onChange={(event) => setConfirmPassword(event.target.value)}
          placeholder="Confirm Password"
          required
        />
        <br />
        <input
          type="email"
          value={email}
          onChange={(event) => setEmail(event.target.value)}
          placeholder="Email"
          required
        />
        <br />
        <input
          type="text"
          value={fullName}
          onChange={(event) => setFullName(event.target.value)}
          placeholder="Full Name"
          required
        />
        <br />
        <input
          type="text"
          value={phone}
          onChange={(event) => setPhone(event.target.value)}
          placeholder="Phone"
          required
        />
        <br />
        <button type="submit">Sign Up</button>
      </form>
    </div>
  );
};

export default SignUpForm;
