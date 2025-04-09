package com.example.cms.dao.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cms.dao.entities.ChatGroup;

@Repository
public interface ChatGroupRepository extends JpaRepository<ChatGroup, String> {
    // You can define custom queries here if needed
}
