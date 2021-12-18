package root.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import root.model.entity.CurrencyEntity;
import root.repository.CurrencyEntityRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {
    private final CurrencyEntityRepository currencyEntityRepository;

    public CurrencyValidator(CurrencyEntityRepository currencyEntityRepository){
        this.currencyEntityRepository = currencyEntityRepository;
    }

    public boolean isValid(String currencyName, ConstraintValidatorContext constraintValidatorContext){
        return currencyEntityRepository.findByName(currencyName) != null;
    }
}
