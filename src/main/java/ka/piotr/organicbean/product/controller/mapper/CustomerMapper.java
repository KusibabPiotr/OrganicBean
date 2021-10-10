package ka.piotr.organicbean.product.controller.mapper;

import ka.piotr.organicbean.product.model.domain.Customer;
import ka.piotr.organicbean.product.model.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDto mapToCustomerDto(final Customer customer){
        return new CustomerDto(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getOrderList(),
                customer.getVisaDetails());
    }

    public Customer mapToCustomer(final CustomerDto customerDto){
        return new Customer(customerDto.getId(),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getEmail(),
                customerDto.getPhoneNumber(),
                customerDto.getAddressDto(),
                customerDto.getOrderList(),
                customerDto.getVisaDetailsDto());
    }
}
