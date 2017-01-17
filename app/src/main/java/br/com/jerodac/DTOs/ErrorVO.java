package br.com.jerodac.DTOs;

/**
 * @author Jean Rodrigo Dalbon Cunha on 17/01/17.
 */
public class ErrorVO {

    private String type;
    private String message;
    private int code;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
