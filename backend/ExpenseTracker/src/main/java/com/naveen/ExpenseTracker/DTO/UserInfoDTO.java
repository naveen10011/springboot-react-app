package com.naveen.ExpenseTracker.DTO;


import com.naveen.ExpenseTracker.Entity.Budget;
import com.naveen.ExpenseTracker.Entity.Earning;
import com.naveen.ExpenseTracker.Entity.Expense;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private Long userid;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Long mobile;

//        private Set<Expense> expenses;
//
//
//        private Set<Budget> budgets;
//
//
//        private Set<Earning> earnings;

}
