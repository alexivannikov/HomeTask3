package root.model.entity;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bank_book", schema = "dict")
public class BankBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "currency", referencedColumnName = "id")
    private CurrencyEntity currency;
}
