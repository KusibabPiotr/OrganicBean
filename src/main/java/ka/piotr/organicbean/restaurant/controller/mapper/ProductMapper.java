package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.Product;
import ka.piotr.organicbean.restaurant.model.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductDto mapToProductDto(final Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static Product mapToProduct(final ProductDto productDto){
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .build();
    }

    public static List<ProductDto> mapToProductDtoList(final List<Product> productList){
        return productList.stream()
                .map(ProductMapper::mapToProductDto)
                .collect(Collectors.toList());
    }
}
