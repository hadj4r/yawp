package md.hadj4r.yawp.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.hadj4r.yawp.api.dto.address.request.AddressUpdateParam;
import md.hadj4r.yawp.api.dto.address.response.AddressInfo;
import md.hadj4r.yawp.assembler.AddressModelAssembler;
import md.hadj4r.yawp.config.security.CustomClaims;
import md.hadj4r.yawp.model.db.Address;
import md.hadj4r.yawp.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final AddressModelAssembler addressModelAssembler;

    // TODO do we actually need a get request for addresses with current implementation?
    @GetMapping(produces = HAL_JSON_VALUE)
    public ResponseEntity<AddressInfo> getAddress(CustomClaims claims) {
        final Address address = addressService.getAddress(claims.getUserId());
        final AddressInfo addressInfo = addressModelAssembler.toModel(address);

        return ResponseEntity.ok(addressInfo);
    }

    @PatchMapping(produces = HAL_JSON_VALUE)
    public ResponseEntity<AddressInfo> updateAddress(CustomClaims claims,
                                                     @Valid @RequestBody AddressUpdateParam addressUpdateParam) {
        final Address address = addressService.updateAddress(claims.getUserId(), addressUpdateParam);
        final AddressInfo addressInfo = addressModelAssembler.toModel(address);

        return ResponseEntity.ok(addressInfo);
    }
}
