package md.hadj4r.yawp.exception;

import org.springframework.http.HttpStatus;

public class UserUpdateNotPossibleException extends HttpException {
    public UserUpdateNotPossibleException(final String message, final HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
