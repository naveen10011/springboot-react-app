package com.naveen.ExpenseTracker.Entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    @SequenceGenerator(name = "seq",sequenceName = "ex_seq",allocationSize = 1)
    private Long expenseid;
    @Column(name = "Amount", nullable = false)
    private Double amount;
    @Column(name = "Date", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    private String description;




    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "budgetid", referencedColumnName = "budgetid")
    private Budget budget;




}



