package md.hadj4r.yawp.service;

public interface TokenBlacklistService {
    void deleteToken(String token, final long ttl);

    boolean exists(String token);
}
