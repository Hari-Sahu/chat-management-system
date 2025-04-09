package com.example.cms.dao.repositiries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cms.dao.entities.ChatMember;
import com.example.cms.dao.entities.User;

@Repository
public interface ChatMemberRepository extends JpaRepository<ChatMember, String> {
    
    // Optional custom methods
    List<ChatMember> findByChatId(String chatId);

    List<ChatMember> findByUserId(String userId);
    
    @Query("SELECT EXISTS ("
    		+ "  SELECT 1"
    		+ "  FROM ChatMember cm1, ChatMember cm2"
    		+ "  WHERE cm1.user = :senderUser"
    		+ "    AND cm2.user = :receiverUser"
    		+ "    and cm1.chat = cm2.chat"
    		+ ") AS is_chat_existing")
    boolean isChatExist(User senderUser, User receiverUser);

    void deleteByChatIdAndUserId(String chatId, String userId);
}