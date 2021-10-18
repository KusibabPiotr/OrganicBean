package ka.piotr.organicbean.restaurant.model.dto;

import ka.piotr.organicbean.restaurant.validation.ValidCardExpiration;
import ka.piotr.organicbean.restaurant.validation.ValidCardNumber;
import ka.piotr.organicbean.restaurant.validation.ValidCvv;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class VisaDetailsDto {

    private Long id;
    @ValidCardNumber
    private String ccNumber;
    @ValidCardExpiration
    private String ccExpiration;
    @ValidCvv
    private String ccCVV;
}
