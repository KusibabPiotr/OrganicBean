package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.ProductMapper;
import ka.piotr.organicbean.product.exceptions.ProductNotFoundException;
import ka.piotr.organicbean.product.model.dto.ProductDto;
import ka.piotr.organicbean.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping(value = "get")
    public List<ProductDto> getProducts(){
        return productMapper.mapToProductDtoList(productService.getProducts());
    }

    @GetMapping(value = "get/{id}")
    public ProductDto getProduct(@PathVariable Long id) throws ProductNotFoundException {
        return productMapper.mapToProductDto(productService.getProduct(id).
                orElseThrow(ProductNotFoundException::new));
    }

    @PostMapping(value = "create",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productMapper.mapToProductDto(productService.saveProduct(productMapper.mapToProduct(productDto,null)));
    }

    @PutMapping(value = "update/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        return productMapper.mapToProductDto(productService.saveProduct(productMapper.mapToProduct(productDto,id)));
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
