package com.slabcode.assessment.service;

import com.slabcode.assessment.entity.User;
import com.slabcode.assessment.exception.CustomException;
import com.slabcode.assessment.facade.UsersFacade;
import com.slabcode.assessment.service.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UsersFacade usersFacade;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    public UsersService(UsersFacade usersFacade) {
        this.usersFacade = usersFacade;
    }

    public User findByName(String name) {
        return usersFacade.findByName(name);
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersFacade.save(user);
    }

    public User findById(Long id) {
        return usersFacade.findById(id);
    }


    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = usersFacade.findByName(username);
            return jwtTokenService.createToken(username, usersFacade.findByName(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
