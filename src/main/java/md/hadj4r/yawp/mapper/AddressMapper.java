package md.hadj4r.yawp.mapper;

import md.hadj4r.yawp.api.dto.address.response.AddressInfo;
import md.hadj4r.yawp.model.db.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = getMapper(AddressMapper.class);

    @Mappings({
            @Mapping(target = "address", source = "address")
    })
    AddressInfo map(final Address address);

}
