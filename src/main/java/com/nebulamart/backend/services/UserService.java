package com.nebulamart.backend.services;

import com.nebulamart.backend.entities.User;
import com.nebulamart.backend.exceptions.UserException;

public interface UserService {
    public User findUserById(Long UserId) throws  UserException;

    public User UserProfileByJwt(String jwt) throws UserException;

    
}
