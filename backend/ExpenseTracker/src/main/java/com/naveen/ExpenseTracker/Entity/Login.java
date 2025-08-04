package com.naveen.ExpenseTracker.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Login {
    @Id
    private String username;
    private String password;
}
