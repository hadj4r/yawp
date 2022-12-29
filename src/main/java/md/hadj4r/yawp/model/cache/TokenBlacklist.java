package md.hadj4r.yawp.model.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("TokenBlacklist")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenBlacklist {
    @Id
    private String token;
}
