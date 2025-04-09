package com.example.cms.dao.repositiries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cms.dao.entities.GroupUser;

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, String> {
	
	List<GroupUser> findByGroupId(String groupId);

    List<GroupUser> findByUserId(String userId);

    boolean existsByGroupIdAndUserId(String groupId, String userId);
}
