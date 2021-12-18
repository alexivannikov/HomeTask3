package root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import root.model.entity.BankBookEntity;
import root.model.entity.CurrencyEntity;

import java.util.List;
import java.util.Optional;

public interface BankBookEntityRepository extends JpaRepository <BankBookEntity, Integer> {
    public List<BankBookEntity> findAllByUserId(Integer userId);

    public Optional<BankBookEntity> findByUserIdAndNumberAndCurrency(Integer userId, String number, CurrencyEntity currencyEntity);

    @Query("delete from BankBookEntity b where b.userId = ?1")
    @Modifying
    @Transactional
    public void deleteAllByUserId(Integer userId);
}
