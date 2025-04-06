package com.example.cms.dao.entities;

import com.example.cms.supports.conveters.AccountActivityTypeConverter;
import com.example.cms.supports.enums.AccountActivityType;
import com.example.cms.utils.EntityIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account_activity")
public class AccountActivity extends BaseEntity {

	@Column
	private String token;

	@Column
	private Integer otp;

	@Column(name = "activity_type")
	@Convert(converter = AccountActivityTypeConverter.class)
	private AccountActivityType activityType;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
	
	public AccountActivity() {
        this.id = EntityIdGenerator.generateId();
    }

	public AccountActivity(AccountActivityType activityType, User user) {
		super();
		this.id = EntityIdGenerator.generateId();
		this.activityType = activityType;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public AccountActivityType getActivityType() {
		return activityType;
	}

	public void setActivityType(AccountActivityType activityType) {
		this.activityType = activityType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
