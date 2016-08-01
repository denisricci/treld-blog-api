package br.com.treld.services;

import br.com.treld.model.Post;
import br.com.treld.model.PostsPerPage;

import java.util.List;

/**
 * Created by rsouza on 29/06/16.
 */
public interface PostService {

    Post save(Post post);
    Post findById(String id);
    Post findByUrl(String url);
    List<Post> getPage(int page);
    List<Post> getPage(int pageIndex, int pageSize);
    void delete(String id);
    void update(Post post);
    PostsPerPage getPostsPerPage(int page, long draw);
}
