package com.ajinkya.expensetracker.dao;

import com.ajinkya.expensetracker.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserManagementDao extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername (String userName);
}
