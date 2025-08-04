import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginPage from './components/LoginPage';
import Dashboard from './components/Dashboard';
import './App.css'

const App = () => {
  return (
    <Router>
      <Routes>
        {/* <Route path="/login" element={<LoginPage />} /> */}
        
        <Route path="/dashboard" element={<Dashboard />} />
        
        
        
        <Route path="/" element={<LoginPage />} />
        
       
      </Routes>
    </Router>
  );
};
export default App;
