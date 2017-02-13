package br.com.treld.repository;

import br.com.treld.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String>{

    Page<Post> findAllByOrderByPublicationDateDesc(Pageable pageable);
    Post findByUrl(String url);
    Page<Post> findByTagsIn(List<String> tags, Pageable pageable);

}
