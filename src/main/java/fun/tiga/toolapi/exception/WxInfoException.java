package fun.tiga.toolapi.exception;


public class WxInfoException extends RuntimeException{
    public WxInfoException(String message) {
        super(message);
    }

    public WxInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getStatus() {
        return 1001;
    }
}
