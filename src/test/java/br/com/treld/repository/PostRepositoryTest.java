package br.com.treld.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.treld.TreldBlogApplicationTests;
import br.com.treld.model.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TreldBlogApplicationTests.class)
public class PostRepositoryTest {

	@Autowired
	private PostRepository repository;

	@Test
	public void testingPostPersistence() {
		Post postToPersist = buildPost();
		repository.save(postToPersist);
		Post postPersisted = repository.findOne(postToPersist.getId());

		assertNotNull(postToPersist.getId());
		assertEquals(postPersisted.getTitle(), postToPersist.getTitle());
		assertEquals(postPersisted.getBody(), postToPersist.getBody());
		assertEquals(postPersisted.getPublicationDate(), postToPersist.getPublicationDate());
		assertEquals(postPersisted.getCreationDate(), postToPersist.getCreationDate());

		repository.delete(postPersisted);
	}
	
	@Test
	public void findAllPostOrderedByPublicationDate(){
		Post post1 = buildPost();
		post1.setPublicationDate(getCurrentDatePlusParam(200));
		Post post2 = buildPost();
		post2.setPublicationDate(getCurrentDatePlusParam(300));
		Post post3 = buildPost();
		post3.setPublicationDate(getCurrentDatePlusParam(400));
		Post post4 = buildPost();
		post4.setPublicationDate(getCurrentDatePlusParam(500));		
		Post post5 = buildPost();		
		post5.setPublicationDate(getCurrentDatePlusParam(600));
		
		List<Post> unOrderedList = new ArrayList<>();
		unOrderedList.add(post1);
		unOrderedList.add(post2);
		unOrderedList.add(post3);
		unOrderedList.add(post4);
		unOrderedList.add(post5);
		
		repository.save(unOrderedList);
		
		List<Post> posts = repository.findAll(new Sort("publicationDate"));
				
	}
	
	private Date getCurrentDatePlusParam(long milisecond){		
		long totalMilisecond = new Date().getTime() + milisecond;
		return new Date(totalMilisecond);
	}

	private Post buildPost() {
		Post post = new Post();
		post.setTitle("Test post");
		post.setBody("This post was created to test with jUnit");
		post.setCreationDate(new Date());
		post.setPublicationDate(new Date());
		return post;
	}
}
