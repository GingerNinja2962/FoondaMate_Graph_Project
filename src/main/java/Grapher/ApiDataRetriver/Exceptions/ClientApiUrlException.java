package Grapher.ApiDataRetriver.Exceptions;

public class ClientApiUrlException extends Exception {
    private String exceptionCode;

    public ClientApiUrlException(String code, String message) {
        super(message);
        this.setCode(code);
    }

    public ClientApiUrlException(String code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
    }

    public String getCode() {
        return exceptionCode;
    }

    public void setCode(String code) {
        this.exceptionCode = code;
    }

}
