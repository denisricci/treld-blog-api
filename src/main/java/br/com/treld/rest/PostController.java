package br.com.treld.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.treld.config.security.RequiresAuthorAuthentication;
import br.com.treld.model.Post;
import br.com.treld.services.PostService;
import io.swagger.annotations.ApiOperation;

/**
 * Created by rsouza on 29/06/16.
 */
@RestController
@RequestMapping("${baseUrl}/post/")
public class PostController {

	public static final String PATH_ID = "/{id}";
	public static final String PATH_URL = "/url/{url}";

	@Autowired
	private PostService postService;

	@ApiOperation(value = "findAllPaginated", nickname = "findAllPaginated")
	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@PathVariable("page") int page) {
		List<Post> posts = postService.getPage(page);
		if(posts.isEmpty()){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPage(page));
	}

	@RequestMapping(value = PATH_ID, method = RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		Post post = postService.findById(id);
		if (post == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(post);
		}
	}

	@RequestMapping(value = PATH_URL, method = RequestMethod.GET)
	public ResponseEntity<Post> findByUrl(@PathVariable("url") String url) {
		Post post = postService.findByUrl(url);
		if (post != null) {
			return ResponseEntity.status(HttpStatus.OK).body(post);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequiresAuthorAuthentication
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Post> save(@Valid @RequestBody Post post) {
		Post postSaved = postService.save(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(PATH_ID).buildAndExpand(postSaved.getId())
				.toUri();
		return ResponseEntity.created(uri).body(postSaved);
	}

	@RequiresAuthorAuthentication
	@RequestMapping(value = PATH_ID, method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		postService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequiresAuthorAuthentication
	@RequestMapping(value = PATH_ID, method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") String id, @Valid @RequestBody Post post) {
		post.setId(id);
		postService.update(post);
		return ResponseEntity.noContent().build();
	}
}
