package md.hadj4r.yawp.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import md.hadj4r.yawp.api.dto.user.request.UserUpdateParam;
import md.hadj4r.yawp.api.dto.user.response.UserInfo;
import md.hadj4r.yawp.assembler.UserModelAssembler;
import md.hadj4r.yawp.config.security.CustomClaims;
import md.hadj4r.yawp.model.db.User;
import md.hadj4r.yawp.service.UserService;
import org.springframework.http.MediaType;
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

    @Operation(
            summary = "User info",
            description = "Returns personal user info"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json")
    )
    @GetMapping(value = "/me",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = HAL_JSON_VALUE
    )
    public ResponseEntity<UserInfo> getUser(CustomClaims token) {
        final User user = userService.getUserById(token.getUserId());
        final UserInfo userInfo = userModelAssembler.toModel(user);

        return ResponseEntity.ok(userInfo);
    }

    @Operation(
            summary = "User Deletion",
            description = "Delete current user"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json")
    )
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestHeader(AUTHORIZATION) String bearerToken,
                                           CustomClaims claims) {
        userService.deleteUser(bearerToken, claims);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "User Update",
            description = "Update current user"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    {
                                        "firstName": "Johny",
                                        "lastName": "Doey",
                                        "about": "waba luba dub dub"
                                    }""",
                            summary = "User Authentication Example"
                    )
            )
    )
    @PatchMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = HAL_JSON_VALUE
    )
    public ResponseEntity<UserInfo> updateUser(CustomClaims claims, @RequestBody UserUpdateParam userUpdateParam) {
        final User user = userService.updateUser(claims.getUserId(), userUpdateParam);
        final UserInfo userInfo = userModelAssembler.toModel(user);

        return ResponseEntity.ok(userInfo);
    }
}
