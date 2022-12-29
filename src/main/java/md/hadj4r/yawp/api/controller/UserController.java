package md.hadj4r.yawp.api.controller;

import lombok.RequiredArgsConstructor;
import md.hadj4r.yawp.api.dto.user.request.UserUpdateParam;
import md.hadj4r.yawp.api.dto.user.response.UserInfo;
import md.hadj4r.yawp.assembler.UserModelAssembler;
import md.hadj4r.yawp.config.security.CustomClaims;
import md.hadj4r.yawp.model.db.User;
import md.hadj4r.yawp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserModelAssembler userModelAssembler;

    @GetMapping(produces = HAL_JSON_VALUE)
    public ResponseEntity<UserInfo> getUser(CustomClaims token) {
        final User user = userService.getUserById(token.getUserId());
        final UserInfo userInfo = userModelAssembler.toModel(user);

        return ResponseEntity.ok(userInfo);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestHeader(AUTHORIZATION) String bearerToken,
                                           CustomClaims claims) {
        userService.deleteUser(bearerToken, claims);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(produces = HAL_JSON_VALUE)
    public ResponseEntity<UserInfo> updateUser(CustomClaims claims, @RequestBody UserUpdateParam userUpdateParam) {
        final User user = userService.updateUser(claims.getUserId(), userUpdateParam);
        final UserInfo userInfo = userModelAssembler.toModel(user);

        return ResponseEntity.ok(userInfo);
    }
}
