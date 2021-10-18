package ka.piotr.organicbean.restaurant.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public final class ProductDto {

    private String name;
    private String description;
    private BigDecimal price;
}
