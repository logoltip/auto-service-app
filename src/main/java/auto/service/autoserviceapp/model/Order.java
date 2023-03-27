package auto.service.autoserviceapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String problemDescription;
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;
    private LocalDate orderDate;
    @ManyToMany
    private List<Work> works;
    @ManyToMany
    private List<Product> products;
    private OrderStatus orderStatus;
    private BigDecimal totalCost;
    private LocalDate completionDate;

    public enum OrderStatus {
        ACCEPTED,
        IN_PROCESS,
        COMPLETE_SUCCESSFULLY,
        COMPLETE_UNSUCCESSFULLY,
        PAID
    }
}
