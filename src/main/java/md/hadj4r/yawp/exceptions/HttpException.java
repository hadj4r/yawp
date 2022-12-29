package md.hadj4r.yawp.exceptions;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;


    public HttpException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
