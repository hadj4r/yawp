package md.hadj4r.yawp.service.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import md.hadj4r.yawp.api.dto.user.response.UserInfo;
import md.hadj4r.yawp.config.security.CustomClaims;
import md.hadj4r.yawp.mapper.UserMapper;
import md.hadj4r.yawp.model.db.User;
import md.hadj4r.yawp.repository.UserRepository;
import md.hadj4r.yawp.service.TokenBlacklistService;
import md.hadj4r.yawp.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    public UserInfo getUserById(final UUID userId) {
        // not using optional bcs we are sure that user exists (taking id from JWT)
        final User user = userRepository.getReferenceById(userId);

        return UserMapper.INSTANCE.map(user);
    }

    @Override
    public void deleteUser(final String bearerToken, final CustomClaims claims) {
        // GDPR and stuff so no soft deletion ;(
        userRepository.deleteById(claims.getUserId());

        // get the difference in millis between now and token expiration
        final long ttl = claims.getExpiration().getTime() - System.currentTimeMillis();

        tokenBlacklistService.deleteToken(bearerToken.substring(7), ttl);
    }
}
