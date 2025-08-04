package com.naveen.ExpenseTracker.Service.ServiceImpl;

import com.naveen.ExpenseTracker.DTO.UserInfoDTO;
import com.naveen.ExpenseTracker.Entity.UserInfo;
import com.naveen.ExpenseTracker.ExceptionHandler.ResourceNotFoundException;
import com.naveen.ExpenseTracker.Mapper.UserInfoMapper;
import com.naveen.ExpenseTracker.Repository.UserInfoRepo;
import com.naveen.ExpenseTracker.Service.ServiceInterface.UserInfoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoServiceInterface {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfoDTO createUser(UserInfoDTO userInfoDTO) {
        UserInfo userInfo=userInfoMapper.userInfoDTOtouserInfo(userInfoDTO);
        userInfo.setRoles("ROLE_USER");
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        UserInfo savedUserInfo=userInfoRepo.save(userInfo);
        return userInfoMapper.userInfotouserInfoDTO(savedUserInfo);
    }

    @Override
    public UserInfoDTO getuserbyid(Long id) {
        UserInfo userInfo=userInfoRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User with that id doesnt exist"+ id));
        return userInfoMapper.userInfotouserInfoDTO(userInfo);
    }

    @Override
    public List<UserInfoDTO> getalluser() {
        List<UserInfo> users=userInfoRepo.findAll();
        return users.stream().map((user)->userInfoMapper.userInfotouserInfoDTO(user)).toList();

    }

    @Override
    public UserInfoDTO updateuser(UserInfoDTO userInfoDTO, Long id) {
        UserInfo user=userInfoRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("user not found")
        );
        user.setUsername(userInfoDTO.getUsername());
        user.setEmail(userInfoDTO.getEmail());
        user.setFullName(userInfoDTO.getFullName());
        user.setMobile(userInfoDTO.getMobile());
        UserInfo updateduser=userInfoRepo.save(user);
        return userInfoMapper.userInfotouserInfoDTO(updateduser);
    }

    @Override
    public UserInfoDTO deleteuser(Long id) {
        UserInfo user=userInfoRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("user not found")
        );
        userInfoRepo.delete(user);
        return userInfoMapper.userInfotouserInfoDTO(user);
    }

    @Override
    public UserInfoDTO getUserByUsername(String username) {
        UserInfo user=userInfoRepo.findByusername(username).orElseThrow(
                ()->new ResourceNotFoundException("user not found")
        );
        return userInfoMapper.userInfotouserInfoDTO(user);
    }
}

