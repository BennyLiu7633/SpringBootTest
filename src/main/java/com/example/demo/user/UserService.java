package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Integer addUser(UserModle user) {
        UserModle newUser = userRepository.save(user);
        return newUser.getId();
    }
}
