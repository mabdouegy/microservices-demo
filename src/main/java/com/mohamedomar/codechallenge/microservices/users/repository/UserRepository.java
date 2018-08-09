package com.mohamedomar.codechallenge.microservices.users.repository;

import java.util.List;
 
import org.springframework.data.repository.CrudRepository;

import com.mohamedomar.codechallenge.microservices.users.model.User;

/**
 * Repository for User data implemented using Spring Data JPA.
 * In real applications, there might be a base class that gathers common functionalities 
 * 
 * @author Mohamed Omar
 */
public interface UserRepository extends CrudRepository<User, String> {
	/**
	 * Find a user with the specified email.
	 *
	 * @param email
	 * @return The user if found, null otherwise.
	 */

   public User findByEmail(String email);
	/**
	 * Find users whose name contains the specified string
	 * 
	 * @param partialName
	 *            Any alphabetic string.
	 * @return The list of matching users - always non-null, but may be
	 *         empty.
	 */
	public List<User> findByNameContainingIgnoreCase(String name);

	/**
	 * Fetch the number of users known to the system.
	 * 
	 * @return The number of users.
	 */
	public List<User> findAllByOrderByEmailAsc();
	
}
