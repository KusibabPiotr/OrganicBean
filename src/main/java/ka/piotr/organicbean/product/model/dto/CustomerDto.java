package ka.piotr.organicbean.product.model.dto;

import ka.piotr.organicbean.product.validation.ValidPhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class CustomerDto {

    private Long id;
    @NotBlank(message = "First name is mandatory!")
    private String firstName;
    @NotBlank(message = "Last name is mandatory!")
    private String lastName;
    @Email(message = "Provide right email format!")
    @NotBlank(message = "Email is mandatory!")
    private String email;
    @ValidPhoneNumber
    private String phoneNumber;
    @NotNull
    @Valid
    private AddressDto addressDto;
    @NotNull
    @Valid
    private VisaDetailsDto visaDetailsDto;
}
