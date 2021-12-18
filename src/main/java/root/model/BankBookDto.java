package root.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import root.validation.Currency;
import root.validation.EntityCreated;
import root.validation.EntityUpdated;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class BankBookDto {
    @Null(groups = EntityCreated.class)
    @NotNull(groups = EntityUpdated.class)
    private Integer id;

    @Null(groups = EntityCreated.class)
    @NotNull(groups = EntityUpdated.class)
    private Integer userId;

    @NotBlank(message = "Ошибка! Счет не может быть пустым")
    private String number;

    @PositiveOrZero
    @NotNull
    private BigDecimal amount;

    @Currency
    private String currency;
}
