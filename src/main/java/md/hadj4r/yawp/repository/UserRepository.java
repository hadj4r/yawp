package md.hadj4r.yawp.repository;

import java.util.Optional;
import java.util.UUID;
import md.hadj4r.yawp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByLogin(final String login);
}
