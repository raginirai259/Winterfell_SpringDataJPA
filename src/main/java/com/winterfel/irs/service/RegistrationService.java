package com.winterfel.irs.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winterfel.irs.entity.UserEntity;
import com.winterfel.irs.exception.InvaidCityException;
import com.winterfel.irs.exception.InvaidEmailException;
import com.winterfel.irs.exception.InvaidNameException;
import com.winterfel.irs.exception.InvaidPasswordException;
import com.winterfel.irs.exception.InvaidPhoneException;
import com.winterfel.irs.exception.InvaidUserIdException;
import com.winterfel.irs.exception.UserIdAlreadyPresentException;
import com.winterfel.irs.exception.winterfelException;
import com.winterfel.irs.model.User;
import com.winterfel.irs.repository.UserRepository;

@Service
public class RegistrationService {

	@Autowired
	private UserRepository userRepo;
	String regex1="^[a-zA-Z0-9]{4,15}+$";
	
	public String registerUser(User user) throws winterfelException
	{
		validate(user);
		
	boolean b=userRepo.existsById(user.getUserId());
	if(b)
		throw new UserIdAlreadyPresentException("RegistrationService.USERID_PRESENT");
		UserEntity userEntity=new UserEntity();
		userEntity.setCity(user.getCity());
		userEntity.setEmail(user.getEmail());
		userEntity.setName(user.getName());
		userEntity.setPassword(user.getPassword());
		userEntity.setPhone(user.getPhone());
		userEntity.setUserId(user.getUserId());
		userRepo.saveAndFlush(userEntity);
		return "UserRepository.REGISTRATION_SUCCESS";
	}

	public void validate(User user) throws winterfelException {
		if(!isValidUserId(user.getUserId()))
		{
			throw new InvaidUserIdException("RegistrationService.INVALID_USER_ID");
		}
		if(!isValidPassword(user.getPassword()))
		{
			throw new InvaidPasswordException("RegistrationService.INVALID_PASSWORD");
		}
		if(!isValidName(user.getName()))
		{
			throw new InvaidNameException("RegistrationService.INVALID_NAME");
		}
		if(!isValidCity(user.getCity()))
		{
			throw new InvaidCityException("RegistrationService.INVALID_CITY");
		}
		if(!isValidEmail(user.getEmail()))
		{
			throw new InvaidEmailException("RegistrationService.INVALID_EMAIL");
		}
		if(!isValidPhoneNumber(user.getPhone()))
		{
			throw new InvaidPhoneException("RegistrationService.INVALID_PHONE_NUMBER");
		}
		
	}

	public  boolean isValidUserId(String userId) {
		Boolean b1=false;
		Pattern pattern1=Pattern.compile(regex1);
		Matcher matcher1=pattern1.matcher(userId);
		if(matcher1.matches())
			b1=true;
		
		return b1;
	}

	public boolean isValidPassword(String password) {
		Boolean b1=false;
		//String regex2="((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})";
		Pattern pattern2=Pattern.compile(regex1);
		Matcher matcher2=pattern2.matcher(password);
		if(matcher2.matches())
			b1=true;
		return b1;
	}

	public boolean isValidName(String name) {
		Boolean b1=false;
		Pattern pattern3=Pattern.compile(regex1);
		Matcher matcher3=pattern3.matcher(name);
		if(matcher3.matches())
			b1=true;
		return b1;
	}

	public boolean isValidCity(String city) {
		Boolean b1=false;
			Pattern pattern4=Pattern.compile(regex1);
		Matcher matcher4=pattern4.matcher(city);
		if(matcher4.matches())
			b1=true;
		return b1;
	}

	public boolean isValidEmail(String email) {
		Boolean b1=false;
		String regex5="^(.+)@(.+)$";
		Pattern pattern5=Pattern.compile(regex5);
		Matcher matcher5=pattern5.matcher(email);
		if(matcher5.matches())
			b1=true;
		return b1;
	}

	public  boolean isValidPhoneNumber(String phone) {
		
		Boolean b1=false;
		String regex6="[0-9]{10}";
		Pattern pattern6=Pattern.compile(regex6);
		Matcher matcher6=pattern6.matcher(phone);
		if(matcher6.matches())
			b1=true;
		return b1;
	}
	
}
