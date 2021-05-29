package com.slabcode.assessment.controller;

import com.slabcode.assessment.dto.UserDTO;
import com.slabcode.assessment.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;

    @Autowired
    UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return usersService.findById(id).withPassWord(null);
    }

    @GetMapping
    public UserDTO findUserByName(@RequestParam String name) {
        return usersService.findByName(name).withPassWord(null);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        userDTO = usersService.save(userDTO).withPassWord(null);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(userDTO);
    }

    @GetMapping("/user")
    public String greetingsUser() {
        return "HELLO USER";
    }

    @GetMapping("/admin")
    public String greetingsAdmin() {
        return "HELLO ADMIN";
    }
}
