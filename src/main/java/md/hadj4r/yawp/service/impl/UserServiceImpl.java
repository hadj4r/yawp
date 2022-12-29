package md.hadj4r.yawp.service.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import md.hadj4r.yawp.api.dto.user.request.UserUpdateParam;
import md.hadj4r.yawp.api.dto.user.response.UserInfo;
import md.hadj4r.yawp.config.security.CustomClaims;
import md.hadj4r.yawp.exception.UserUpdateNotPossibleException;
import md.hadj4r.yawp.mapper.UserMapper;
import md.hadj4r.yawp.model.db.User;
import md.hadj4r.yawp.repository.UserRepository;
import md.hadj4r.yawp.service.TokenBlacklistService;
import md.hadj4r.yawp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    public UserInfo getUserById(final UUID userId) {
        // not using optional bcs we are sure that user exists (taking id from JWT)
        final User user = userRepository.getUserWithAddressById(userId);

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

    @Override
    public UserInfo updateUser(final UUID userId, final UserUpdateParam userUpdateParam) {
        final User user = userRepository.getReferenceById(userId);

        updateUser(user, userUpdateParam);

        return UserMapper.INSTANCE.map(user);
    }

    private void updateUser(final User user, final UserUpdateParam userUpdateParam) {
        boolean isUpdated = false;

        if (isNotBlank(userUpdateParam.getFirstName()) && !userUpdateParam.getFirstName().equals(user.getFirstName())) {
            user.setFirstName(userUpdateParam.getFirstName());
            isUpdated = true;
        }

        if (isNotBlank(userUpdateParam.getLastName()) && !userUpdateParam.getLastName().equals(user.getLastName())) {
            user.setLastName(userUpdateParam.getLastName());
            isUpdated = true;
        }

        if (isNotBlank(userUpdateParam.getAbout()) && !userUpdateParam.getAbout().equals(user.getAbout())) {
            user.setAbout(userUpdateParam.getAbout());
            isUpdated = true;
        }

        if (isUpdated) {
            userRepository.save(user);
            return;
        }

        throw new UserUpdateNotPossibleException("Please provide at least one valid field to update", HttpStatus.EXPECTATION_FAILED);
    }
}
