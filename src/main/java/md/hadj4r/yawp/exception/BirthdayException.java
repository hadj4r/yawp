package md.hadj4r.yawp.exception;

import org.springframework.http.HttpStatus;

public class BirthdayException extends HttpException {
    public BirthdayException(final String message, final HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
