package br.com.treld.services;

import br.com.treld.model.Post;

import java.util.List;

/**
 * Created by rsouza on 29/06/16.
 */
public interface PostService {

    void save(Post post);
    Post findById(String id);
    List<Post> getPage(int page);

}
