package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.Address;
import ka.piotr.organicbean.restaurant.model.dto.AddressDto;


public class AddressMapper {

    private AddressMapper() {
    }

    public static Address mapToAddress(final AddressDto addressDto){
        return new Address(addressDto.getId(),
                addressDto.getFlatNumber(),
                addressDto.getHouseNumber(),
                addressDto.getStreet(),
                addressDto.getCity(),
                addressDto.getPostCode());
    }

    public static AddressDto mapToAddressDto(final Address address){
        return new AddressDto(address.getId(),
                address.getFlatNumber(),
                address.getHouseNumber(),
                address.getStreet(),
                address.getCity(),
                address.getPostCode());
    }
}
