package md.hadj4r.yawp.exception;

import org.springframework.http.HttpStatus;

public class AddressUpdateNotPossibleException extends HttpException {
    public AddressUpdateNotPossibleException(final String message, final HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
