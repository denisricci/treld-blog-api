package br.com.treld.exceptions;

/**
 * Created by rsouza on 10/07/16.
 */
public class TreldException extends Exception {

	private static final long serialVersionUID = 1L;

	public TreldException (){
        super();
    }

    public TreldException(Throwable e){
        super(e);
    }

}
