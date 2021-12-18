package root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import root.model.entity.CurrencyEntity;
import root.validation.Currency;

import java.util.Optional;

public interface CurrencyEntityRepository extends JpaRepository<CurrencyEntity, Integer> {
    CurrencyEntity findByName(String name);
}
