package com.example.cms.dao.repositiries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.dao.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByMobile(String mobile);
}
