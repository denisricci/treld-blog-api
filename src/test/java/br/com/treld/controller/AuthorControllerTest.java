package br.com.treld.controller;

import static br.com.treld.utils.JsonUtils.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.treld.TreldTest;
import br.com.treld.model.Author;

@TreldTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorControllerTest extends AbstractControllerTest {

	private MockMvc mockMvc;

	private static final String AUTHOR_PATH = "/api/author";
	private ObjectMapper mapper = new ObjectMapper();
	
	private static final String USERNAME = "dricci";
	private static final String PASSWORD = "dricci";
	private static final String EMAIL = "denis.ricci@teste.com";
	private static final String ABOUT = "testando";

	@Before
	public void setup() throws Exception {
		this.mockMvc = getMockMvc();
		doLogin(mockMvc);		
	}

	@Test
	public void validateCreateAuthor() throws Exception {
		ResultActions resultCreate = mockMvc.perform(post(AUTHOR_PATH).content(asJsonString(buildAuthor()))
				.contentType(MediaType.APPLICATION_JSON).session(getSession())).andExpect(status().isCreated());
		String localResourceCreated = resultCreate.andReturn().getResponse().getRedirectedUrl();
		ResultActions resultSearch = mockMvc.perform(get(localResourceCreated).session(getSession()));
		String responseString = resultSearch.andReturn().getResponse().getContentAsString();
		Author authorCreated = mapper.readValue(responseString, Author.class);
		Assert.assertEquals(USERNAME, authorCreated.getUsername());
		Assert.assertNull(authorCreated.getPassword());
		Assert.assertEquals(EMAIL, authorCreated.getEmail());
		Assert.assertEquals(ABOUT, authorCreated.getAbout());
		deleteAuthor(authorCreated.getUsername());
	}
	
	private void deleteAuthor(String username) throws Exception {
		mockMvc.perform(delete(AUTHOR_PATH + "/" + username).session(getSession()));
	}

	private Author buildAuthor() {
		Author author = new Author(USERNAME, PASSWORD);
		author.setEmail(EMAIL);
		author.setAbout(ABOUT);		
		return author;
	}

}
