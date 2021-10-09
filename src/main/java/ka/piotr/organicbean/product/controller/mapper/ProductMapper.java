package ka.piotr.organicbean.product.controller.mapper;

import ka.piotr.organicbean.product.model.domain.Product;
import ka.piotr.organicbean.product.model.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDto mapToProductDto(final Product product){
        return new ProductDto(
                product.getName(),
                product.getDescription(),
                product.getPrice());
    }

    public Product mapToProduct(final ProductDto productDto,final Long id){
        return new Product(id,
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice());
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList){
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
