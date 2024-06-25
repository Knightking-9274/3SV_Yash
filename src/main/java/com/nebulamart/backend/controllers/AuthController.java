package com.nebulamart.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nebulamart.backend.config.JwtProvider;
import com.nebulamart.backend.entities.Cart;
import com.nebulamart.backend.entities.User;
import com.nebulamart.backend.exceptions.UserException;
import com.nebulamart.backend.repositories.UserRepository;
import com.nebulamart.backend.requests.LoginRequest;
import com.nebulamart.backend.responses.AuthResponse;
import com.nebulamart.backend.services.CartService;
import com.nebulamart.backend.services.implementations.CustomUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserServiceImplementation customUserServiceImplementation;
    @Autowired 
    private CartService cartService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createuserHandler(@RequestBody User user)throws UserException{
        String email = user.getEmail();
        String password = user.getPassword();
        String firtString = user.getFirstName();
        String lastString = user.getLastName();

        User isEmailExists = userRepository.findByEmail(email);
        if(isEmailExists!=null)
            throw new UserException("Email already exists!");
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firtString);
        createdUser.setLastName(lastString);
        
        User savedUser = userRepository.save(createdUser);
        Cart cart = cartService.createCart(savedUser); 
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Signup success!");

        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
        
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login success!");

        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
    }
    private Authentication authenticate(String username, String password) {
        UserDetails userdetails = customUserServiceImplementation.loadUserByUsername(username);
        if(userdetails==null)
            throw new BadCredentialsException("Invalid Username!");
        if(!passwordEncoder.matches(password,userdetails.getPassword()))    
            throw new BadCredentialsException("Invalid Password!");
        return new UsernamePasswordAuthenticationToken( userdetails,null,userdetails.getAuthorities());
    }
}
