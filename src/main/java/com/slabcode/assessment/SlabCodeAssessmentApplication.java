package com.slabcode.assessment;

import com.slabcode.assessment.entity.Role;
import com.slabcode.assessment.entity.User;
import com.slabcode.assessment.facade.UsersFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class SlabCodeAssessmentApplication implements CommandLineRunner {

    @Autowired
    private UsersFacade usersFacade;

    public static void main(String[] args) {
        SpringApplication.run(SlabCodeAssessmentApplication.class, args);
    }

    @Override
    public void run(String... params) throws Exception {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@email.com");
        admin.setRoles(new ArrayList(Arrays.asList(Role.ROLE_ADMIN)));

        usersFacade.save(admin);

        User client = new User();
        client.setUsername("client");
        client.setPassword("client");
        client.setEmail("client@email.com");
        client.setRoles(new ArrayList(Arrays.asList(Role.ROLE_USER)));

        usersFacade.save(client);
    }


}
