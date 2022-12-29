package md.hadj4r.yawp.api.dto.user.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfo {
    private String login;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String about;
    private String address;
}
