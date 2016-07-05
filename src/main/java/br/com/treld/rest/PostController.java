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
import br.com.treld.model.Post;
import br.com.treld.services.PostService;

/**
 * Created by rsouza on 29/06/16.
 */
@RestController
@RequestMapping("${baseUrl}/post/")
public class PostController {

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getAll(@PathVariable("page") int page) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPage(page));
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid Post post) {
		Post postSaved = postService.save(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postSaved.getId())
				.toUri();
		//No rest, normalmente enviamos ao cliente o endere�o de onde ele pode consultar o recurso criado. Eduardo
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> get(@PathVariable("id") String id) {
		Post post = postService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		postService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") String id,@Valid @RequestBody Post post){
		post.setId(id);
		postService.update(post);
		return ResponseEntity.noContent().build();
	}

}
