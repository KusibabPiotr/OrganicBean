package ka.piotr.organicbean.restaurant.controller;

import ka.piotr.organicbean.restaurant.controller.mapper.ProductMapper;
import ka.piotr.organicbean.restaurant.exceptions.ProductNotFoundException;
import ka.piotr.organicbean.restaurant.model.dto.ProductDto;
import ka.piotr.organicbean.restaurant.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts(){
        return ProductMapper.mapToProductDtoList(productService.getProducts());
    }

    @GetMapping(value = "/{id}")
    public ProductDto getProduct(@PathVariable Long id) throws ProductNotFoundException {
        return ProductMapper.mapToProductDto(productService.getProduct(id).
                orElseThrow(ProductNotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return ProductMapper.mapToProductDto(productService.saveProduct(ProductMapper.mapToProduct(productDto)));
    }

    @PutMapping(value = "/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@PathVariable Long id,
                                    @RequestBody ProductDto productDto){
        return ProductMapper.mapToProductDto(productService.updateProduct(ProductMapper.mapToProduct(productDto),id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
