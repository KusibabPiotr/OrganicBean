package ka.piotr.organicbean.restaurant.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
public final class ProductDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
