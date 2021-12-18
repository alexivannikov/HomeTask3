package root.mapper;

import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import root.model.BankBookDto;
import root.model.entity.BankBookEntity;
import root.model.entity.CurrencyEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-15T19:50:52+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 14.0.2 (AdoptOpenJDK)"
)
@Component
public class BankBookMapperImpl implements BankBookMapper {

    @Override
    public BankBookDto mapToDto(BankBookEntity bankBookEntity) {
        if ( bankBookEntity == null ) {
            return null;
        }

        String currency = null;
        Integer id = null;
        Integer userId = null;
        String number = null;
        BigDecimal amount = null;

        currency = bankBookEntityCurrencyName( bankBookEntity );
        id = bankBookEntity.getId();
        userId = bankBookEntity.getUserId();
        number = bankBookEntity.getNumber();
        if ( bankBookEntity.getAmount() != null ) {
            amount = BigDecimal.valueOf( bankBookEntity.getAmount() );
        }

        BankBookDto bankBookDto = new BankBookDto( id, userId, number, amount, currency );

        return bankBookDto;
    }

    @Override
    public BankBookEntity mapToEntity(BankBookDto bankBookDto) {
        if ( bankBookDto == null ) {
            return null;
        }

        BankBookEntity bankBookEntity = new BankBookEntity();

        bankBookEntity.setCurrency( bankBookDtoToCurrencyEntity( bankBookDto ) );
        bankBookEntity.setId( bankBookDto.getId() );
        bankBookEntity.setUserId( bankBookDto.getUserId() );
        bankBookEntity.setNumber( bankBookDto.getNumber() );
        if ( bankBookDto.getAmount() != null ) {
            bankBookEntity.setAmount( bankBookDto.getAmount().intValue() );
        }

        return bankBookEntity;
    }

    private String bankBookEntityCurrencyName(BankBookEntity bankBookEntity) {
        if ( bankBookEntity == null ) {
            return null;
        }
        CurrencyEntity currency = bankBookEntity.getCurrency();
        if ( currency == null ) {
            return null;
        }
        String name = currency.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected CurrencyEntity bankBookDtoToCurrencyEntity(BankBookDto bankBookDto) {
        if ( bankBookDto == null ) {
            return null;
        }

        CurrencyEntity currencyEntity = new CurrencyEntity();

        currencyEntity.setName( bankBookDto.getCurrency() );

        return currencyEntity;
    }
}
