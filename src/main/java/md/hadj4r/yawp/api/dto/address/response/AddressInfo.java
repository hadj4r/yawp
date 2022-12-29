package md.hadj4r.yawp.api.dto.address.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressInfo extends RepresentationModel<AddressInfo> {
    private String address;
}
