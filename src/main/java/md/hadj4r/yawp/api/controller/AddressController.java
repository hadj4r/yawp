package md.hadj4r.yawp.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.hadj4r.yawp.api.dto.address.request.AddressUpdateParam;
import md.hadj4r.yawp.api.dto.address.response.AddressInfo;
import md.hadj4r.yawp.config.security.CustomClaims;
import md.hadj4r.yawp.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    // TODO do we actually need a get request for addresses with current implementation?

    @PatchMapping
    public ResponseEntity<AddressInfo> updateAddress(CustomClaims claims,
                                                     @Valid @RequestBody AddressUpdateParam addressUpdateParam) {
        final AddressInfo addressInfo = addressService.updateAddress(claims.getUserId(), addressUpdateParam);

        return ResponseEntity.ok(addressInfo);
    }
}
