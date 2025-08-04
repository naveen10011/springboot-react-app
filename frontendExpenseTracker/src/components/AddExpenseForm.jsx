import React, { useState } from 'react';
import axios from 'axios';
import './AddExpenseForm.css'; // Import the CSS file

const AddExpenseForm = ({ setExpenses, user, budgets }) => {
    const [amountString, setAmount] = useState('');
    const [description, setDescription] = useState('');
    const [budgetId, setBudgetId] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();

        const amount = parseFloat(amountString);
        const newExpense = { amount, description, userInfo: user, budget: { budgetid: parseFloat(budgetId) } };
        console.log('ExpenseData:', newExpense);

        const token = localStorage.getItem('token');

        axios.post('http://localhost:8080/expense/create-expense', newExpense, {
            headers: { Authorization: `Bearer ${token}` }
        })
            .then(response => {
                setExpenses(prevExpenses => [...prevExpenses, response.data]); // Update expenses state
                setDescription('');
                setAmount('');
                setBudgetId('');
            });
    };

    return (
        <form onSubmit={handleSubmit} className="expense-form">
            <h3>Add Expense</h3>
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
            <button type="submit" className="submit-button">Add Expense</button>
        </form>
    );
};

export default AddExpenseForm;
