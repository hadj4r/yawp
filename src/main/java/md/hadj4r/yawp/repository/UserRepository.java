package md.hadj4r.yawp.repository;

import java.util.Optional;
import java.util.UUID;
import md.hadj4r.yawp.model.db.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByLogin(final String login);


    boolean existsByLogin(final String login);

    @EntityGraph(attributePaths = "address")
    User getUserWithAddressById(UUID userId);
}
