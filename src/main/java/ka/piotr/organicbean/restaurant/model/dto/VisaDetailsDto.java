package ka.piotr.organicbean.restaurant.model.dto;

import ka.piotr.organicbean.restaurant.validation.ValidCardExpiration;
import ka.piotr.organicbean.restaurant.validation.ValidCardNumber;
import ka.piotr.organicbean.restaurant.validation.ValidCvv;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
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
