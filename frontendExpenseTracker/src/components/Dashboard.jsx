import React, { useState, useEffect } from 'react';
import axios from 'axios';
import UserInfo from './UserInfo';
import ExpenseList from './ExpenseList';
import AddExpenseForm from './AddExpenseForm';
import AddEarningForm from './AddEarningForm';
import AddBudgetForm from './AddBudgetForm';
import UpdateExpenseModal from './UpdateExpenseModal';
import BudgetList from './BudgetList';
import './Dashboard.css';  // Import the new CSS file

const Dashboard = () => {
  const [user, setUser] = useState({});
  const [expenses, setExpenses] = useState([]);
  const [earnings, setEarnings] = useState([]);
  const [budgets, setBudgets] = useState([]);
  const [selectedExpense, setSelectedExpense] = useState(null);
  const [isUpdateModalOpen, setIsUpdateModalOpen] = useState(false);
  const [showExpenseForm, setShowExpenseForm] = useState(false);
  const [showEarningForm, setShowEarningForm] = useState(false);
  const [showBudgetForm, setShowBudgetForm] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem('token');
    const userData = JSON.parse(localStorage.getItem('user'));
    if (token && userData) {
      setUser(userData);
      fetchUserData(token, userData.userid);
      console.log('UserId:',userData.userid,': token :',token);
    } else {
      // Redirect to login if not authenticated
      window.location.href = '/';
    }
  }, []);

  const fetchUserData = async (token, userId) => {
    try {
      const expensesResponse = await axios.get(`http://localhost:8080/expense/expensesbyuserid/${userId}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      console.log('expense:',expensesResponse.data);
      setExpenses(expensesResponse.data);

      const earningsResponse = await axios.get(`http://localhost:8080/earning/earningbyuserid/${userId}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      console.log('expense:',earningsResponse.data);
      setEarnings(earningsResponse.data);

      const budgetsResponse = await axios.get(`http://localhost:8080/budget/budgetsbyuserid/${userId}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      
      budgetsResponse.data.map(budget =>console.log(budget.budgetname))
      setBudgets(budgetsResponse.data);
    } catch (error) {
      console.error('There was an error fetching user data:', error);
    }
  };

  const handleDeleteBudget = async (budgetId) => {
    const token = localStorage.getItem('token');
    try {
      await axios.delete(`http://localhost:8080/budget/delete-budget/${budgetId}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setBudgets(budgets.filter(budget => budget.budgetid !== budgetId));
    } catch (error) {
      console.error('There was an error deleting the budget:', error);
    }
  };

  const handleDelete = async (expenseId) => {
    const token = localStorage.getItem('token');
    try {
      await axios.delete(`http://localhost:8080/expense/delete/${expenseId}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setExpenses(expenses.filter(expense => expense.expenseid !== expenseId));
    } catch (error) {
      console.error('There was an error deleting the expense:', error);
    }
  };

  const handleUpdate = (expense) => {
    setSelectedExpense(expense);
    setIsUpdateModalOpen(true);
  };

  const handleUpdateSubmit = (updatedExpense) => {
    setExpenses(prevExpenses => prevExpenses.map(expense => (
      expense.expenseid === updatedExpense.expenseid ? updatedExpense : expense
    )));
  };

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    window.location.href = '/';
  };

  return (
    <div className="dashboard-container">
      <aside className="sidebar">
      <button className="logout" onClick={handleLogout}>Logout</button>
        <UserInfo user={user} earnings={earnings} expenses={expenses} />
        <nav>
          <ul>
            <li>
              <button onClick={() => setShowExpenseForm(!showExpenseForm)}>Add Expense</button>
            </li>
            <li>
              <button onClick={() => setShowEarningForm(!showEarningForm)}>Add Earning</button>
            </li>
            <li>
              <button onClick={() => setShowBudgetForm(!showBudgetForm)}>Add Budget</button>
            </li>
            
          </ul>
        </nav>
        <div className="budget-list">
          <BudgetList budgets={budgets} handleDelete={handleDeleteBudget} />
        </div>
      </aside>
      <main className="main-content">
        <div className="expense-list">
          <ExpenseList expenses={expenses} handleDelete={handleDelete} handleUpdate={handleUpdate} />
        </div>
        {showExpenseForm && (
          <div className="form-container">
            <AddExpenseForm setExpenses={setExpenses} user={user} budgets={budgets} />
          </div>
        )}
        {showEarningForm && (
          <div className="form-container">
            <AddEarningForm setEarnings={setEarnings} user={user} />
          </div>
        )}
        {showBudgetForm && (
          <div className="form-container">
            <AddBudgetForm setBudgets={setBudgets} user={user} />
          </div>
        )}
      </main>
      {isUpdateModalOpen && (
        <UpdateExpenseModal
          expense={selectedExpense}
          budgets={budgets}
          onClose={() => setIsUpdateModalOpen(false)}
          onUpdate={handleUpdateSubmit}
        />
      )}
    </div>
  );
};

export default Dashboard;
