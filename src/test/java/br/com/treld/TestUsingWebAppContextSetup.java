package br.com.treld;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TestUsingWebAppContextSetup extends TreldBlogApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void validatePost() throws Exception {
		ResultActions resultado = mockMvc.perform(post("/login").param("username", "treld")
				.param("password", "treld")).andExpect(status().isOk());
		System.out.println(resultado);
	}

}
