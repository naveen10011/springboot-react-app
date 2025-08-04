package com.naveen.ExpenseTracker.Config;

import com.naveen.ExpenseTracker.Mapper.BudgetMapper;
import com.naveen.ExpenseTracker.Mapper.EarningMapper;
import com.naveen.ExpenseTracker.Mapper.ExpenseMapper;
import com.naveen.ExpenseTracker.Mapper.UserInfoMapper;
import com.naveen.ExpenseTracker.Service.ServiceImpl.BudgetServiceImpl;
import com.naveen.ExpenseTracker.Service.ServiceImpl.EarningServiceImpl;
import com.naveen.ExpenseTracker.Service.ServiceImpl.ExpenseServiceImpl;
import com.naveen.ExpenseTracker.Service.ServiceImpl.UserInfoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public UserInfoMapper userInfoMapper(){
        return new UserInfoMapper();
    }
    @Bean
    public UserInfoServiceImpl userInfoService(){
        return new UserInfoServiceImpl();
    }

    @Bean
    public EarningMapper earningMapper() {
        return new EarningMapper();
    }

    @Bean
    public EarningServiceImpl earningService() {
        return new EarningServiceImpl();
    }

    @Bean
    public BudgetMapper budgetMapper() {
        return new BudgetMapper();
    }

    @Bean
    public BudgetServiceImpl budgetService() {
        return new BudgetServiceImpl();
    }

    @Bean
    public ExpenseMapper expenseMapper() {
        return new ExpenseMapper();
    }

    @Bean
    public ExpenseServiceImpl expenseService() {
        return new ExpenseServiceImpl();
    }




}
