package br.com.treld.controller;

import br.com.treld.TreldTest;
import br.com.treld.model.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by edubranquinho on 29/07/16.
 */
@TreldTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PostControllerTest extends AbstractControllerTest{

	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	private static final String postJson = " { \"title\":\"Creating rest api\", \"body\":\"open your ide...\" } ";
	private static final String POST_API = "/api/post/";

	
	@Before
	public void setup() throws Exception {
		this.mockMvc = getMockMvc();
		doLogin(mockMvc);
	}


	/*
	 * This test validates the URI that is generated when a post is created. It
	 * also validates if the post was really created.
	 * 
	 * @Author: Eduardo
	 */
	@Test
	public void validateUriPost() throws Exception {
		ResultActions result = mockMvc.perform(post(POST_API).content(postJson)
				.contentType(MediaType.APPLICATION_JSON).session(getSession()))
				.andExpect(status().isCreated());
		String localResourceCreated = result.andReturn().getResponse().getRedirectedUrl();
		ResultActions resultSearch = mockMvc.perform(get(localResourceCreated).session(getSession()));
		String responseString = resultSearch.andReturn().getResponse().getContentAsString();
		Post postCreated = mapper.readValue(responseString, Post.class);
		assertTrue("Creating rest api".equals(postCreated.getTitle()));
		deletePost(postCreated.getId());
	}

	private void deletePost(String id) throws Exception {
		mockMvc.perform(delete(POST_API + id).session(getSession()));
	}

	private Post createPostToUseInTest() throws Exception {
		ResultActions result = mockMvc.perform(post(POST_API).content(postJson)
				.contentType(MediaType.APPLICATION_JSON).session(getSession()));
		String postAsString = result.andReturn().getResponse().getContentAsString();
		return mapper.readValue(postAsString, Post.class);
	}

	@Test
	public void validateModification() throws Exception {
		Post postCreatedInTest = createPostToUseInTest();
		String postJsonAltered = " { \"title\":\"Creating rest api part 2\", \"body\":\"open your eclipse...\" } ";
		mockMvc.perform(put(POST_API + postCreatedInTest.getId()).session(getSession())
				.content(postJsonAltered).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
		ResultActions resultSearch = mockMvc
				.perform(get(POST_API + postCreatedInTest.getId()).session(getSession()))
				.andExpect(status().isOk());
		String responseString = resultSearch.andReturn().getResponse().getContentAsString();
		Post postModified = mapper.readValue(responseString, Post.class);
		assertEquals(postModified.getTitle(), "Creating rest api part 2");
		deletePost(postCreatedInTest.getId());
	}

	@Test
	public void validateDeletion() throws Exception {
		Post postCreatedInTest = createPostToUseInTest();
		mockMvc.perform(delete(POST_API + postCreatedInTest.getId()).session(getSession()))
				.andExpect(status().isNoContent());
	}

	@Test
	public void findByTags() throws Exception {
		Post post = new Post();
        post.setTitle("Test post");
        post.setBody("Body of post");
        post.setTags(Arrays.asList("Java", "Test"));

        String postJson = mapper.writeValueAsString(post);

        String postAsString = mockMvc.perform(post(POST_API).content(postJson)
                .contentType(MediaType.APPLICATION_JSON).session(getSession()))
                .andReturn().getResponse().getContentAsString();

        post = mapper.readValue(postAsString, Post.class);

        String response = mockMvc.perform(get(POST_API + "/tags?tags=Java&page=1")).andReturn().getResponse().getContentAsString();

        Post[] posts = mapper.readValue(response, Post[].class);

        mockMvc.perform(delete(POST_API + post.getId()).session(getSession()))
				.andExpect(status().isNoContent());

        assertEquals(1, posts.length);
        assertEquals(post.getId(), posts[0].getId());
    }

}
