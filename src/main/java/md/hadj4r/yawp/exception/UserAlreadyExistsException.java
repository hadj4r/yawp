package md.hadj4r.yawp.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends HttpException {
    public UserAlreadyExistsException(final String message, final HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
