package com.example.cms.dao.repositiries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cms.dao.entities.AccountActivity;
import com.example.cms.dao.entities.User;

@Repository
public interface AccountActivityRepository extends JpaRepository<AccountActivity, String> {
    
    // Optional: Find all activities by user ID
	Optional<AccountActivity> findByUser(User userId);

    // Optional: Get latest activity by user
    AccountActivity findTopByUser_IdOrderByCreatedOnDesc(String userId);
}
