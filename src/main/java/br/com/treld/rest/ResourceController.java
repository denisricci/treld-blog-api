package br.com.treld.rest;

import br.com.treld.config.security.RequiresAuthorAuthentication;
import br.com.treld.exceptions.FileException;
import br.com.treld.model.FileLocation;
import br.com.treld.repository.FileLocationRepository;
import br.com.treld.services.FileService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by rsouza on 23/07/16.
 */
@RestController
@RequestMapping("${baseUrl}/resource")
public class ResourceController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileLocationRepository fileLocationRepository;

    @RequiresAuthorAuthentication
    @ApiOperation(value="resourceUpload", nickname="resourceUpload")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> upload(MultipartFile file) throws FileException {
        FileLocation fileLocation = fileService.saveFile(file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fileLocation.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value="getResource", nickname="getResource")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> get(@PathVariable("id") String id) throws IOException {
        FileLocation fileLocation = fileLocationRepository.findOne(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fileLocation.getMimeType()));

        InputStream is = fileService.get(id);
        return new ResponseEntity<byte[]>(IOUtils.toByteArray(is), headers, HttpStatus.OK);
    }

}
