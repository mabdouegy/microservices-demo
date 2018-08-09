package com.mohamedomar.codechallenge.microservices.users.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohamedomar.codechallenge.microservices.users.exception.UserNotFoundException;
import com.mohamedomar.codechallenge.microservices.users.model.User;
import com.mohamedomar.codechallenge.microservices.users.service.UserService;

/**
 * A RESTFul controller for accessing user information.
 * 
 * @author Mohamed Omar
 */

//TODO implement idomptent update with Patch verb and the etag concept

//TODO central bean validation validation should be implemented using  @NotNull , @Email annotation and @Valid

//TODO in real application central exception handling should be managed by a HandlerExceptionResolver

//TODO in real application , should create a DTO layer instead of returning the User entity
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

	protected Logger logger = Logger.getLogger(UserController.class
			.getName());
 
	 @Autowired
	 private UserService userService;

	/**
	 * Fetch an user with the specified email
	 * 
	 * @param email
	 * @return The user if found.
	 * @throws UserNotFoundException
	 * If the email is not recognised.
	 */
    @RequestMapping(value ="user/email/", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public User byEmail(@RequestParam("email") String email) {
		logger.info("user-service byEmail() invoked: " + email);
		User user = userService.findByEmail(email);
		if (user == null) {
			throw new UserNotFoundException(email);
		}
		else {
			return user;
		}
	}
	/**
	 * save a user
	 * 
	 * @param user
	 *             
	 * @return The saved user
	 */	 
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void add(@RequestBody User user) {

		logger.info("user-service add() invoked: " + user.toString());
		 
		userService.save(user);
	}
	
	/**
	 * Fetch users with the specified name. A partial case-insensitive match
	 * is supported. So <code>http://.../users/username/a</code> will find any
	 * users with upper or lower case 'a' in their name.
	 * 
	 * @param partialName
	 * @return A non-null, non-empty set of user.
	 * @throws UserNotFoundException
	 *             If there are no matches at all.
	 */
    @RequestMapping(value ="user/name/{name}", method = RequestMethod.GET, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> byName(@PathVariable("name") String partialName) {
		logger.info("user-service findByNameIgnoreCase() invoked: "
				+ userService.getClass().getName() + " for "
				+ partialName);

		List<User> users = userService.findByNameIgnoreCase(partialName);
		logger.info("user-service byName() found: " + users);

		if (users.isEmpty())
			throw new UserNotFoundException(partialName);
		else {
			return users;
		}
	}
    
	/**
	 * Fetch all users

	 * @return A non-null, non-empty set of user.
	 * @throws UserNotFoundException
	 *             If there are no matches at all.
	 */
    @RequestMapping(value ="", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> all() {
		logger.info("user-service findAll() invoked");

		List<User> users = userService.findAll();
		logger.info("user-service findAll() found: " +users);
		
		return users;
	}
    
	/**
	 * delete a user by email
     *
	 */
    @RequestMapping(value ="user/email/", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void deleteByEmail(@RequestParam("email") String email) {
		logger.info("user-service byEmail() invoked: " + email);
		userService.deleteByEmail(email);
	}
    
}
