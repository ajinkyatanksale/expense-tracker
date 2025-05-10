package com.ajinkya.expensetracker.service.impl;

import com.ajinkya.expensetracker.dao.UserManagementDao;
import com.ajinkya.expensetracker.dto.auth.*;
import com.ajinkya.expensetracker.entity.UserEntity;
import com.ajinkya.expensetracker.service.UserManagementService;
import com.ajinkya.expensetracker.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserManagementServiceImpl implements UserManagementService {

    private final Logger logger = LoggerFactory.getLogger(UserManagementServiceImpl.class);
    private final String LOG_ENTRY = "Entry";
    private final String LOG_EXIT = "EXIT";

    @Autowired
    UserManagementDao userManagementDao;

    @Override
    public EnrollResponse enrollUser(EnrollRequest enrollRequest) {
        String logPrefix = "enrollUser()";
        logger.debug(logPrefix + "[" + LOG_ENTRY + "]");
        EnrollResponse enrollResponse = new EnrollResponse();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(enrollRequest.getUserName());
        userEntity.setCustomerName(enrollRequest.getName());
        userEntity.setPhoneNumber(enrollRequest.getPhoneNumber());
        userEntity.setAge(enrollRequest.getAge());
        userEntity.setPsswrd(PasswordUtil.encryptPassword(enrollRequest.getPassword()));
        long customer_id = userManagementDao.save(userEntity).getCustomerId();
        logger.debug(logPrefix + "New user ceated!! [" + customer_id + "]");
        enrollResponse.setMessage("Enrollment successful!! Welcome user!!");
        logger.debug(logPrefix + LOG_EXIT);
        return enrollResponse;
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        String logPrefix = "loginUser()";
        logger.debug(logPrefix + "[" + LOG_ENTRY + "]");
        UserEntity user = userManagementDao.findByUsername(loginRequest.getUserName());
        LoginResponse loginResponse = new LoginResponse();
        if (null != user) {
            if (PasswordUtil.checkPassword(loginRequest.getPassword(), user.getPsswrd())) {
                loginResponse.setLoginStatus(true);
                loginResponse.setMessage("Welcome " + user.getCustomerName());
                logger.debug(logPrefix + "Login success!! [" + user.getCustomerId() + "]");
            } else {
                loginResponse.setLoginStatus(false);
                loginResponse.setMessage("The username or password is wrong");
                logger.debug(logPrefix + "Login failed!! [" + user.getCustomerId() + "]");
            }
        } else {
            loginResponse.setLoginStatus(false);
            loginResponse.setMessage("The username or password is wrong");
            logger.debug(logPrefix + "Login failed!!");
        }
        logger.debug(logPrefix + LOG_EXIT);
        return loginResponse;
    }

    public UserInfo getUserInfo (String userName) {
        String logPrefix = "getUserInfo()";
        logger.debug(logPrefix + "[" + LOG_ENTRY + "]");
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
        logger.debug(logPrefix + LOG_EXIT);
        return userInfo;
    }

    @Override
    public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserInfo(username);
    }
}
