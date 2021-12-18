package root.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import root.mapper.BankBookMapper;
import root.model.BankBookDto;
import root.model.BankBookException;
import root.model.entity.BankBookEntity;
import root.model.entity.CurrencyEntity;
import root.repository.BankBookEntityRepository;
import root.repository.CurrencyEntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@RequiredArgsConstructor
@Component
@Validated
public class BankBookServiceImpl implements BankBookService{
    private final BankBookEntityRepository bankBookEntityRepository;
    private final CurrencyEntityRepository currencyEntityRepository;
    private final BankBookMapper bankBookMapper;

    public List<BankBookDto> findAllBankBookDtoOfUser(Integer userId) {
        List <BankBookEntity> bankBookEntities = bankBookEntityRepository.findAllByUserId(userId);
        List <BankBookDto> bankBookDtos = new CopyOnWriteArrayList<>();

        if(bankBookEntities.isEmpty()){
            throw new BankBookException("У клиента id = " + userId + " нет счетов");
        }
        else{
            for(BankBookEntity b: bankBookEntities){
                bankBookDtos.add(bankBookMapper.mapToDto(b));
            }

        }

        return bankBookDtos;
    }

    public BankBookDto findBankBookDtoById(Integer bankBookId) {
        BankBookDto b = bankBookMapper.mapToDto(bankBookEntityRepository.findById(bankBookId).get());

        if(b == null){
            throw new BankBookException("Счет id = " + bankBookId + " не существует");
        }

        return b;
    }

    public BankBookDto createBankBookDto(BankBookDto bankBookDto) {
        CurrencyEntity currency = currencyEntityRepository.findByName(bankBookDto.getCurrency());

        Optional <BankBookEntity> bankBookEntityOptional = bankBookEntityRepository.findByUserIdAndNumberAndCurrency(
                bankBookDto.getUserId(),
                bankBookDto.getNumber(),
                currency);

        if(bankBookEntityOptional.isPresent()){
            throw new BankBookException("Счет с данной валютой уже существует!");
        }
        else{
            BankBookEntity bankBookEntity = bankBookMapper.mapToEntity(bankBookDto);
            bankBookEntity.setCurrency(currency);

            return bankBookMapper.mapToDto(bankBookEntityRepository.save(bankBookEntity));
        }

    }

    public BankBookDto updateBankBookDto(BankBookDto bankBookDto){
        BankBookEntity bankBookEntity = bankBookEntityRepository.findById(bankBookDto.getId())
                .orElseThrow(() -> new BankBookException("Изменяемого счета не существует"));

        if(!bankBookDto.getNumber().equals(bankBookEntity.getNumber())){
            throw new BankBookException("Номер счета изменять нельзя");
        }
        else{
            CurrencyEntity currency = currencyEntityRepository.findByName(bankBookDto.getCurrency());

            bankBookEntity = bankBookMapper.mapToEntity(bankBookDto);
            bankBookEntity.setCurrency(currency);

            return bankBookMapper.mapToDto(bankBookEntityRepository.save(bankBookEntity));
        }
    }

    public Integer deleteBankBookDtoById(Integer id) throws Exception {
        if(bankBookEntityRepository.findById(id) == null){
            throw new BankBookException("Счет id = " + id + " не существует");
        }
        else{
           bankBookEntityRepository.deleteById(id);
        }

        return 200;
    }
    
    public Integer deleteAllBankBookDtoOfUser(Integer userId) throws Exception {
        List<BankBookEntity> bankBookEntities = bankBookEntityRepository.findAllByUserId(userId);

        if(bankBookEntities.isEmpty()){
            throw new BankBookException("У клиента id = " + userId + " нет счетов");
        }
        else{
            bankBookEntityRepository.deleteAllByUserId(userId);
        }

        return 200;
    }


}
