package com.mohamedomar.codechallenge.microservices.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohamedomar.codechallenge.microservices.users.exception.DuplicateUserException;
import com.mohamedomar.codechallenge.microservices.users.model.User;
import com.mohamedomar.codechallenge.microservices.users.repository.UserRepository;


/**
 * service for accessing user information. This is where business logic lives!
 * For demo purposes, this is mainly a wrapper to the repository(DAO).
 *
 * In real applications, there might be a base class that gathers common functionalities
 * 
 * 
 * @author Mohamed Omar
 */

//TODO for demo purposes, input bean validation is not implemented. 
@Service
public class UserServiceImpl implements UserService {
	 @Autowired
	protected UserRepository userRepository;
	
	/**
	 * Find a user with the specified email.
	 *
	 * @param email
	 * @return The user if found, null otherwise.
	 */
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * Find users whose name contains the specified string
	 * 
	 * @param partialName
	 *            Any alphabetic string.
	 * @return The list of matching users - always non-null, but may be
	 *         empty.	
	 */
	public List<User> findByNameIgnoreCase(String name) {
		return userRepository.findByNameContainingIgnoreCase(name);
	}

	/**
	 * save entity
	 * 
	 * @param user
	 */
	@Override
	public void save(User user) {
		User existingUser = userRepository.findByEmail(user.getEmail());
		if(existingUser != null)
			throw new DuplicateUserException(user.getEmail());
		userRepository.save(user);
		
	}

	/**
	 * Find all users
	 *
	 * @return all users, an empty list instead
	 */
	
	@Override
	public List<User> findAll() {
		return userRepository.findAllByOrderByEmailAsc();
	}
	

	//normally the parameter to pass is the id of the user not the email
	/**
	 * delete entity
	 * 
	 * @param email
	 */
	@Override
	public void deleteByEmail(String email) {
		User existingUser = userRepository.findByEmail(email);
		if( existingUser != null)
			userRepository.delete(existingUser);	
	}
	
}
