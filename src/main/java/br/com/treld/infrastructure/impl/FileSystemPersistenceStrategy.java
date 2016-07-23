package br.com.treld.infrastructure.impl;

import br.com.treld.exceptions.FileException;
import br.com.treld.infrastructure.FilePersistenceStrategy;
import br.com.treld.model.FileLocation;
import br.com.treld.repository.FileLocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by rsouza on 23/07/16.
 */
@Component
public class FileSystemPersistenceStrategy implements FilePersistenceStrategy {

    private static final String FILE_SEPARATOR = File.separator;
    private static final Logger LOG = LoggerFactory.getLogger(FileSystemPersistenceStrategy.class);

    @Value("${file.upload.base.dir}")
    private String baseDir;

    @Autowired
    private FileLocationRepository fileLocationRepository;

    @Override
    public FileLocation save(MultipartFile file) throws FileException{
        FileLocation fileLocation = new FileLocation(file);
        fileLocationRepository.save(fileLocation);

        ensureFolder(baseDir);

        String folder = baseDir + FILE_SEPARATOR + fileLocation.getId();

        File systemFile = new File(folder);
        try {
            file.transferTo(systemFile);
            fileLocation.setRelativePath(fileLocation.getId());
            fileLocation.setAbsolutePath(folder);
            fileLocationRepository.save(fileLocation);
            return fileLocation;
        } catch (IOException e) {
            LOG.error("Error trying to save file to file system, removing FileLocation: " + fileLocation.getId());
            fileLocationRepository.delete(fileLocation);
            throw new FileException("Error trying to save file to: " + folder, e);
        }
    }

    private void ensureFolder(String baseDir) throws FileException{
        File folder = new File(baseDir);
        if(folder.exists())
            return;

        boolean success = folder.mkdirs();
        if(!success)
            throw new FileException("Error trying to create folder {" + baseDir + "} please check permissions");
    }

    @Override
    public InputStream get(String id) throws FileException {
        FileLocation fileLocation = fileLocationRepository.findOne(id);
        try {
            return new FileInputStream(fileLocation.getAbsolutePath());
        } catch (FileNotFoundException e) {
            throw new FileException("Error trying to retrieve file from file system ", e);
        }
    }
}
