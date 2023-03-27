package auto.service.autoserviceapp.mapper.impl;

import auto.service.autoserviceapp.dto.response.PaymentResponseDto;
import auto.service.autoserviceapp.mapper.ResponseDtoMapper;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper implements ResponseDtoMapper<PaymentResponseDto, BigDecimal> {
    @Override
    public PaymentResponseDto mapToDto(BigDecimal payment) {
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        paymentResponseDto.setPayment(payment);
        return paymentResponseDto;
    }
}
