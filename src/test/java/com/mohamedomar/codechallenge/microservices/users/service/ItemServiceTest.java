 
package com.mohamedomar.codechallenge.microservices.users.service;


import static com.mohamedomar.codechallenge.microservices.users.constants.TestConstants.QUERY_PARAM_NON_EXISTING_EMAIL;
import static com.mohamedomar.codechallenge.microservices.users.constants.TestConstants.QUERY_PARAM_NON_EXISTING_USER_PARTIAL_NAME;
import static com.mohamedomar.codechallenge.microservices.users.constants.TestConstants.QUERY_PARAM_USER_1_PARTIAL_NAME;
import static com.mohamedomar.codechallenge.microservices.users.constants.TestConstants.USER_1_EMAIL;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.mohamedomar.codechallenge.microservices.users.config.ServiceTestConfig;
import com.mohamedomar.codechallenge.microservices.users.exception.DuplicateUserException;
import com.mohamedomar.codechallenge.microservices.users.model.User;
import com.mohamedomar.codechallenge.microservices.users.repository.UserRepository;
import com.mohamedomar.codechallenge.microservices.users.util.TestUtil;

/**
 * Test class for Users Service
 * 
 * @author Mohamed Omar
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceTestConfig.class, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
public class ItemServiceTest {
	
	@Autowired
	@InjectMocks
	private UserService userService;

	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(userRepository);
	}

	@Test
	public void testSearchByNameOneResult() {
		// Given
		List<User> users = TestUtil.createOneUsersList();
		// When
		Mockito.when(userRepository.findByNameContainingIgnoreCase(QUERY_PARAM_USER_1_PARTIAL_NAME)).thenReturn(users);
		List<User> resultList =  userService.findByNameIgnoreCase(QUERY_PARAM_USER_1_PARTIAL_NAME);
		
		// Then
		Mockito.verify(userRepository, Mockito.times(1)).findByNameContainingIgnoreCase(QUERY_PARAM_USER_1_PARTIAL_NAME);

		Assert.assertNotNull(resultList);
		Assert.assertFalse(resultList.isEmpty());
		Assert.assertThat(resultList, CoreMatchers.is(users));
	}
	
	@Test
	public void testSearchByNameNoResults() {
		// Given
		List<User> users = TestUtil.createEmptyUsersList();
		// When
		Mockito.when(userRepository.findByNameContainingIgnoreCase(QUERY_PARAM_NON_EXISTING_USER_PARTIAL_NAME)).thenReturn(users);
		List<User> resultList =  userService.findByNameIgnoreCase(QUERY_PARAM_NON_EXISTING_USER_PARTIAL_NAME);
		// Then
		Mockito.verify(userRepository, Mockito.times(1)).findByNameContainingIgnoreCase(QUERY_PARAM_NON_EXISTING_USER_PARTIAL_NAME);

		Assert.assertNotNull(resultList);
		Assert.assertTrue(resultList.isEmpty());
	}
	
	@Test
	public void testSearchEmailOneResult() {
		// Given
		User user = TestUtil.createDefaultUser();
		// When
		Mockito.when(userRepository.findByEmail(USER_1_EMAIL)).thenReturn(user);
		User resultUser = userService.findByEmail(USER_1_EMAIL);
		// Then
		Mockito.verify(userRepository, Mockito.times(1)).findByEmail(USER_1_EMAIL);

		Assert.assertNotNull(resultUser);
		Assert.assertThat(resultUser, CoreMatchers.is(user));
	}
	
	public void testSearchEmailNoResults() {

		// When
		Mockito.when(userRepository.findByEmail(QUERY_PARAM_NON_EXISTING_EMAIL)).
		then(null);
		User resultUser = userService.findByEmail(QUERY_PARAM_NON_EXISTING_EMAIL);

		// Then
		Mockito.verify(userRepository, Mockito.times(1)).findByEmail(QUERY_PARAM_NON_EXISTING_EMAIL);

		Assert.assertNull(resultUser);
	}
	
	@Test
	public void testFindAll() {
		// Given
		List<User> users = TestUtil.createOneUsersList();
		// When
		Mockito.when(userRepository.findAllByOrderByEmailAsc()).thenReturn(users);
		List<User> resultList =  userService.findAll();
		
		// Then
		Mockito.verify(userRepository, Mockito.times(1)).findAllByOrderByEmailAsc();

		Assert.assertNotNull(resultList);
		Assert.assertFalse(resultList.isEmpty());
		Assert.assertThat(resultList, CoreMatchers.is(users));
	}
	
	@Test
	public void testSaveSuccess() {
		// Given
		User user = TestUtil.createDefaultUser();
		// When
		Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
		 userService.save(user);
		
		// Then
		Mockito.verify(userRepository, Mockito.times(1)).findByEmail(user.getEmail());
		Mockito.verify(userRepository, Mockito.times(1)).save(user);
	}
	
	@Test(expected = DuplicateUserException.class)
	public void testSaveDuplicateFail() {
		// Given
		User user = TestUtil.createDefaultUser();
		
		// When
		Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

		userService.save(user);
		Assert.fail("method should fail");

		// Then
		Mockito.verify(userRepository, Mockito.times(1)).findByEmail(user.getEmail());
		Mockito.verify(userRepository, Mockito.times(0)).save(user);
		Mockito.verifyNoMoreInteractions(userRepository);
	}
	
	@Test
	public void testDeleteSuccess() {
		// Given
		User user = TestUtil.createDefaultUser();
		Mockito.when(userRepository.findByEmail(USER_1_EMAIL)).thenReturn(user);
		 userService.deleteByEmail(USER_1_EMAIL);
		
		// Then
		Mockito.verify(userRepository, Mockito.times(1)).findByEmail(USER_1_EMAIL);
		Mockito.verify(userRepository, Mockito.times(1)).delete(user);
	}
	
	@Test
	public void testDeleteNonExisting() {
		// Given
		Mockito.when(userRepository.findByEmail(QUERY_PARAM_NON_EXISTING_EMAIL)).thenReturn(null);
		userService.deleteByEmail(QUERY_PARAM_NON_EXISTING_EMAIL);
		
		// Then
		Mockito.verify(userRepository, Mockito.times(1)).findByEmail(QUERY_PARAM_NON_EXISTING_EMAIL);
		verifyNoMoreInteractions(userRepository);
	}
}
