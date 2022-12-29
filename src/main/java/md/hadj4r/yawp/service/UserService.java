package md.hadj4r.yawp.service;

import java.util.UUID;
import md.hadj4r.yawp.api.dto.user.request.UserUpdateParam;
import md.hadj4r.yawp.api.dto.user.response.UserInfo;
import md.hadj4r.yawp.config.security.CustomClaims;

public interface UserService {
    UserInfo getUserById(UUID userId);

    void deleteUser(final String bearerToken, CustomClaims claims);

    UserInfo updateUser(UUID userId, UserUpdateParam userUpdateParam);
}
