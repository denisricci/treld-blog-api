package br.com.treld.exceptions;

import java.io.IOException;

/**
 * Created by rsouza on 23/07/16.
 */
public class FileException extends IOException {

    public FileException(String message){
        super(message);
    }

    public FileException(String message, Exception e){
        super(message, e);
    }

}
