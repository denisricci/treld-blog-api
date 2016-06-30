package br.com.treld.repository;

import org.springframework.cglib.core.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.treld.model.Post;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String>{

    Page<Post> findAllByOrderByPublicationDateDesc(Pageable pageable);

}
