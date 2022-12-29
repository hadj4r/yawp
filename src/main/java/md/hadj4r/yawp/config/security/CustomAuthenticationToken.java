package md.hadj4r.yawp.config.security;

import io.jsonwebtoken.Claims;
import java.util.HashSet;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final Claims claims;

    public CustomAuthenticationToken(final String login, final Claims claims) {
        super(login, "", new HashSet<>());
        this.claims = claims;
    }
}
