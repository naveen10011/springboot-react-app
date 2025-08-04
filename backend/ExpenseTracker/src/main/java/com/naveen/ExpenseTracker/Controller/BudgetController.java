package com.naveen.ExpenseTracker.Controller;

import com.naveen.ExpenseTracker.DTO.BudgetDTO;
import com.naveen.ExpenseTracker.DTO.ExpenseDTO;
import com.naveen.ExpenseTracker.Service.ServiceImpl.BudgetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetServiceImpl budgetService;

    @PostMapping("/create-budget")
    public BudgetDTO createBudget(@RequestBody BudgetDTO budgetDTO) {
        return budgetService.createBudget(budgetDTO);
    }

    @GetMapping("/get-budget/{id}")
    public ResponseEntity<BudgetDTO> getBudgetById(@PathVariable Long id) {
        BudgetDTO budgetDTO = budgetService.getBudgetById(id);
        return ResponseEntity.ok(budgetDTO);
    }

    @GetMapping("/get-allbudgets")
    public List<BudgetDTO> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

    @PutMapping("/update-budget/{id}")
    public ResponseEntity<BudgetDTO> updateBudget(@RequestBody BudgetDTO budgetDTO, @PathVariable Long id) {
        BudgetDTO updatedBudgetDTO = budgetService.updateBudget(budgetDTO, id);
        return ResponseEntity.ok(updatedBudgetDTO);
    }

    @DeleteMapping("/delete-budget/{id}")
    public ResponseEntity<String> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/budgetsbyuserid/{userId}")
    public ResponseEntity<List<BudgetDTO>> getExpensesByUserId(@PathVariable Long userId) {
        List<BudgetDTO> budgets = budgetService.getBudgetByUserId(userId);
        return ResponseEntity.ok(budgets);
    }
}
