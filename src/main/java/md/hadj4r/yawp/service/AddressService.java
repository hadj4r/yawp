package md.hadj4r.yawp.service;

import java.util.UUID;
import md.hadj4r.yawp.api.dto.address.request.AddressUpdateParam;
import md.hadj4r.yawp.api.dto.address.response.AddressInfo;

public interface AddressService {
    AddressInfo updateAddress(UUID userId, AddressUpdateParam addressUpdateParam);
}
