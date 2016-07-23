package br.com.treld.infrastructure;

import br.com.treld.exceptions.FileException;
import br.com.treld.model.FileLocation;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by rsouza on 23/07/16.
 */
public interface FilePersistenceStrategy {

    FileLocation save(MultipartFile file) throws FileException;

    InputStream get(String id) throws FileException;

}
