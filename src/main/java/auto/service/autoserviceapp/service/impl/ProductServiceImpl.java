package auto.service.autoserviceapp.service.impl;

import auto.service.autoserviceapp.model.Product;
import auto.service.autoserviceapp.repository.ProductRepository;
import auto.service.autoserviceapp.service.ProductService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new NoSuchElementException("Can't find product by id: " + productId));
    }

    @Override
    public List<Product> findAllById(List<Long> productIds) {
        return productRepository.findAllById(productIds);
    }
}
