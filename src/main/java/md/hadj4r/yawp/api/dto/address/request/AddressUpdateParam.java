package md.hadj4r.yawp.api.dto.address.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressUpdateParam {
    @NotEmpty(message = "Address is required")
    private String address;
}
