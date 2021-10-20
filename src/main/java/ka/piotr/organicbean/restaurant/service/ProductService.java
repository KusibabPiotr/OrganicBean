package ka.piotr.organicbean.restaurant.service;

import ka.piotr.organicbean.restaurant.model.domain.Product;
import ka.piotr.organicbean.restaurant.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Product product, Long id){
        Product productDb = productRepository.findById(id).orElseThrow();
        productDb.setName(product.getName());
        productDb.setDescription(product.getDescription());
        productDb.setPrice(product.getPrice());
        return productDb;
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
