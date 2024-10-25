package service;

import com.jpa.entity.UserEntity;

public interface UserService {
    boolean emailExists(String email) ;
    Long insert(UserEntity user) ;
}
