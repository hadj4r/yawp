package md.hadj4r.yawp.assembler;

import md.hadj4r.yawp.api.controller.AddressController;
import md.hadj4r.yawp.api.dto.address.response.AddressInfo;
import md.hadj4r.yawp.mapper.AddressMapper;
import md.hadj4r.yawp.model.db.Address;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AddressModelAssembler implements RepresentationModelAssembler<Address, AddressInfo> {

    @Override
    public AddressInfo toModel(final Address entity) {
        AddressInfo address = AddressMapper.INSTANCE.map(entity);

        return addLinks(address);
    }

    public AddressInfo addLinks(final AddressInfo address) {
        Link self = linkTo(methodOn(AddressController.class)
                .getAddress(null)).withSelfRel();
        Link update = linkTo(methodOn(AddressController.class)
                .updateAddress(null, null))
                .withRel("update");

        return address.add(self, update);
    }
}
