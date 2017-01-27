package br.com.treld.controller;

import java.net.URI;

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
import br.com.treld.model.User;
import br.com.treld.services.AuthorService;

/**
 * Created by edubranquinho on 25/07/16.
 */
@RestController
@RequestMapping("${baseUrl}/author/")
public class AuthorController {

	public static final String PATH_ID = "/{username}";

	@Autowired
	AuthorService authorService;

	@RequiresAuthorAuthentication
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<User> save(@Valid @RequestBody User author) {
		User authorSaved = authorService.save(author);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(PATH_ID)
				.buildAndExpand(authorSaved.getUsername()).toUri();
		return ResponseEntity.created(uri).body(authorSaved);
	}
	
	@RequiresAuthorAuthentication
	@RequestMapping(value = PATH_ID, method = RequestMethod.GET)
	public ResponseEntity<?> findByUsername(@PathVariable("username") String username) {
		User author = authorService.findByUsername(username);
		if (author != null) {
			return ResponseEntity.status(HttpStatus.OK).body(author);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}