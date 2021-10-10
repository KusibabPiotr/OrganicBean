package ka.piotr.organicbean.product.model.dto;

import ka.piotr.organicbean.product.model.domain.Address;
import ka.piotr.organicbean.product.model.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
@Data
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AddressDto addressDto;
    private VisaDetailsDto visaDetailsDto;
}
