/**
 * 
 */
package com.mohamedomar.codechallenge.microservices.users.util;

import static com.mohamedomar.codechallenge.microservices.users.constants.TestConstants.USER_1_EMAIL;
import static com.mohamedomar.codechallenge.microservices.users.constants.TestConstants.USER_1_FULL_NAME;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohamedomar.codechallenge.microservices.users.model.User;
/**
 * Utils for testing
 * 
 * @author Mohamed Omars
 *
 */
public final class TestUtil {

	private TestUtil() {
	}

	public static User createDefaultUser() {
		User user = new User();
		user.setEmail(USER_1_EMAIL);
		user.setName(USER_1_FULL_NAME);
		return user;
	}

	public static List<User> createOneUsersList() {
		List<User> list = new ArrayList<>();
		list.add(createDefaultUser());
		return list;
	}
	
	public static List<User> createEmptyUsersList() {
		return  new ArrayList<>();
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
