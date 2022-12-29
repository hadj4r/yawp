package md.hadj4r.yawp.assembler;

import lombok.RequiredArgsConstructor;
import md.hadj4r.yawp.api.controller.UserController;
import md.hadj4r.yawp.api.dto.user.response.UserInfo;
import md.hadj4r.yawp.mapper.UserMapper;
import md.hadj4r.yawp.model.db.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class UserModelAssembler implements RepresentationModelAssembler<User, UserInfo> {
    private final AddressModelAssembler addressModelAssembler;

    @Override
    public UserInfo toModel(final User entity) {
        UserInfo user = UserMapper.INSTANCE.map(entity);

        addressModelAssembler.addLinks(user.getAddress());

        Link self = linkTo(methodOn(UserController.class)
                .getUser(null)).withSelfRel();
        Link update = linkTo(methodOn(UserController.class)
                .updateUser(null, null)).withRel("update");
        Link delete = linkTo(methodOn(UserController.class)
                .deleteUser(null, null)).withRel("delete");

        return user.add(self, update, delete);
    }
}
