package md.hadj4r.yawp.service.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import md.hadj4r.yawp.api.dto.address.request.AddressUpdateParam;
import md.hadj4r.yawp.exception.AddressUpdateNotPossibleException;
import md.hadj4r.yawp.model.db.Address;
import md.hadj4r.yawp.repository.db.AddressRepository;
import md.hadj4r.yawp.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public Address updateAddress(final UUID userId, final AddressUpdateParam addressUpdateParam) {
        final Address address = addressRepository.findByUserId(userId);

        if (address.getAddress().equals(addressUpdateParam.getAddress())) {
            throw new AddressUpdateNotPossibleException("Please provide at least one valid field to update", HttpStatus.EXPECTATION_FAILED);
        }

        address.setAddress(addressUpdateParam.getAddress());

        return addressRepository.save(address);
    }

    @Override
    public Address getAddress(final UUID userId) {
        return addressRepository.findByUserId(userId);
    }
}