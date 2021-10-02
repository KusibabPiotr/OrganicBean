package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.ProductMapper;
import ka.piotr.organicbean.product.exceptions.ProductNotFoundException;
import ka.piotr.organicbean.product.model.dto.ProductDto;
import ka.piotr.organicbean.product.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private ProductService productService;
    private ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts(){
        return productMapper.mapToProductDtoList(productService.getProducts());
    }

    @GetMapping(value = "getProduct")
    public ProductDto getProduct(@RequestParam Long id) throws ProductNotFoundException {
        return productMapper.mapToProductDto(productService.getProduct(id).
                orElseThrow(ProductNotFoundException::new));
    }

    @PostMapping(value = "createProduct",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto){
        productService.saveProduct(productMapper.mapToProduct(productDto));
    }

    @PutMapping(value = "updateProduct",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        return productMapper.mapToProductDto(productService.saveProduct(productMapper.mapToProduct(productDto)));
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam Long id){
        productService.deleteProduct(id);
    }
}
