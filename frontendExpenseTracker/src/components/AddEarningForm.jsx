import React, { useState } from 'react';
import axios from 'axios';
import './AddEarningForm.css'; // Import the CSS file

const AddEarningForm = ({ setEarnings, user }) => {
    const [totalAmountString, setTotalAmount] = useState('');
    const [earningName, setEarningName] = useState('');
    const [isRecurring, setIsRecurring] = useState(false);
    const [date, setDate] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();

        const totalAmount = parseFloat(totalAmountString);
        const newEarning = { 
            totalamount: totalAmount, 
            earningname: earningName, 
            isRecurring, 
            date, 
            userInfo: user 
        };
        console.log('EarningData:', newEarning);

        const token = localStorage.getItem('token');

        axios.post('http://localhost:8080/earning/create-earning', newEarning, {
            headers: { Authorization: `Bearer ${token}` }
        })
            .then(response => {
                setEarnings(prevEarnings => [...prevEarnings, response.data]);
                setEarningName('');
                setTotalAmount('');
                setIsRecurring(false);
                setDate('');
            });
    };

    return (
        <form onSubmit={handleSubmit} className="earning-form">
            <h3>Add Earning</h3>
            <input
                type="number"
                value={totalAmountString}
                onChange={(e) => setTotalAmount(e.target.value)}
                placeholder="Total Amount"
                required
                className="input-field"
            />
            <input
                type="text"
                value={earningName}
                onChange={(e) => setEarningName(e.target.value)}
                placeholder="Earning Name"
                required
                className="input-field"
            />
            <input
                type="date"
                value={date}
                onChange={(e) => setDate(e.target.value)}
                required
                className="input-field"
            />
            <label className="checkbox-label">
                Recurring:
                <input
                    type="checkbox"
                    checked={isRecurring}
                    onChange={(e) => setIsRecurring(e.target.checked)}
                    className="checkbox-field"
                />
            </label>
            <button type="submit" className="submit-button">Add Earning</button>
        </form>
    );
};

export default AddEarningForm;
