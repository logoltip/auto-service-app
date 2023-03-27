package auto.service.autoserviceapp.service.calculator;

import auto.service.autoserviceapp.model.Product;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceCalculator implements PaymentCalculator<BigDecimal, List<Product>> {
    @Override
    public BigDecimal calculate(List<Product> list) {
        return list.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
