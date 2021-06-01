package com.slabcode.assessment.controller;

import com.slabcode.assessment.dto.UserDTO;
import com.slabcode.assessment.service.UsersService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UsersController {

    private UsersService usersService;

    @Autowired
    UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/sign-up")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UsersController.sign-up}")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        userDTO = UserDTO.fromUser(usersService.save(userDTO.toUser(), true)).withPassWord(null);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(userDTO);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UsersController.search}")
    public UserDTO findUserByName(@RequestParam String name) {
        return UserDTO.fromUser(usersService.findByName(name)).withPassWord(null);
    }

    /*
    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UsersController.greetings.user}")
    public String greetingsUser() {
        return "HELLO USER";
    }
    //*/

    /*//
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UsersController.greetings.admin}")
    public String greetingsAdmin() {
        return "HELLO ADMIN";
    }
    //*/

    @PostMapping("/sign-in")
    @ApiOperation(value = "${UsersController.sign-in}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public String signIn(
            @ApiParam("UserName") @RequestParam String userName,
            @ApiParam("PassWord") @RequestParam String passWord) {
        return usersService.signin(userName, passWord);
    }
}
