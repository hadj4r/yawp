package md.hadj4r.yawp.service;

import java.util.UUID;
import md.hadj4r.yawp.api.dto.address.request.AddressUpdateParam;
import md.hadj4r.yawp.model.db.Address;

public interface AddressService {
    Address updateAddress(UUID userId, AddressUpdateParam addressUpdateParam);

    Address getAddress(UUID userId);
}
