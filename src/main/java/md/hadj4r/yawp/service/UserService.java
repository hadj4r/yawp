package md.hadj4r.yawp.service;

import java.util.UUID;
import md.hadj4r.yawp.api.dto.user.request.UserUpdateParam;
import md.hadj4r.yawp.config.security.CustomClaims;
import md.hadj4r.yawp.model.db.User;

public interface UserService {
    User getUserById(UUID userId);

    void deleteUser(final String bearerToken, CustomClaims claims);

    User updateUser(UUID userId, UserUpdateParam userUpdateParam);
}
