package br.com.treld.services.impl;

import br.com.treld.model.Post;
import br.com.treld.model.PostsPerPage;
import br.com.treld.repository.PostRepository;
import br.com.treld.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rsouza on 29/06/16.
 */
@Component
public class DefaultPostService implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post findById(String id) {
        return postRepository.findOne(id);
    }
    
    @Override
	public Post findByUrl(String url) {		
		return postRepository.findByUrl(url);
	}

    @Override
    public List<Post> getPage(int pageNumber) {
        return getPage(pageNumber, 10);
    }

    @Override
    public List<Post> getPage(int pageIndex, int pageSize) {
        Pageable page = new PageRequest(pageIndex-1, pageSize);
        List<Post> posts =  postRepository.findAllByOrderByPublicationDateDesc(page).getContent();
        PostsPerPage ppp = new PostsPerPage();
        ppp.setData(posts);
        ppp.setDraw(pageIndex++);
        ppp.setRecordsTotal(postRepository.count());
        return posts;
    }

	@Override
	public void delete(String id) {
		postRepository.delete(id);
	}

	@Override
	public void update(Post post) {
		postRepository.save(post);
	}

    @Override
    public Long count() { return postRepository.count(); }
}
