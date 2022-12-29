package md.hadj4r.yawp.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.hadj4r.yawp.api.dto.user.request.LoginParam;
import md.hadj4r.yawp.api.dto.user.request.SignUpParam;
import md.hadj4r.yawp.api.dto.user.response.TokenInfo;
import md.hadj4r.yawp.service.AuthService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "User Authentication",
            description = "Authenticate the user and return a JWT token if the user is valid."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    {
                                      "login": "john",
                                      "password": "password"
                                    }""",
                            summary = "User Authentication Example"
                    )
            )
    )
    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TokenInfo> login(@RequestBody final LoginParam loginParam) {
        final TokenInfo tokenInfo = authService.login(loginParam);

        return ResponseEntity.ok(tokenInfo);
    }

    @Operation(
            summary = "User Registration",
            description = "Register the user and return a JWT token if the user is valid."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    {
                                      "login": "rick",
                                      "password": "password"
                                      "repeatPassword": "password"
                                    }""",
                            summary = "User Registration Example"
                    )
            )
    )
    @PostMapping(
            value = "/signup",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TokenInfo> signup(@Valid @RequestBody final SignUpParam signUpParam) {
        final TokenInfo tokenInfo = authService.signup(signUpParam);

        return ResponseEntity.ok(tokenInfo);
    }
}
