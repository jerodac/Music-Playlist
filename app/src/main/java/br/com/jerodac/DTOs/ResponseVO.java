package br.com.jerodac.DTOs;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Jean Rodrigo Dalbon Cunha on 13/01/17.
 */
public class ResponseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("error")
    private Error error;

    public Error getError() {
        return error;
    }
}
