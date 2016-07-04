package br.com.treld.services.impl;

import br.com.treld.model.Post;
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
		Post postSaved = postRepository.save(post);
		return postSaved;
	}

	@Override
	public Post findById(String id) {
		return postRepository.findOne(id);
	}

	@Override
	public List<Post> getPage(int pageNumber) {
		Pageable page = new PageRequest(pageNumber - 1, 20);
		return postRepository.findAllByOrderByPublicationDateDesc(page).getContent();
	}

	@Override
	public void delete(String id) {
		postRepository.delete(id);
	}

	@Override
	public void update(Post post) {
		postRepository.save(post);
	}
}
