package com.example.demo.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModle,Integer> {
    UserModle findByEmail(String email);
    UserModle findByUsername(String username);
}
