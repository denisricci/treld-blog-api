package br.com.treld.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by rsouza on 23/07/16.
 */
@Document(collection = "filesLocation")
public class FileLocation {

    @Id
    private String id;
    private String relativePath;
    private String absolutePath;
    private String originalName;
    private String mimeType;

    @Deprecated
    public FileLocation(){
        // To spring data eyes only
    }

    public FileLocation(MultipartFile multipartFile){
        originalName = multipartFile.getOriginalFilename();
        mimeType = multipartFile.getContentType();
    }

    public String getId() {
        return id;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getOriginalName() {
        return originalName;
    }
}
