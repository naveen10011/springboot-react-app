package com.naveen.ExpenseTracker.DTO;


import com.naveen.ExpenseTracker.Entity.UserInfo;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EarningDTO {
    private Long earningid;
    private String earningname;
    private Double totalamount;
    private boolean isRecurring;
    private LocalDate date;
    private LocalDateTime createdAt;

    private UserInfo userInfo;
}
