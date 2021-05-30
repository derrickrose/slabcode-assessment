package com.slabcode.assessment.service;

import com.slabcode.assessment.dto.UserDTO;
import com.slabcode.assessment.entity.User;
import com.slabcode.assessment.exception.CustomException;
import com.slabcode.assessment.facade.UsersFacade;
import com.slabcode.assessment.service.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UsersFacade usersFacade;

    @Autowired
    public UsersService(UsersFacade usersFacade) {
        this.usersFacade = usersFacade;
    }

    public UserDTO findByName(String name) {
        User user = usersFacade.findByName(name);
        return UserDTO.fromUser(user);
    }

    public UserDTO save(UserDTO userDTO) {
        User user = usersFacade.save(userDTO.toUser());
        return UserDTO.fromUser(user);
    }

    public UserDTO findById(Long id) {
        User user = usersFacade.findById(id);
        return UserDTO.fromUser(user);
    }


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;


    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenService.createToken(username, usersFacade.findByName(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
