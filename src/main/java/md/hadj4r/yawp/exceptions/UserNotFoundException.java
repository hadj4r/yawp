package md.hadj4r.yawp.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends HttpException {
    public UserNotFoundException(final String message, final HttpStatus status) {
        super(message, status);
    }
}
