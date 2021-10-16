package ka.piotr.organicbean.product.controller.mapper;

import ka.piotr.organicbean.product.model.domain.Address;
import ka.piotr.organicbean.product.model.dto.AddressDto;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address mapToAddress(final AddressDto addressDto){
        return new Address(addressDto.getId(),
                addressDto.getFlatNumber(),
                addressDto.getHouseNumber(),
                addressDto.getStreet(),
                addressDto.getCity(),
                addressDto.getPostCode());
    }

    public AddressDto mapToAddressDto(final Address address){
        return new AddressDto(address.getId(),
                address.getFlatNumber(),
                address.getHouseNumber(),
                address.getStreet(),
                address.getCity(),
                address.getPostCode());
    }
}
