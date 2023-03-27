package auto.service.autoserviceapp.service.calculator;

public interface PaymentCalculator<T, L> {
    T calculate(L list);
}
