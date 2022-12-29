package md.hadj4r.yawp.exception;

import org.springframework.http.HttpStatus;

public class PasswordsNotEqualException extends HttpException {
    public PasswordsNotEqualException(final String message, final HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
