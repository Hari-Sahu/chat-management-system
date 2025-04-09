package com.example.cms.dao.entities;

import com.example.cms.supports.conveters.GroupUserRoleConverter;
import com.example.cms.supports.enums.GroupUserRole;
import com.example.cms.utils.EntityIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "group_users")
public class GroupUser extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private ChatGroup group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "user_role", nullable = false)
    @Convert(converter = GroupUserRoleConverter.class)
    private GroupUserRole userRole;
    
	public GroupUser() {
        this.id = EntityIdGenerator.generateId();
    }

	public GroupUser(ChatGroup group, User user) {
		super();
		this.id = EntityIdGenerator.generateId();
		this.group = group;
		this.user = user;
	}

	public ChatGroup getGroup() {
		return group;
	}

	public void setGroup(ChatGroup group) {
		this.group = group;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GroupUserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(GroupUserRole userRole) {
		this.userRole = userRole;
	}
}
