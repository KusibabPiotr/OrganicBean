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
                customer.getHouseNumber(),
                customer.getStreet(),
                customer.getCity(),
                customer.getPostCode(),
                customer.getPhoneNumber(),
                customer.getOrderList(),
                customer.getCcNumber(),
                customer.getCcExpiration(),
                customer.getCcCVV());
    }

    public Customer mapToCustomer(final CustomerDto customerDto){
        return new Customer(customerDto.getId(),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getEmail(),
                customerDto.getHouseNumber(),
                customerDto.getStreet(),
                customerDto.getCity(),
                customerDto.getPostCode(),
                customerDto.getPhoneNumber(),
                customerDto.getOrderList(),
                customerDto.getCcNumber(),
                customerDto.getCcExpiration(),
                customerDto.getCcCVV());
    }
}
