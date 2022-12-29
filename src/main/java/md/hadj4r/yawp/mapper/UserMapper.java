package md.hadj4r.yawp.mapper;

import md.hadj4r.yawp.api.dto.user.request.SignUpParam;
import md.hadj4r.yawp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "login", source = "login"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "birthday", source = "birthday"),
            @Mapping(target = "about", source = "about"),
            @Mapping(target = "address.address", source = "address"),
    })
    User map(final SignUpParam signUpParam);
}
