package md.hadj4r.yawp.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
    @Operation(
            summary = "Retrieve address info",
            description = "Returns address info associated with current user"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json")
    )
    @GetMapping(produces = HAL_JSON_VALUE)
    public ResponseEntity<AddressInfo> getAddress(CustomClaims claims) {
        final Address address = addressService.getAddress(claims.getUserId());
        final AddressInfo addressInfo = addressModelAssembler.toModel(address);

        return ResponseEntity.ok(addressInfo);
    }

    @Operation(
            summary = "Update address info",
            description = "Update address info associated with current user"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    {
                                    "address": "1600 Pennsylvania Avenue NW, Washington, DC 20500, USA"
                                    }""",
                            summary = "Address Update Example"
                    )
            )
    )
    @PatchMapping(produces = HAL_JSON_VALUE)
    public ResponseEntity<AddressInfo> updateAddress(CustomClaims claims,
                                                     @Valid @RequestBody AddressUpdateParam addressUpdateParam) {
        final Address address = addressService.updateAddress(claims.getUserId(), addressUpdateParam);
        final AddressInfo addressInfo = addressModelAssembler.toModel(address);

        return ResponseEntity.ok(addressInfo);
    }
}
