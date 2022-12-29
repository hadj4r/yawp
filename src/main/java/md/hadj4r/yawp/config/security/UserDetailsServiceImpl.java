package md.hadj4r.yawp.config.security;

import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import md.hadj4r.yawp.exception.UserNotFoundException;
import md.hadj4r.yawp.model.User;
import md.hadj4r.yawp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        final User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException(login, NOT_FOUND));

        return org.springframework.security.core.userdetails.User.withUsername(login)
                .password(user.getPassword())
                // TODO add authorities
                .authorities(new HashSet<>())
                .build();
    }

}
