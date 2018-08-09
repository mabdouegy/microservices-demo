package com.mohamedomar.codechallenge.microservices.users.service;

import java.util.List;

import com.mohamedomar.codechallenge.microservices.users.model.User;


/**
 * interface for service for accessing user information. This is where business logic lives!
 * For demo purposes, this is just a wrapper to the repository(DAO).
 * 
 * 
 * @author Mohamed Omar
 */
public interface UserService {

	/**
	 * Find all users
	 *
	 * @return all users, an empty list instead
	 */
	public List<User> findAll();
	
	/**
	 * Find a user with the specified email.
	 *
	 * @param email
	 * @return The user if found, null otherwise.
	 */
	public User findByEmail(String email);
	
	/**
	 * save entity
	 * 
	 * @param user
	 */
	public void save(User user);

	
	/**
	 * Find users whose name contains the specified string
	 * 
	 * @param partialName Any alphabetic string.
	 * @return The list of matching users - always non-null, but may be
	 *         empty.
	 */
	public List<User> findByNameIgnoreCase(String partialName);

	/**
	 * delete entry
	 * 
	 * @param email
	 */
	void deleteByEmail(String id);
}
