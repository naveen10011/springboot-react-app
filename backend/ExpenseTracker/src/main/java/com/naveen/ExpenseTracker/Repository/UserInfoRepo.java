package com.naveen.ExpenseTracker.Repository;


import com.naveen.ExpenseTracker.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepo extends JpaRepository<UserInfo,Long> {

    Optional<UserInfo> findByusername(String username);
}