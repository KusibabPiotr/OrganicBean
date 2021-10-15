package ka.piotr.organicbean.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Digits;
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

    private String phoneNumber;
    @NotNull
    private AddressDto addressDto;
    @NotNull
    private VisaDetailsDto visaDetailsDto;
}
