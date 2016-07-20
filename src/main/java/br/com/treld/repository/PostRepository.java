package br.com.treld.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.treld.model.Post;

public interface PostRepository extends MongoRepository<Post, String>{

    Page<Post> findAllByOrderByPublicationDateDesc(Pageable pageable);
    Post findByUrl(String url);

}
