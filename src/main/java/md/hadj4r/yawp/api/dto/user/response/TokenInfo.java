package md.hadj4r.yawp.api.dto.user.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenInfo {
    private String accessToken;
    // refreshToken is not used in this project
}
