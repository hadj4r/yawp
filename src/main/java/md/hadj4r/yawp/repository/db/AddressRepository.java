package md.hadj4r.yawp.repository.db;

import java.util.UUID;
import md.hadj4r.yawp.model.db.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, UUID> {
    Address findByUserId(UUID userId);
}
