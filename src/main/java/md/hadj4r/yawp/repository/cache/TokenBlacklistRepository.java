package md.hadj4r.yawp.repository.cache;

import md.hadj4r.yawp.model.cache.TokenBlacklist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenBlacklistRepository extends CrudRepository<TokenBlacklist, String> {
    boolean existsByToken(String token);
}
