package com.example.cms.dao.repositiries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cms.dao.entities.ChatMember;

@Repository
public interface ChatMemberRepository extends JpaRepository<ChatMember, String> {
    
    // Optional custom methods
    List<ChatMember> findByChatId(String chatId);

    List<ChatMember> findByUserId(String userId);
    
    boolean existsByChatIdAndUserId(String chatId, String userId);

    void deleteByChatIdAndUserId(String chatId, String userId);
}