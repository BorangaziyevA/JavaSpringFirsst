package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);
    User findById(int id);
    User deleteUserById(Long id);
    User findByEmailAndAndName(String name, String email);
    User findByName(String name);
}
