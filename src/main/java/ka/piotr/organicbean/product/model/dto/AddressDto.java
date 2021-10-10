package ka.piotr.organicbean.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddressDto {

    private Long id;
    private String houseNumber;
    private String street;
    private String city;
    private String postCode;
}
