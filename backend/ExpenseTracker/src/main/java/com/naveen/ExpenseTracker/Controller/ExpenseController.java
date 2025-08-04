package com.naveen.ExpenseTracker.Controller;

import com.naveen.ExpenseTracker.DTO.ExpenseDTO;
import com.naveen.ExpenseTracker.Service.ServiceImpl.ExpenseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseServiceImpl expenseService;

    @PostMapping("/create-expense")
    public ExpenseDTO createExpense(@RequestBody ExpenseDTO expenseDTO){
        return expenseService.createExpense(expenseDTO);
    }

    @GetMapping("/get-expense/{id}")
    public ResponseEntity<ExpenseDTO> getexpensebyid(@PathVariable Long id){
        ExpenseDTO expenseDTO=expenseService.getexpensebyid(id);
        return ResponseEntity.ok(expenseDTO);

    }

    @GetMapping("/get-allexpense")
    public List<ExpenseDTO> getallexpense(){
        return expenseService.getallExpense();
    }

    @PutMapping("/update-expense/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@RequestBody ExpenseDTO expenseDTO,@PathVariable Long id){
        ExpenseDTO expenseDTO1= expenseService.updateexpense(expenseDTO,id);
        return ResponseEntity.ok(expenseDTO1);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id){
        expenseService.deleteexpense(id);
        return ResponseEntity.ok("Expense deleted.");
    }

    @GetMapping("/expensesbyuserid/{userId}")
    public ResponseEntity<List<ExpenseDTO>> getExpensesByUserId(@PathVariable Long userId) {
        List<ExpenseDTO> expenses = expenseService.getExpensesByUserId(userId);
        return ResponseEntity.ok(expenses);
    }

}

