package md.hadj4r.yawp.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import md.hadj4r.yawp.service.TokenBlacklistService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private static final String BEARER = "Bearer ";

    private final JwtTokenService jwtTokenService;

    private final TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = header.substring(7);
        if (!jwtTokenService.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (tokenBlacklistService.exists(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Get user identity and set it on the spring security context
        SecurityContextHolder.getContext().setAuthentication(jwtTokenService.getAuthentication(token));

        filterChain.doFilter(request, response);
    }
}
