package com.learn.springdatajpa.repository;

import com.learn.springdatajpa.model.User;
import com.learn.springdatajpa.model.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class ManyToManyMappingTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    public void saveUser() {
        User user = new User();
        user.setFirstName("shalini");
        user.setLastName("dhana");
        user.setEmail("shalini@gmail.com");
        user.setPassword("shalini4857");

        boolean isNewRoleForUser = false;
        if (true) {
            UserRole userRole1 = new UserRole();
            userRole1.setName("HR");
            UserRole userRole2 = new UserRole();
            userRole2.setName("CUSTOMER");
            List<UserRole> userRoles = new ArrayList<>();
            userRoleRepository.saveAll(userRoles);
        }
        UserRole userRole1 = userRoleRepository.findById(1L).get();
        UserRole userRole2 = userRoleRepository.findById(2L).get();
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole1);
        userRoles.add(userRole2);

        user.setUserRoles(userRoles);

        userRepository.save(user);
    }

    @Test
    public void updateUser() {
        User user = userRepository.findById(1L).get();
        user.setEmail("manoRamo5849");
        userRepository.save(user);
    }

    @Test
    public void getUser() {
        User user = userRepository.findById(1L).get();
        for (UserRole userRole : user.getUserRoles()) {
            System.out.println(userRole.getName());
        }
    }

    @Test
    public void getRoles() {
        UserRole userRole = userRoleRepository.findById(1L).get();
        for (User user : userRole.getUsers()) {
            System.out.println(user.getFirstName());
        }
    }
}
