import React from 'react';
import { formatDate } from './utils';
import './BudgetList.css'; // Import the CSS file

const BudgetList = ({ budgets, handleDelete,}) => {
  return (
    <div className="expense-table">
      <div className="grid-container">
        {/* <div className='grid-item'>#</div> */}
        <div className='grid-item'>Budget</div>
        <div className='grid-item'>Amount</div>
        <div className='grid-item'>Actions</div>
      </div>
      {budgets.map((budget, index) => (
        <div key={budget.budgetid} className="table-row">
          {/* <div>{index + 1}</div> */}
          <div>{budget.budgetname}</div>
          <div>₹{budget.amountlimit}</div>
          {/* <div>{formatDate(expense.createdAt)}</div> */}
          <div>
            {/* <button onClick={() => handleUpdate(expense)} className="update-button">Update</button> */}
            <button onClick={() => handleDelete(budget.budgetid)} className="delete-button">Delete</button>
          </div>
        </div>
      ))}
    </div>
  );
};

export default BudgetList;
