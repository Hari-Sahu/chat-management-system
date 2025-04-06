package com.example.cms.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.dao.entities.User;
import com.example.cms.dao.repositiries.UserRepository;
import com.example.cms.dto.requests.UserRegistrationRequest;
import com.example.cms.dto.responses.AppResponse;
import com.example.cms.exceptions.ServiceErrorCodes;
import com.example.cms.exceptions.ServiceException;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;

    public AppResponse registerUser(UserRegistrationRequest reqDTO) {
        Optional<User> existing = userRepository.findByMobile(reqDTO.getMobileNumber());
        if (existing.isPresent()) {
            throw new ServiceException(ServiceErrorCodes.MOBILE_NUMBER_EXIST);
        }
//        user.encryptPassword();
        User user = new User();
        user.setName(reqDTO.getName());
        user.setMobile(reqDTO.getMobileNumber());
        user.setPassword(reqDTO.getPassword());
        user.setProfileImageURL(reqDTO.getProfileImageURL());
        userRepository.save(user);
        
        AppResponse res = new AppResponse();
        res.setMessage("User registered successfully");
        return res;
    }

    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User updateUser(String id, User user) {
        User existing = getUser(id);
        existing.setName(user.getName());
        return userRepository.save(existing);
    }

//    public boolean login(String mobile, String password) {
//        return userRepository.findByMobile(mobile)
//                .map(user -> new BCryptPasswordEncoder().matches(password, user.getPassword()))
//                .orElse(false);
//    }
}
