package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.Address;
import ka.piotr.organicbean.restaurant.model.dto.AddressDto;


public class AddressMapper {

    private AddressMapper() {
    }

    public static Address mapToAddress(final AddressDto addressDto){
        return Address.builder()
                .id(addressDto.getId())
                .flatNumber(addressDto.getFlatNumber())
                .houseNumber(addressDto.getFlatNumber())
                .street(addressDto.getStreet())
                .city(addressDto.getCity())
                .postCode(addressDto.getPostCode())
                .build();
    }

    public static AddressDto mapToAddressDto(final Address address){
        return AddressDto.builder()
                .id(address.getId())
                .flatNumber(address.getFlatNumber())
                .houseNumber(address.getHouseNumber())
                .street(address.getStreet())
                .city(address.getCity())
                .postCode(address.getPostCode())
                .build();
    }
}
