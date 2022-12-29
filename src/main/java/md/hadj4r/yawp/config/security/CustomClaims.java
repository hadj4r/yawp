package md.hadj4r.yawp.config.security;

import io.jsonwebtoken.Claims;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import static md.hadj4r.yawp.utils.Constants.USER_ID;

public class CustomClaims extends UsernamePasswordAuthenticationToken {
    private final Claims claims;

    public CustomClaims(final String login, final Claims claims) {
        super(login, "", new HashSet<>());
        this.claims = claims;
    }

    public UUID getUserId() {
        return UUID.fromString(claims.get(USER_ID, String.class));
    }

    public Date getExpiration() {
        return claims.getExpiration();
    }
}
