package root.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import root.model.BankBookDto;
import root.model.BankBookException;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class BankBookServiceImpl implements BankBookService{
    private final Map<Integer, BankBookDto> usersBankBookDto = new ConcurrentHashMap<>();
    private final AtomicInteger sequenceId = new AtomicInteger(1);

    @PostConstruct
    public void init(){
        int id = sequenceId.getAndIncrement();

        usersBankBookDto.put(id, new BankBookDto(id, 2, "348249103491022", new BigDecimal(100.0), "USD"));

        id = sequenceId.getAndIncrement();

        usersBankBookDto.put(id, new BankBookDto(id, 3, "437075560923456", new BigDecimal(200.0), "RUR"));

        id = sequenceId.getAndIncrement();

        usersBankBookDto.put(id, new BankBookDto(id, 3, "923469218542984", new BigDecimal(300.0), "EUR"));

        id = sequenceId.getAndIncrement();

        usersBankBookDto.put(id, new BankBookDto(id, 1, "546778907635443", new BigDecimal(800.0), "EUR"));

        id = sequenceId.getAndIncrement();

        usersBankBookDto.put(id, new BankBookDto(id, 1, "942778907935443", new BigDecimal(100.0), "USD"));
    }

    public List<BankBookDto> findAllBankBookDtoOfUser(int userId) {
        List l = new CopyOnWriteArrayList();

        for(BankBookDto b: usersBankBookDto.values()){
            if(b.getUserId() == userId){
                l.add(b);
            }
        }

        if(l.isEmpty()){
            throw new BankBookException("У клиента id = " + userId + " нет счетов");
        }

        return l;
    }

    public BankBookDto findBankBookDtoById(int bankBookId) {
        BankBookDto b = usersBankBookDto.get(bankBookId);

        if(b == null){
            throw new BankBookException("Счет id = " + bankBookId + " не существует");
        }

        return b;
    }

    public BankBookDto createBankBookDto(BankBookDto bankBookDto) {
        int id = sequenceId.getAndIncrement();

        bankBookDto.setId(id);

        for(BankBookDto b: usersBankBookDto.values()) {
            if (bankBookDto.getUserId() == b.getUserId() && bankBookDto.getNumber().equals(b.getNumber())) {
                if (bankBookDto.getCurrency().equals(b.getCurrency())) {
                    throw new BankBookException("Счет " + bankBookDto.getCurrency() + " с номером " + bankBookDto.getNumber() + " уже существует у пользователя id = " + b.getId());
                }
            }
        }

        usersBankBookDto.put(id, bankBookDto);

        return bankBookDto;
    }

    public BankBookDto updateBankBookDto(BankBookDto bankBookDto){
        BankBookDto b = usersBankBookDto.get(bankBookDto.getId());

        if(b == null){
            throw new BankBookException("Изменяемого счета не существует");
        }
        else{
            if(!bankBookDto.getNumber().equals(b.getNumber())){
                throw new BankBookException("Номер счета изменять нельзя");
            }
            else{
                usersBankBookDto.put(bankBookDto.getId(), bankBookDto);
            }
        }

        return bankBookDto;
    }

    public Integer deleteBankBookDtoById(int id) throws Exception {
        if(usersBankBookDto.get(id) == null){
            throw new BankBookException("Счет id = " + id + " не существует");
        }
        else{
           usersBankBookDto.remove(id);
        }

        return 200;
    }

    public int deleteAllBankBookDtoOfUser(int userId) throws Exception {
        List <Integer> l = new CopyOnWriteArrayList<>();

        for(BankBookDto b: usersBankBookDto.values()){
            if(b.getUserId() == userId){
                l.add(b.getId());
            }
        }

        if(l.isEmpty()){
            throw new BankBookException("У клиента id = " + userId + " нет счетов");
        }
        else{
            for(Integer id: l){
                usersBankBookDto.remove(id);
            }
        }

        return 200;
    }
}
