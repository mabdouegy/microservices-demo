package com.mohamedomar.codechallenge.microservices.users.controller;

import static com.mohamedomar.codechallenge.microservices.users.constants.TestConstants.QUERY_PARAM_NON_EXISTING_EMAIL;
import static com.mohamedomar.codechallenge.microservices.users.constants.TestConstants.QUERY_PARAM_NON_EXISTING_USER_PARTIAL_NAME;
import static com.mohamedomar.codechallenge.microservices.users.constants.TestConstants.QUERY_PARAM_USER_1_PARTIAL_NAME;
import static com.mohamedomar.codechallenge.microservices.users.constants.TestConstants.USER_1_EMAIL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mohamedomar.codechallenge.microservices.users.config.WebTestConfig;
import com.mohamedomar.codechallenge.microservices.users.service.UserService;
import com.mohamedomar.codechallenge.microservices.users.util.TestUtil;

/**
 * Test class for users rest controller
 * 
 * @author Mohamed Omar
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebTestConfig.class })
@WebAppConfiguration
public class UserControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private UserService userService;

	@Before
	public void setUp() {
		Mockito.reset(userService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testFindAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(userService, Mockito.times(1)).findAll();
	}

	@Test
	public void testFindExistingUserByEmail() throws Exception {
		Mockito.when(userService.findByEmail(USER_1_EMAIL)).thenReturn(TestUtil.createDefaultUser());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/user/email/").param("email", USER_1_EMAIL)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(userService, Mockito.times(1)).findByEmail(USER_1_EMAIL);
	}

	@Test
	public void testFindNonExistingUserByEmail() throws Exception {
		Mockito.when(userService.findByEmail(QUERY_PARAM_NON_EXISTING_EMAIL)).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/user/email/")
				.param("email", QUERY_PARAM_NON_EXISTING_EMAIL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());

		Mockito.verify(userService, Mockito.times(1)).findByEmail(QUERY_PARAM_NON_EXISTING_EMAIL);
	}

	@Test
	public void testFindExistingUserByPartialName() throws Exception {
		Mockito.when(userService.findByNameIgnoreCase(QUERY_PARAM_USER_1_PARTIAL_NAME))
				.thenReturn(TestUtil.createOneUsersList());

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/user/name/" + QUERY_PARAM_USER_1_PARTIAL_NAME)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.verify(userService, Mockito.times(1)).findByNameIgnoreCase(QUERY_PARAM_USER_1_PARTIAL_NAME);
	}

	@Test
	public void testFindNonExistingUserByPartialName() throws Exception {
		Mockito.when(userService.findByNameIgnoreCase(QUERY_PARAM_NON_EXISTING_USER_PARTIAL_NAME))
				.thenReturn(TestUtil.createEmptyUsersList());

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/users/user/name/" + QUERY_PARAM_NON_EXISTING_USER_PARTIAL_NAME)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());

		Mockito.verify(userService, Mockito.times(1)).findByNameIgnoreCase(QUERY_PARAM_NON_EXISTING_USER_PARTIAL_NAME);
	}

	/*
	 * From controller perspective, same behavior if email exists or no, Hence one
	 * test method is enough
	 */
	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/user/email/").param("email", USER_1_EMAIL)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.verify(userService, Mockito.times(1)).deleteByEmail(USER_1_EMAIL);
	}

}
