package com.nebulamart.backend.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.nebulamart.backend.config.JwtProvider;
import com.nebulamart.backend.entities.User;
import com.nebulamart.backend.exceptions.UserException;
import com.nebulamart.backend.repositories.UserRepository;
import com.nebulamart.backend.services.UserService;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long UserId) throws UserException {
       
        Optional<User> user = userRepository.findById(UserId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("User not found with id: "+UserId);
    }

    @Override
    public User UserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new UserException("User not found with email: "+email);
        }
        return user;
    }
    
}
