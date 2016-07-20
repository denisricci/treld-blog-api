package br.com.treld;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by edubranquinho on 29/07/16.
 */
@TreldTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUsingWebAppContextSetup {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private Filter springSecurityFilterChain;

	private MockMvc mockMvc;
	private HttpSession session;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain)
				.build();
		login();
	}

	public void login() throws Exception {
		this.session = mockMvc.perform(post("/login").param("username", "treld").param("password", "treld")).andReturn()
				.getRequest().getSession();
	}

	/*
	 * This test validates the URI that is generated when a post is created. It
	 * also validates if the post was really created.
	 * 
	 * @Author: Eduardo
	 */
	@Test
	public void validateUriPost() throws Exception {
		String post = " { \"title\":\"Creating rest api\", \"body\":\"open your ide...\" } ";
		ResultActions result = mockMvc.perform(post("/api/post/").content(post).contentType(MediaType.APPLICATION_JSON)
				.session((MockHttpSession) session)).andExpect(status().isCreated());		
		String localResourceCreated = result.andReturn().getResponse().getRedirectedUrl();
		ResultActions resultSearch = mockMvc.perform(get(localResourceCreated).session((MockHttpSession) session));
		String responseString = resultSearch.andReturn().getResponse().getContentAsString();
		assertTrue(responseString.contains("Creating rest api"));
	}

}
