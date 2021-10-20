package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.Product;
import ka.piotr.organicbean.restaurant.model.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductDto mapToProductDto(final Product product){
        return new ProductDto(
                product.getName(),
                product.getDescription(),
                product.getPrice());
    }

    public static Product mapToProduct(final ProductDto productDto,final Long id){
        return new Product(id,
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice());
    }

    public static List<ProductDto> mapToProductDtoList(final List<Product> productList){
        return productList.stream()
                .map(ProductMapper::mapToProductDto)
                .collect(Collectors.toList());
    }
}
