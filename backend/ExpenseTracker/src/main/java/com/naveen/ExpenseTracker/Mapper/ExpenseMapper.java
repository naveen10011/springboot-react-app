package com.naveen.ExpenseTracker.Mapper;


import com.naveen.ExpenseTracker.DTO.ExpenseDTO;
import com.naveen.ExpenseTracker.Entity.Expense;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ExpenseMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Expense expenseDTOtoExpense(ExpenseDTO expenseDTO){
        Expense expense = modelMapper.map(expenseDTO, Expense.class);
        return expense;
    }

    public ExpenseDTO expenseToExpenseDTO(Expense expense){
        ExpenseDTO expenseDTO = modelMapper.map(expense, ExpenseDTO.class);
        return expenseDTO;
    }


}
