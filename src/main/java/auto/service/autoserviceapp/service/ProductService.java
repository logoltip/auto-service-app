package auto.service.autoserviceapp.service;

import auto.service.autoserviceapp.model.Product;
import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product findById(Long productId);

    List<Product> findAllByIds(List<Long> productIds);
}
