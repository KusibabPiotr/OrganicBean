package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.Customer;
import ka.piotr.organicbean.restaurant.model.dto.CustomerDto;

public class CustomerMapper {

    private CustomerMapper() {
    }

    public static CustomerDto mapToCustomerDto(final Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .addressDto(AddressMapper.mapToAddressDto(customer.getAddress()))
                .visaDetailsDto(VisaDetailsMapper.mapToVisaDetailsDto(customer.getVisaDetails()))
                .build();
    }

    public static Customer mapToCustomer(final CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .phoneNumber(customerDto.getPhoneNumber())
                .address(AddressMapper.mapToAddress(customerDto.getAddressDto()))
                .visaDetails(VisaDetailsMapper.mapToVisaDetails(customerDto.getVisaDetailsDto()))
                .build();
    }
}
