package ka.piotr.organicbean.product.model.dto;

import ka.piotr.organicbean.product.validation.ValidCardExpiration;
import ka.piotr.organicbean.product.validation.ValidCardNumber;
import ka.piotr.organicbean.product.validation.ValidCvv;
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
