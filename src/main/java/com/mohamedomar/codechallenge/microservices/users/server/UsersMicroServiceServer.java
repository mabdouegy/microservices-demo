package com.mohamedomar.codechallenge.microservices.users.server;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.mohamedomar.codechallenge.microservices.users.config.SwaggerConfig;
import com.mohamedomar.codechallenge.microservices.users.config.UsersConfiguration;

/**
 * Run as a micro-service, 
 * <p>
 * Note that the configuration for this application is imported from
 * {@link UsersConfiguration}. This is a deliberate separation of concerns.
 * 
 * @author Mohamed Omar
 */
@EnableAutoConfiguration
@Import({UsersConfiguration.class, SwaggerConfig.class})
public class UsersMicroServiceServer {


	protected Logger logger = Logger.getLogger(UsersMicroServiceServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded tomcat
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
	 
		// Tell server to look for users-server.properties or
		// users-server.yml
		System.setProperty("spring.config.name", "users-server");

		SpringApplication.run(UsersMicroServiceServer.class, args);
	}
}
