package br.com.treld.config;

import br.com.treld.config.security.AuthorUserDetails;
import br.com.treld.model.Author;
import br.com.treld.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by rsouza on 16/07/16.
 */
@Service
public class MongoDBAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(MongoDBAuthenticationProvider.class);

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Assert.notNull(userDetails.getPassword(), "Password of user " + userDetails.getUsername() + " is null");

        //PasswordEncoder encoder = new StandardPasswordEncoder();
        //String encodedPassword = encoder.encode(authentication.getCredentials().toString());

        String encodedPassword = authentication.getCredentials().toString();

       if(!userDetails.getPassword().equals(encodedPassword)){
           throw new UsernameNotFoundException("Authentication Failed");
       }

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        Author author = authorRepository.findByUsername(username);

        if(author == null){
            String errorMessage = "Author with username " + username + " not found";
            LOG.debug(errorMessage);
            throw new UsernameNotFoundException(errorMessage);
        }

        return new AuthorUserDetails(author);
    }
}
