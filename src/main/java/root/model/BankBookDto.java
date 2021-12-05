package root.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankBookDto {
    Integer id;

    Integer userId;

    String number;

    BigDecimal amount;

    String currency;
}
