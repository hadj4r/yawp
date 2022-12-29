package md.hadj4r.yawp.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import md.hadj4r.yawp.exceptions.InvalidJWTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import static io.jsonwebtoken.Jwts.parserBuilder;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;
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

    public String createToken(final String login) {
        final Claims claims = Jwts.claims().setSubject(login);

        final SecretKey key = Keys.hmacShaKeyFor(secretKey);
        final Date now = new Date();
        final Date lifetime = new Date(now.getTime() + lifetimeInMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(lifetime)
                .signWith(key, HS256)
                .compact();
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
        return parserBuilder()
                .setSigningKey(secretKey)
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
