package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Integer addUser(UserModle user) {
        Set<ConstraintViolation<UserModle>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<UserModle> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException(sb.toString(), violations);
        }
        UserModle newUser = userRepository.save(user);
        return newUser.getId();
    }
}
