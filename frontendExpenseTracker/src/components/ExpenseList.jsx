import React from "react";
import { formatDate } from "./utils";
import "./ExpenseList.css"; // Import the CSS file

const ExpenseList = ({ expenses, handleDelete, handleUpdate }) => {
  return (
    <div className="expense-table">
      <div className="grid-container">
        <div className="grid-item">#</div>
        <div className="grid-item">Description</div>
        <div className="grid-item">Budget</div>
        <div className="grid-item">Amount</div>
        <div className="grid-item">Date</div>
        <div className="grid-item">Actions</div>
      </div>
      {expenses.map((expense, index) => (
        <div key={expense.expenseid} className="table-row">
          <div>{index + 1}</div>
          <div>{expense.description}</div>
          <div>{expense.budget.budgetname}</div>
          <div>₹{expense.amount}</div>
          <div>{formatDate(expense.createdAt)}</div>
          <div>
            <button
              onClick={() => handleUpdate(expense)}
              className="update-button"
            >
              Update
            </button>
            <button
              onClick={() => handleDelete(expense.expenseid)}
              className="delete-button"
            >
              Delete
            </button>
          </div>
        </div>
      ))}
    </div>
  );
};

export default ExpenseList;
