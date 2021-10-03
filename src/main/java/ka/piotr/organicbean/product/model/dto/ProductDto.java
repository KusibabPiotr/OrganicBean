package ka.piotr.organicbean.product.model.dto;

import ka.piotr.organicbean.product.model.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public final class ProductDto {

    private String name;
    private String description;
    private BigDecimal price;
    private Menu menu;
}
