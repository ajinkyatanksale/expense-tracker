package com.ajinkya.expensetracker.service.impl;

import com.ajinkya.expensetracker.dao.UserManagementDao;
import com.ajinkya.expensetracker.dto.auth.*;
import com.ajinkya.expensetracker.entity.UserEntity;
import com.ajinkya.expensetracker.service.UserManagementService;
import com.ajinkya.expensetracker.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    UserManagementDao userManagementDao;

    @Override
    public EnrollResponse enrollUser(EnrollRequest enrollRequest) {
        EnrollResponse enrollResponse = new EnrollResponse();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(enrollRequest.getUserName());
        userEntity.setCustomerName(enrollRequest.getName());
        userEntity.setPhoneNumber(enrollRequest.getPhoneNumber());
        userEntity.setAge(enrollRequest.getAge());
        userEntity.setPsswrd(PasswordUtil.encryptPassword(enrollRequest.getPassword()));
        long customer_id = userManagementDao.save(userEntity).getCustomerId();

        enrollResponse.setMessage("Enrollment successful!! Welcome user!!");
        return enrollResponse;
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        UserEntity user = userManagementDao.findByUsername(loginRequest.getUserName());
        LoginResponse loginResponse = new LoginResponse();
        if (null != user) {
            if (PasswordUtil.checkPassword(loginRequest.getPassword(), user.getPsswrd())) {
                loginResponse.setLoginStatus(true);
                loginResponse.setMessage("Welcome " + user.getCustomerName());
            } else {
                loginResponse.setLoginStatus(false);
                loginResponse.setMessage("The username or password is wrong");
            }
        } else {
            loginResponse.setLoginStatus(false);
            loginResponse.setMessage("The username or password is wrong");
        }
        return loginResponse;
    }

    public UserInfo getUserInfo (String userName) {
        UserEntity user = userManagementDao.findByUsername(userName);
        UserInfo userInfo = null;
        if (user != null) {
            userInfo = new UserInfo();
            userInfo.setUsername(user.getUsername());
            userInfo.setName(user.getCustomerName());
            userInfo.setPhoneNumber(user.getPhoneNumber());
            userInfo.setAge(user.getAge());
            userInfo.setCustomerId(user.getCustomerId());
        }
        return userInfo;
    }

    @Override
    public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserInfo(username);
    }
}
