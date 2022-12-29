package md.hadj4r.yawp.api.dto.user.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpParam {
    @NotEmpty(message = "login is mandatory")
    @Size(min = 3, max = 64, message = "login size should be between 3 and 64 characters")
    private String login;
    @NotEmpty(message = "password is mandatory")
    @Size(min = 8, max = 64, message = "password size should be between 8 and 64 characters")
    private String password;
    @NotEmpty(message = "repeat password is mandatory")
    @Size(min = 8, max = 64, message = "repeat password size should be between 8 and 64 characters")
    private String repeatPassword;
    @NotEmpty(message = "first name is mandatory")
    private String firstName;
    @NotEmpty(message = "last name is mandatory")
    private String lastName;
    @Past(message = "Birthday should be at least 18 years ago")
    private LocalDate birthday;
    private String about;
    @NotEmpty(message = "Address is mandatory")
    private String address;
}
