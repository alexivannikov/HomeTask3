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

    @Query("select b from BankBookEntity b where b.userId = ?1 and b.number = ?2 and b.currency = ?3")
    public Optional<BankBookEntity> findByUserIdAndNumberAndCurrency(Integer userId, String number, CurrencyEntity currencyEntity);

    @Query("select b from BankBookEntity b where b.userId = ?1 and b.number = ?2")
    public BankBookEntity findByUserIdAndNumber(Integer userId, String number);

    @Query("select b from BankBookEntity b where b.number = ?1")
    public BankBookEntity findByNumber(String number);

    @Query("delete from BankBookEntity b where b.userId = ?1")
    @Modifying
    @Transactional
    public void deleteAllByUserId(Integer userId);
}
