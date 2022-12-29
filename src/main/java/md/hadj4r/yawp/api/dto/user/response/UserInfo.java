package md.hadj4r.yawp.api.dto.user.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.hadj4r.yawp.api.dto.address.response.AddressInfo;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfo extends RepresentationModel<UserInfo> {
    private String login;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String about;
    private AddressInfo address;
}
