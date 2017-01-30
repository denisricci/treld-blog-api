package br.com.treld.services;

import br.com.treld.TreldTest;
import br.com.treld.model.Post;
import br.com.treld.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rsouza on 02/07/16.
 */
@TreldTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void saveTest(){
        Post post = new Post();
        post.setTitle("Post For Test");

        postService.save(post);

        assertNotNull(post.getId());
        Post postFromDb = postService.findById(post.getId());

        assertEquals(post.getId(), postFromDb.getId());
        assertEquals(post.getTitle(), postFromDb.getTitle());
    }

    @Test
    public void findAllPostOrderedByPublicationDate() {
        postRepository.deleteAll();

        Post p2006 = createPostWithYear(2006);
        Post p2007 = createPostWithYear(2007);
        Post p2008 = createPostWithYear(2008);
        Post p2009 = createPostWithYear(2009);
        Post p2010 = createPostWithYear(2010);
        Post p2011 = createPostWithYear(2011);
        Post p2012 = createPostWithYear(2012);
        Post p2013 = createPostWithYear(2013);
        Post p2014 = createPostWithYear(2014);
        Post p2015 = createPostWithYear(2015);
        Post p2016 = createPostWithYear(2016);
        Post p2017 = createPostWithYear(2017);
        Post p2018 = createPostWithYear(2018);
        Post p2019 = createPostWithYear(2019);
        Post p2020 = createPostWithYear(2020);

        postService.save(p2006);
        postService.save(p2007);
        postService.save(p2008);
        postService.save(p2009);
        postService.save(p2010);
        postService.save(p2011);
        postService.save(p2012);
        postService.save(p2013);
        postService.save(p2014);
        postService.save(p2015);
        postService.save(p2016);
        postService.save(p2017);
        postService.save(p2018);
        postService.save(p2019);
        postService.save(p2020);

        List<Post> page1 = postService.getPage(1, 10);
        assertEquals(10, page1.size());
        assertEquals(p2020.getId(), page1.get(0).getId());
        assertEquals(p2019.getId(), page1.get(1).getId());
        assertEquals(p2018.getId(), page1.get(2).getId());
        assertEquals(p2017.getId(), page1.get(3).getId());
        assertEquals(p2016.getId(), page1.get(4).getId());
        assertEquals(p2015.getId(), page1.get(5).getId());
        assertEquals(p2014.getId(), page1.get(6).getId());
        assertEquals(p2013.getId(), page1.get(7).getId());
        assertEquals(p2012.getId(), page1.get(8).getId());
        assertEquals(p2011.getId(), page1.get(9).getId());

        List<Post> page2 = postService.getPage(2, 10);
        assertEquals(5, page2.size());
        assertEquals(p2010.getId(), page2.get(0).getId());
        assertEquals(p2009.getId(), page2.get(1).getId());
        assertEquals(p2008.getId(), page2.get(2).getId());
        assertEquals(p2007.getId(), page2.get(3).getId());
        assertEquals(p2006.getId(), page2.get(4).getId());
        
        postRepository.deleteAll();
    }

    public Post createPostWithYear(int year){
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);
        Post post = new Post();
        post.setTitle("Post from " + date.toString());
        post.setPublicationDate(date.getTime());
        post.buildUrl();
        return post;
    }
}
