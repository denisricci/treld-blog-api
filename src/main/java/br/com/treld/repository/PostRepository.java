package br.com.treld.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.treld.model.Post;

public interface PostRepository extends MongoRepository<Post, String>{
	
	
}
