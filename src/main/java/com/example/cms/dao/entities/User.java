package com.example.cms.dao.entities;

import com.example.cms.utils.EntityIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@NotBlank
    private String name;

    @NotBlank
    @Column(name = "mobile_number", unique = true)
    private String mobile;

    @NotBlank
    private String password;
    
    @Column(name = "profile_image_url")
    private String profileImageURL;

	public User() {
		super();
		this.id = EntityIdGenerator.generateId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfileImageURL() {
		return profileImageURL;
	}

	public void setProfileImageURL(String profileImageURL) {
		this.profileImageURL = profileImageURL;
	}
}
