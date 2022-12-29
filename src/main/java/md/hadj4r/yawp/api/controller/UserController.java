package md.hadj4r.yawp.api.controller;

import lombok.RequiredArgsConstructor;
import md.hadj4r.yawp.api.dto.user.response.UserInfo;
import md.hadj4r.yawp.config.security.CustomClaims;
import md.hadj4r.yawp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserInfo> getUser(CustomClaims token) {
        final UserInfo userInfo = userService.getUserById(token.getUserId());

        return ResponseEntity.ok(userInfo);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestHeader(AUTHORIZATION) String bearerToken,
                                           CustomClaims claims) {
        userService.deleteUser(bearerToken, claims);

        return ResponseEntity.noContent().build();
    }
}
