package com.winterfel.irs;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.winterfel.irs.model.User;
import com.winterfel.irs.service.RegistrationService;

@SpringBootApplication
@PropertySource(value= {"classpath:configuration.properties"})
public class WinterfellSpringDataJpaApplication implements CommandLineRunner{

	@Autowired 
	private Environment env;
	@Autowired
	ApplicationContext context;
	public static void main(String[] args) {
		SpringApplication.run(WinterfellSpringDataJpaApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		try
		{
			User user=new User();
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter user ID: ");
			String uid=sc.next();
			System.out.println("Enter password: ");
			String pwd=sc.next();
			System.out.println("Enter Name: ");
			String name=sc.next();
			System.out.println("Enter City: ");
			String city=sc.next();
			System.out.println("Enter Email: ");
			String email=sc.next();
			System.out.println("Enter Phone: ");
			String phone=sc.next();
			user.setUserId(uid);
			user.setPhone(phone);
			user.setCity(city);
			user.setEmail(email);
			user.setPassword(pwd);
			user.setName(name);
			
			RegistrationService service=(RegistrationService) context.getBean("registrationService");
			String registrationMessage=service.registerUser(user);
			System.out.println(env.getProperty(registrationMessage));
			
		}catch(Exception e) {
			System.out.println(env.getProperty(e.getMessage()));
		}
		
	}

}
