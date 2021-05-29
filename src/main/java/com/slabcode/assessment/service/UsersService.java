package com.slabcode.assessment.service;

import com.slabcode.assessment.dto.UserDTO;
import com.slabcode.assessment.entity.User;
import com.slabcode.assessment.facade.UsersFacade;
import org.springframework.beans.factory.annotation.Autowired;
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
}
