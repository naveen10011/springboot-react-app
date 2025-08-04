package com.naveen.ExpenseTracker.Entity;


import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    @SequenceGenerator(name = "seq",sequenceName = "ex_seq",allocationSize = 1)
    @Column(name = "userid")
    private Long userid;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "mobile", nullable = false)
    private Long mobile;

    private String roles;


//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo")
//    private Set<Expense> expenses;
//
////    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo")
////    private Set<Budget> budgets;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo")
//    private Set<Earning> earnings;
}

