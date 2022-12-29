package md.hadj4r.yawp.service.impl;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import md.hadj4r.yawp.repository.cache.TokenBlacklistRepository;
import md.hadj4r.yawp.service.TokenBlacklistService;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenBlacklistServiceImpl implements TokenBlacklistService {
    private final TokenBlacklistRepository tokenBlacklistRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void deleteToken(final String token, final long ttl) {
        // TODO refactor this, maybe should use smaller lifetime + refresh token
        setKeyTTL(token, ttl);
    }

    @Override
    public boolean exists(final String token) {
        return tokenBlacklistRepository.existsByToken(token);
    }

    private void setKeyTTL(String key, long ttl) {
        final BoundValueOperations<String, Object> boundValueOps = redisTemplate.boundValueOps(key);

        boundValueOps.set(key);
        boundValueOps.expire(ttl, TimeUnit.MILLISECONDS);
    }
}
