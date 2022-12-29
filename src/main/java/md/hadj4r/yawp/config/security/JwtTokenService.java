package md.hadj4r.yawp.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import md.hadj4r.yawp.exception.InvalidJWTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import static io.jsonwebtoken.Jwts.parserBuilder;
import static java.util.Base64.getEncoder;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
public class JwtTokenService {

    private final long lifetimeInMillis;

    private final byte[] secretKey;


    public JwtTokenService(@Value("${security.jwt.token.lifetime:3600000}") long lifetimeInMillis,
                           @Value("${security.jwt.token.secretKey}") String secretKey) {
        this.lifetimeInMillis = lifetimeInMillis;
        this.secretKey = getEncoder().encode(secretKey.getBytes());
    }

    public String createToken(final String login, final UUID userId) {
        final Key key = Keys.hmacShaKeyFor(secretKey);

        final long now = System.currentTimeMillis();
        final Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId.toString());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(login)
                .setExpiration(new Date(now + lifetimeInMillis))
                .setIssuedAt(new Date(now))
                .addClaims(claims)
                .signWith(key).compact();
    }

    public boolean validate(final String token) {
        try {
            getAllClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJWTException("Expired or invalid JWT token", FORBIDDEN);
        }
    }

    private Claims getAllClaims(final String token) {
        final Key key = Keys.hmacShaKeyFor(secretKey);

        return parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody();
    }

    public String getLogin(final String token) {
        return getAllClaims(token).getSubject();
    }

    public Authentication getAuthentication(final String token) {
        final String login = getLogin(token);
        return new CustomAuthenticationToken(login, getAllClaims(token));
    }
}
