package md.hadj4r.yawp.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.hadj4r.yawp.api.dto.user.request.LoginParam;
import md.hadj4r.yawp.api.dto.user.request.SignUpParam;
import md.hadj4r.yawp.api.dto.user.response.TokenInfo;
import md.hadj4r.yawp.service.AuthService;
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

    @PostMapping("/login")
    public ResponseEntity<TokenInfo> login(@RequestBody final LoginParam loginParam) {
        final TokenInfo tokenInfo = authService.login(loginParam);

        return ResponseEntity.ok(tokenInfo);
    }

    @PostMapping("/signup")
    public ResponseEntity<TokenInfo> signup(@Valid @RequestBody final SignUpParam signUpParam) {
        final TokenInfo tokenInfo = authService.signup(signUpParam);

        return ResponseEntity.ok(tokenInfo);
    }
}
