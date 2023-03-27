package auto.service.autoserviceapp.service.calculator;

import auto.service.autoserviceapp.model.Work;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class WorkPriceCalculator implements PaymentCalculator<BigDecimal, List<Work>> {
    @Override
    public BigDecimal calculate(List<Work> list) {
        return list.stream()
                .map(Work::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
