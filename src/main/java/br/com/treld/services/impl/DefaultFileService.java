package br.com.treld.services.impl;

import br.com.treld.exceptions.FileException;
import br.com.treld.exceptions.TreldRuntimeException;
import br.com.treld.model.FileLocation;
import br.com.treld.infrastructure.FilePersistenceStrategy;
import br.com.treld.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by rsouza on 23/07/16.
 */
@Component
public class DefaultFileService implements FileService {

    @Autowired
    private FilePersistenceStrategy filePersistenceStrategy;

    @Override
    public FileLocation saveFile(MultipartFile file) throws FileException{
        return filePersistenceStrategy.save(file);
    }

    @Override
    public InputStream get(String id) {
        try {
            return filePersistenceStrategy.get(id);
        } catch (FileException e) {
            throw new TreldRuntimeException(e);
        }
    }
}
