package com.jpa.dao;

import com.jpa.dto.UserDTO;
import com.jpa.entity.UserEntity;
import com.jpa.utils.HibernateUtil;

import javax.persistence.EntityManager;

public interface UserDB {
    UserEntity getUserById (long userId);
    UserEntity getUserByEmail (String email);
    Long insert(UserEntity user) ;
}
