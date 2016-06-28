package br.com.treld.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.treld.model.Post;

public interface PostRepository extends MongoRepository<Post, String>{
	
	public List<Post> findAll(Sort sort);
}
