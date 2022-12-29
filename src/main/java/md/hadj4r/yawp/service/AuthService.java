package md.hadj4r.yawp.service;

import md.hadj4r.yawp.api.dto.user.request.LoginParam;
import md.hadj4r.yawp.api.dto.user.request.SignUpParam;
import md.hadj4r.yawp.api.dto.user.response.TokenInfo;

public interface AuthService {
    TokenInfo login(LoginParam loginParam);

    TokenInfo signup(SignUpParam signUpParam);

}
