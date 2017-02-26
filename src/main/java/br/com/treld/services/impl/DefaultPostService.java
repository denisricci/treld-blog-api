package br.com.treld.services.impl;

import br.com.treld.exceptions.TreldRuntimeException;
import br.com.treld.model.Post;
import br.com.treld.repository.PostRepository;
import br.com.treld.services.PostService;
import br.com.treld.services.PostValidationService;
import br.com.treld.validator.ValidationResult;
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

    @Autowired
    private PostValidationService postValidationService;

    @Override
    public Post save(Post post) {
        ValidationResult validationResult = postValidationService.validate(post);

        if(validationResult.hasErrors()){
            String errorMessage = validationResult.getErrors().stream().reduce("", (a,b) -> a + " | " + b);
            throw new TreldRuntimeException(errorMessage);
        }

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
    public List<Post> findByTags(List<String> tags, int page) {
        return findByTags(tags, page, 20);
    }

    @Override
    public List<Post> findByTags(List<String> tags, int page, int pageSize) {
        return postRepository.findByTagsIn(tags, new PageRequest(page -1, pageSize)).getContent();
    }

    @Override
    public List<Post> getPage(int pageNumber) {
        return getPage(pageNumber, 20);
    }

    @Override
    public List<Post> getPage(int pageIndex, int pageSize) {
        Pageable page = new PageRequest(pageIndex-1, pageSize);
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
