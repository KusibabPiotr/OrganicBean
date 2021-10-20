package ka.piotr.organicbean.restaurant.model.dto;

import ka.piotr.organicbean.restaurant.validation.ValidPostCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Builder
@Data
public class AddressDto {

    private Long id;
    @NotBlank(message = "We need yours flat number for delivery!")
    @Digits(integer = 4, fraction = 0)
    private int flatNumber;
    @NotBlank(message = "We need yours house number for delivery!")
    @Digits(integer = 4, fraction = 0)
    private int houseNumber;
    @NotBlank(message = "We need street name for delivery!")
    private String street;
    @NotBlank(message = "And also we need to know your city for delivery!")
    private String city;
    @ValidPostCode
    private String postCode;
}
