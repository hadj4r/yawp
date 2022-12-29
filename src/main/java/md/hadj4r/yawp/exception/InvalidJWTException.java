package md.hadj4r.yawp.exception;

import org.springframework.http.HttpStatus;

public class InvalidJWTException extends HttpException {

    public InvalidJWTException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
