package br.com.treld.model;

/**
 * Created by rsouza on 13/07/16.
 */
public class Tag {

    private String code;

    @Override
    public String toString() {
        return code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
