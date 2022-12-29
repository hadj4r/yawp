package md.hadj4r.yawp.exception;

import org.springframework.http.HttpStatus;

public class CredentialsNotFoundException extends HttpException {

    public CredentialsNotFoundException(String message) {
        this(message, HttpStatus.NOT_FOUND);
    }

    private CredentialsNotFoundException(final String message, final HttpStatus status) {
        super(message, status);
    }
}
