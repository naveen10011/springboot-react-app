package com.naveen.ExpenseTracker.Repository;


import com.naveen.ExpenseTracker.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepo extends JpaRepository<Expense,Long> {
    List<Expense> findByUserInfo_userid(Long userId);
}
