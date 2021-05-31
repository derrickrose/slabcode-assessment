package com.slabcode.assessment.repository;

import com.slabcode.assessment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    User findByName(String name);

    User save(User user);

    User findById(Long id);
}
