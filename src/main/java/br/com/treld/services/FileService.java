package br.com.treld.services;

import br.com.treld.exceptions.FileException;
import br.com.treld.model.FileLocation;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by rsouza on 23/07/16.
 */
public interface FileService {

    /**
     * Save a file using the properly strategy
     * @return FileLocation with the data
     * @throws FileException
     */
    FileLocation saveFile(MultipartFile file) throws FileException;

    InputStream get(String id);
}
