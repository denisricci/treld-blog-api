package br.com.treld.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.treld.model.Author;
import br.com.treld.model.User;
import br.com.treld.services.AuthorService;

/**
 * Created by rsouza on 23/07/16.
 */
public class AbstractControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private AuthorService authorService;

    @Value("${baseUrl}")
    private String baseUrl;

    private User author;
    private MockHttpSession session;

    public MockMvc getMockMvc(){
        return MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain).build();
    }

    public void doLogin(MockMvc mvc) throws Exception {
        session = new MockHttpSession();
        String username = "treld";
        String password = "treld";
        author = new Author(username, password);
        authorService.save(author);
        mvc.perform(post("/login")
                .param("username", username)
                .param("password", password)
                .session(session))
                .andExpect(status().is2xxSuccessful());
    }

    public User getAuthor() {
        return author;
    }

    public MockHttpSession getSession() {
        return session;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
