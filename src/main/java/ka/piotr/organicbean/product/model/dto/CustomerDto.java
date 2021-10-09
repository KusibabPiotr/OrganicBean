package ka.piotr.organicbean.product.model.dto;

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
    private String houseNumber;
    private String street;
    private String city;
    private String postCode;
    private String phoneNumber;
    private List <Order> orderList;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
}
