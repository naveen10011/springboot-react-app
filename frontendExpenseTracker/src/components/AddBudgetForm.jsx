import React, { useState } from 'react';
import axios from 'axios';
import './AddBudgetForm.css'; // Import the CSS file

const AddBudgetForm = ({ setBudgets, user }) => {
  const [budgetName, setBudgetName] = useState('');
  const [amountLimit, setAmountLimit] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();

    const newBudget = { budgetname: budgetName, amountlimit: parseFloat(amountLimit), userInfo: user };
    console.log('BudgetData:', newBudget);

    const token = localStorage.getItem('token');

    axios.post('http://localhost:8080/budget/create-budget', newBudget, {
      headers: { Authorization: `Bearer ${token}` }
    })
      .then(response => {
        setBudgets(prevBudgets => [...prevBudgets, response.data]);
        setBudgetName('');
        setAmountLimit('');
      })
      .catch(error => {
        console.error('There was an error creating the budget:', error);
      });
  };

  return (
    <form onSubmit={handleSubmit} className="budget-form">
      <h3>Add Budget</h3>
      <input
        type="text"
        value={budgetName}
        onChange={(e) => setBudgetName(e.target.value)}
        placeholder="Budget Name"
        required
        className="input-field"
      />
      <input
        type="number"
        value={amountLimit}
        onChange={(e) => setAmountLimit(e.target.value)}
        placeholder="Amount Limit"
        required
        className="input-field"
      />
      <button type="submit" className="submit-button">Add Budget</button>
    </form>
  );
};

export default AddBudgetForm;
