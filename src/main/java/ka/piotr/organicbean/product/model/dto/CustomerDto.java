package ka.piotr.organicbean.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

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
