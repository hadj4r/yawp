package md.hadj4r.yawp.service;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import md.hadj4r.yawp.api.dto.user.request.LoginParam;
import md.hadj4r.yawp.api.dto.user.request.SignUpParam;
import md.hadj4r.yawp.api.dto.user.response.TokenInfo;
import md.hadj4r.yawp.config.security.JwtTokenService;
import md.hadj4r.yawp.exception.BirthdayException;
import md.hadj4r.yawp.exception.CredentialsNotFoundException;
import md.hadj4r.yawp.exception.PasswordsNotEqualException;
import md.hadj4r.yawp.exception.UserAlreadyExistsException;
import md.hadj4r.yawp.mapper.UserMapper;
import md.hadj4r.yawp.model.db.User;
import md.hadj4r.yawp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final String USER_NOT_FOUND = "User not found with current login or password";
    private static final String USER_ALREADY_EXISTS = "User with current login already exists";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    @Override
    public TokenInfo login(final LoginParam loginParam) {
        final User userByLogin = userRepository.findByLogin(loginParam.getLogin())
                .orElseThrow(() -> new CredentialsNotFoundException(USER_NOT_FOUND));

        if (!passwordEncoder.matches(loginParam.getPassword(), userByLogin.getPassword())) {
            throw new CredentialsNotFoundException(USER_NOT_FOUND);
        }

        final String token = jwtTokenService.createToken(userByLogin.getLogin(), userByLogin.getId());

        return new TokenInfo(token);
    }

    @Override
    public TokenInfo signup(final SignUpParam signUpParam) {
        if (userRepository.existsByLogin(signUpParam.getLogin())) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }

        if (!signUpParam.getPassword().equals(signUpParam.getRepeatPassword())) {
            throw new PasswordsNotEqualException("Passwords must be equal", HttpStatus.BAD_REQUEST);
        }

        final LocalDate now = LocalDate.now();
        if (signUpParam.getBirthday().isAfter(now.minusYears(18))) {
            throw new BirthdayException("User must be at least 18 years old", HttpStatus.BAD_REQUEST);
        }

        final User newUser = UserMapper.INSTANCE.map(signUpParam);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userRepository.save(newUser);

        final String token = jwtTokenService.createToken(newUser.getLogin(), newUser.getId());

        return new TokenInfo(token);
    }

}
