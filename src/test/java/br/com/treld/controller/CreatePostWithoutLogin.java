package br.com.treld.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.treld.TreldTest;

/**
 * Created by edubranquinho on 04/08/16.
 */
@TreldTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CreatePostWithoutLogin {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private Filter springSecurityFilterChain;
	private MockMvc mockMvc;
	private static final String postJson = " { \"title\":\"Creating rest api\", \"body\":\"open your ide...\" } ";

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain)
				.build();
	}

	@Test
	public void validateFailToCreatePostWithoutLogin() throws Exception {
		mockMvc.perform(post("/api/post/").content(postJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}
}
