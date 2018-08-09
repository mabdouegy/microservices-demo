package com.mohamedomar.codechallenge.microservices.users.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import com.mohamedomar.codechallenge.microservices.users.repository.UserRepository;


/**
 * Config class for test Application
 * 
 * @author Mohamed Omar
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.mohamedomar.codechallenge.microservices.users.service"} )
public class ServiceTestConfig {

	@Bean
	public UserRepository userRepository() {
		return Mockito.mock(UserRepository.class);
	}



}
