package com.example.cms.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.dao.entities.User;
import com.example.cms.dao.repositiries.UserRepository;
import com.example.cms.dto.requests.UserRegistrationRequest;
import com.example.cms.dto.responses.UserDetailsDTO;
import com.example.cms.exceptions.ServiceErrorCodes;
import com.example.cms.exceptions.ServiceException;
import com.example.cms.utils.AESUtil;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	public void registerUser(UserRegistrationRequest reqDTO) {
		Optional<User> existing = getUserByMobile(reqDTO.getMobileNumber());
        if (existing.isPresent()) {
            throw new ServiceException(ServiceErrorCodes.MOBILE_NUMBER_EXIST);
        }
        User user = new User();
        user.setName(reqDTO.getName());
        user.setMobile(reqDTO.getMobileNumber());
        user.setPassword(AESUtil.encrypt(reqDTO.getPassword()));
        user.setProfileImageURL(reqDTO.getProfileImageURL());
        userRepository.save(user);
	}
	
    public User getUser(String id) {
        return userRepository.findById(id)
        		.orElseThrow(() -> new ServiceException(ServiceErrorCodes.DATA_NOT_FOUND, "User"));
    }
    
    public Optional<User> getUserByMobile(String mobile) {
        return userRepository.findByMobile(mobile);
    }
    
    public User getUserByMobileNumber(String mobile) {
        return userRepository.findByMobile(mobile)
        		.orElseThrow(() -> new ServiceException(ServiceErrorCodes.DATA_NOT_FOUND, "User"));
    }
    
    public UserDetailsDTO getUserDetails(User user) {
        return mapToDTO(user);
    }

    public UserDetailsDTO updateUser(User user, String name) {
    	user.setName(name);
        userRepository.save(user);
        return mapToDTO(user);
    }
    
    private UserDetailsDTO mapToDTO(User user) {
    	UserDetailsDTO dto = new UserDetailsDTO();
        dto.setId(user.getId());
        dto.setMobile(user.getMobile());
        dto.setName(user.getName());
        dto.setProfileImageURL(user.getProfileImageURL());
        return dto;
    }
}
