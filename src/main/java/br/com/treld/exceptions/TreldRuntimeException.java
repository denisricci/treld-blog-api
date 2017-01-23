package br.com.treld.exceptions;

/**
 * Created by rsouza on 23/07/16.
 */
public class TreldRuntimeException extends RuntimeException{

    public TreldRuntimeException(Throwable e){
        super(e);
    }

    public TreldRuntimeException(String message){
        super(message);
    }
}
