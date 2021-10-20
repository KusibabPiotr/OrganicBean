package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.Customer;
import ka.piotr.organicbean.restaurant.model.dto.CustomerDto;

public class CustomerMapper {

    private CustomerMapper() {
    }

    public static CustomerDto mapToCustomerDto(final Customer customer){
        return new CustomerDto(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                AddressMapper.mapToAddressDto(customer.getAddress()),
                VisaDetailsMapper.mapToVisaDetailsDto(customer.getVisaDetails()));
    }

    public static Customer mapToCustomer(final CustomerDto customerDto){
        return new Customer(customerDto.getId(),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getEmail(),
                customerDto.getPhoneNumber(),
                AddressMapper.mapToAddress(customerDto.getAddressDto()),
                VisaDetailsMapper.mapToVisaDetails(customerDto.getVisaDetailsDto()));
    }
}
