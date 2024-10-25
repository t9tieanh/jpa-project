package service.impl;

import com.jpa.dao.UserDB;
import com.jpa.dao.impl.UserDBImpl;
import com.jpa.entity.UserEntity;
import service.UserService;

public class UserServiceImpl implements UserService {
    private UserDB userDB = new UserDBImpl();

    public boolean emailExists(String email) {
        if (userDB.getUserByEmail(email) == null)
            return false;
        return true ;
    }

    @Override
    public Long insert(UserEntity user) {
        return userDB.insert(user);
    }
}
