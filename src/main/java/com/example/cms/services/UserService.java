package com.example.cms.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.dao.entities.AccountActivity;
import com.example.cms.dao.entities.User;
import com.example.cms.dao.repositiries.AccountActivityRepository;
import com.example.cms.dao.repositiries.UserRepository;
import com.example.cms.dto.requests.UserRegistrationRequest;
import com.example.cms.dto.responses.AppResponse;
import com.example.cms.exceptions.ServiceErrorCodes;
import com.example.cms.exceptions.ServiceException;
import com.example.cms.supports.enums.AccountActivityType;
import com.example.cms.utils.JwtUtil;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private AccountActivityRepository accActvRepository;
	
	@Autowired
	private JwtUtil jwtUtil;

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

	public String login(String mobile, String password) {
		return userRepository.findByMobile(mobile).filter(user -> user.getPassword().equals(password))
				.map(user -> {
					accActvRepository.findByUser(user)
					.ifPresent(acctObj -> {
						throw new ServiceException(ServiceErrorCodes.ALREADY_LOGIN);
					});
					
					String token = jwtUtil.generateToken(mobile);
					saveAccountActivity(AccountActivityType.LOGIN, user, token);
					return token;
				})
				.orElse(null);
	}
	
	private void saveAccountActivity(AccountActivityType type, User user, String token) {
		AccountActivity entity = new AccountActivity(type, user);
		entity.setToken(token);
		accActvRepository.save(entity);
	}
}
