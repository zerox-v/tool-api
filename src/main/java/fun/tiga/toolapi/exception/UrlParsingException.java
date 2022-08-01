package fun.tiga.toolapi.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class UrlParsingException extends RuntimeException {

    public UrlParsingException(String message) {
        super(message);
    }

    public UrlParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getStatus() {
        return 1000;
    }
}
