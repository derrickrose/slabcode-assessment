package com.slabcode.assessment.facade;

import com.slabcode.assessment.entity.User;
import com.slabcode.assessment.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class UsersFacade {
    private UsersRepository usersRepository;

    @Autowired
    UsersFacade(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User findByName(String name) {
        return usersRepository.findByName(name);
    }

    @Transactional
    public User save(User user) {
        return usersRepository.save(user);
    }


    public User findById(Long id) {
        return usersRepository.findById(id);
    }

}
