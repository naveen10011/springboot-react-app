import React from 'react';

const UserInfo = ({ user, earnings, expenses }) => {
  const totalEarnings = earnings.reduce((total, earning) => total + earning.totalamount, 0);
  const totalExpenses = expenses.reduce((total, expense) => total + expense.amount, 0);
  const amountremain=totalEarnings-totalExpenses;

  return (
    <div className="user-info">
      <h1> {user.fullName}</h1>
      {/* <h2>{user.username}</h2> */}
      <h2>{user.email}</h2>
      <h2>{user.mobile}</h2>
      <p>Total Earnings: ₹{totalEarnings}</p>
      <p>Total Expenses: ₹{totalExpenses}</p>
      <p>Amount Remaining: ₹{amountremain}</p>
    </div>
  );
};

export default UserInfo;
