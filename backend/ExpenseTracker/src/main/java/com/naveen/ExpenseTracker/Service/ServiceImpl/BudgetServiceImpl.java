package com.naveen.ExpenseTracker.Service.ServiceImpl;

import com.naveen.ExpenseTracker.DTO.BudgetDTO;
import com.naveen.ExpenseTracker.Entity.Budget;
import com.naveen.ExpenseTracker.ExceptionHandler.ResourceNotFoundException;
import com.naveen.ExpenseTracker.Mapper.BudgetMapper;
import com.naveen.ExpenseTracker.Repository.BudgetRepo;
import com.naveen.ExpenseTracker.Service.ServiceInterface.BudgetServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetServiceInterface {

    @Autowired
    private BudgetMapper budgetMapper;

    @Autowired
    private BudgetRepo budgetRepo;

    @Override
    public BudgetDTO createBudget(BudgetDTO budgetDTO) {
        Budget budget = budgetMapper.budgetDTOtoBudget(budgetDTO);
        Budget savedBudget = budgetRepo.save(budget);
        return budgetMapper.budgetToBudgetDTO(savedBudget);
    }

    @Override
    public BudgetDTO getBudgetById(Long id) {
        Budget budget = budgetRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Budget with id " + id + " doesn't exist"));
        return budgetMapper.budgetToBudgetDTO(budget);
    }

    @Override
    public List<BudgetDTO> getAllBudgets() {
        List<Budget> budgets = budgetRepo.findAll();
        return budgets.stream()
                .map(budget -> budgetMapper.budgetToBudgetDTO(budget))
                .toList();
    }

    @Override
    public BudgetDTO updateBudget(BudgetDTO budgetDTO, Long id) {
        Budget budget = budgetRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Budget with id " + id + " doesn't exist"));
        budget.setBudgetname(budgetDTO.getBudgetname());
        budget.setAmountlimit(budgetDTO.getAmountlimit());
        Budget updatedBudget = budgetRepo.save(budget);
        return budgetMapper.budgetToBudgetDTO(updatedBudget);
    }

    @Override
    public BudgetDTO deleteBudget(Long id) {
        Budget budget = budgetRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Budget with id " + id + " doesn't exist"));
        budgetRepo.delete(budget);
        return budgetMapper.budgetToBudgetDTO(budget);
    }

    @Override
    public List<BudgetDTO> getBudgetByUserId(Long userId) {
        List<Budget> budgets = budgetRepo.findByUserInfo_userid(userId);
        return budgets.stream().map(budget -> budgetMapper.budgetToBudgetDTO(budget)).toList();

    }
}



