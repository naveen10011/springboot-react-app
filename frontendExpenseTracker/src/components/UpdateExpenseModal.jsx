import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './UpdateExpenseModal.css'; // Import the CSS file

const UpdateExpenseModal = ({ expense, budgets, onClose, onUpdate }) => {
  const [amountString, setAmount] = useState('');
  const [description, setDescription] = useState('');
  const [budgetId, setBudgetId] = useState('');

  useEffect(() => {
    if (expense) {
      setAmount(expense.amount.toString());
      setDescription(expense.description);
      setBudgetId(expense.budget.budgetid.toString());
    }
  }, [expense]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    const amount = parseFloat(amountString);
    const updatedExpense = { ...expense, amount, description, budget: { budgetid: parseFloat(budgetId) } };

    const token = localStorage.getItem('token');

    try {
      const response = await axios.put(`http://localhost:8080/expense/update-expense/${expense.expenseid}`, updatedExpense, {
        headers: { Authorization: `Bearer ${token}` },
      });
      onUpdate(response.data);
      onClose();
    } catch (error) {
      console.error('There was an error updating the expense:', error);
    }
  };

  return (
    <div className="modal">
      <div className="modal-content">
        <form onSubmit={handleSubmit} className="expense-form">
          <h3>Update Expense</h3>
          <input
            type="number"
            value={amountString}
            onChange={(e) => setAmount(e.target.value)}
            placeholder="Amount"
            required
            className="input-field"
          />
          <input
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            placeholder="Description"
            required
            className="input-field"
          />
          <select
            value={budgetId}
            onChange={(e) => setBudgetId(e.target.value)}
            required
            className="select-field"
          >
            <option value="">Select Budget</option>
            {budgets.map(budget => (
              <option key={budget.budgetid} value={budget.budgetid}>{budget.budgetname}</option>
            ))}
          </select>
          <button type="submit" className="submit-button">Update Expense</button>
          <button type="button" onClick={onClose} className="cancel-button">Cancel</button>
        </form>
      </div>
    </div>
  );
};

export default UpdateExpenseModal;
