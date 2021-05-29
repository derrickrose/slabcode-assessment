package com.slabcode.assessment.dto;

import com.slabcode.assessment.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String passWord;


    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassWord(user.getPassWord());
        return userDTO;
    }

    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassWord(this.passWord);
        return user;
    }

    public UserDTO withPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }
}
