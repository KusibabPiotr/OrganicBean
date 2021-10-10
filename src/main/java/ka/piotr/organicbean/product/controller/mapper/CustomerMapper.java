package ka.piotr.organicbean.product.controller.mapper;

import ka.piotr.organicbean.product.model.domain.Customer;
import ka.piotr.organicbean.product.model.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final AddressMapper addressMapper;
    private final VisaDetailsMapper visaDetailsMapper;

    public CustomerDto mapToCustomerDto(final Customer customer){
        return new CustomerDto(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                addressMapper.mapToAddressDto(customer.getAddress()),
                customer.getOrderList(),
                visaDetailsMapper.mapToVisaDetailsDto(customer.getVisaDetails()));
    }

    public Customer mapToCustomer(final CustomerDto customerDto){
        return new Customer(customerDto.getId(),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getEmail(),
                customerDto.getPhoneNumber(),
                addressMapper.mapToAddress(customerDto.getAddressDto()),
                customerDto.getOrderList(),
                visaDetailsMapper.mapToVisaDetails(customerDto.getVisaDetailsDto()));
    }
}
