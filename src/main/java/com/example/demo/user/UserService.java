package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
@Validated
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserModle findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserModle findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Integer addUser(UserModle user) {
        Set<ConstraintViolation<UserModle>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<UserModle> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException(sb.toString(), violations);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserModle newUser = userRepository.save(user);
        return newUser.getId();
    }

    public boolean isLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication == null || authentication instanceof AnonymousAuthenticationToken);
    }
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
