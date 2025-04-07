package com.example.cms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.dao.entities.AccountActivity;
import com.example.cms.dao.entities.User;
import com.example.cms.dao.repositiries.AccountActivityRepository;
import com.example.cms.dto.requests.UserRegistrationRequest;
import com.example.cms.dto.responses.AppResponse;
import com.example.cms.exceptions.ServiceErrorCodes;
import com.example.cms.exceptions.ServiceException;
import com.example.cms.supports.enums.AccountActivityType;
import com.example.cms.utils.AESUtil;
import com.example.cms.utils.JwtUtil;

@Service
public class AuthenticationService {
	
	@Autowired
    private UserService userServ;
		
	@Autowired
    private AccountActivityRepository accActvRepository;
	
	@Autowired
	private JwtUtil jwtUtil;

    public AppResponse registerUser(UserRegistrationRequest reqDTO) {
    	userServ.registerUser(reqDTO);
        AppResponse res = new AppResponse();
        res.setMessage("User registered successfully");
        return res;
    }
    
	public String login(String mobile, String password) {
		return userServ.getUserByMobile(mobile)
				.filter(user -> user.getPassword().equals(AESUtil.encrypt(password)))
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
	
	public AccountActivity getAccountByToken(String token) {
		return accActvRepository.findByToken(token)
				.map(acc -> acc)
	            .orElse(null);
	}
	
	public void logout(AccountActivity acc) {
		accActvRepository.delete(acc);
	}
}
