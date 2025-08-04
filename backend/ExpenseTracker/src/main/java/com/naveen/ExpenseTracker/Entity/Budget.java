package com.naveen.ExpenseTracker.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@ToString
@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    @SequenceGenerator(name = "seq",sequenceName = "ex_seq",allocationSize = 1)
    private Long budgetid;
    @Column
    private String budgetname;
    private Double amountlimit;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private UserInfo userInfo;

    public Long getBudgetid() {
        return budgetid;
    }

    public void setBudgetid(Long budgetid) {
        this.budgetid = budgetid;
    }

    public String getBudgetname() {
        return budgetname;
    }

    public void setBudgetname(String budgetname) {
        this.budgetname = budgetname;
    }

    public Double getAmountlimit() {
        return amountlimit;
    }

    public void setAmountlimit(Double amountlimit) {
        this.amountlimit = amountlimit;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }



    //        @OneToMany(mappedBy = "budget")
//        private List<Expense> expenses;


}
