/**
 * 
 */
package com.mohamedomar.codechallenge.microservices.users.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mohamedomar.codechallenge.microservices.users.service.UserService;


/**
 * Config class for test Web Application
 * 
 * @author Mohamed Omar
 *
 */

@Configuration
@ComponentScan(basePackages = "com.mohamedomar.codechallenge.microservices.users.*", excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Configuration.class),
		@Filter(type = FilterType.ANNOTATION, value = Repository.class),
		@Filter(type = FilterType.ANNOTATION, value = Service.class) })
public class WebTestConfig {

	@Bean
	public UserService userService() {
		return Mockito.mock(UserService.class);
	}
}
