package com.example.myproject.data.repositories;

import com.example.myproject.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findUserByUsername(String username);
    User getById(String id);
}
