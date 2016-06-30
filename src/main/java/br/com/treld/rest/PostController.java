package br.com.treld.rest;

import br.com.treld.model.Post;
import br.com.treld.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by rsouza on 29/06/16.
 */
@RestController
@RequestMapping("${baseUrl}/post/")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public List<Post> getAll(@PathVariable("page") int page){
        return postService.getPage(page);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void get(Post post){
        postService.save(post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Post get(@PathVariable("id") String id){

        return postService.findById(id);
    }

}
