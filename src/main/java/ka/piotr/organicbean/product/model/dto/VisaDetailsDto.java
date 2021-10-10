package ka.piotr.organicbean.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VisaDetailsDto {

    private Long id;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
}
