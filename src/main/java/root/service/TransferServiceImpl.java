package root.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import root.mapper.BankBookMapper;
import root.mapper.TransactionMapper;
import root.model.dto.TransactionDto;
import root.model.entity.CurrencyEntity;
import root.model.exception.TransferException;
import root.model.entity.BankBookEntity;
import root.model.entity.StatusEntity;
import root.model.entity.TransactionEntity;
import root.repository.BankBookEntityRepository;
import root.repository.CurrencyEntityRepository;
import root.repository.StatusEntityRepository;
import root.repository.TransactionEntityRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
@Validated
public class TransferServiceImpl implements TransferService {
    private final TransactionEntityRepository transactionEntityRepository;
    private final BankBookEntityRepository bankBookEntityRepository;
    private final CurrencyEntityRepository currencyEntityRepository;
    private final StatusEntityRepository statusEntityRepository;
    private final BankBookService bankBookService;
    private final BankBookMapper bankBookMapper;
    private final TransactionMapper transactionMapper;

    public TransactionDto transfer(TransactionDto transactionDto, Integer... userId) {
        LocalDateTime startTransfer = LocalDateTime.now();

        BankBookEntity sourceBankBook = bankBookEntityRepository.findByNumber(transactionDto.getSourceBankBookId());
        BankBookEntity targetBankBook = null;

        if (userId.length == 0) {
            targetBankBook = bankBookEntityRepository.findByNumber(transactionDto.getTargetBankBookId());
        } else {
            targetBankBook = bankBookEntityRepository.findByUserIdAndNumber(userId[0],
                    bankBookEntityRepository.findByNumber(transactionDto.getTargetBankBookId()).getNumber());
        }


        TransactionEntity t = transactionMapper.mapToEntity(transactionDto);

        if (targetBankBook == null) {
            LocalDateTime endTransfer = LocalDateTime.now();

            t.setSourceBankBookId(sourceBankBook);
            t.setTargetBankBookId(targetBankBook);
            t.setStatus(statusEntityRepository.findByName("declined"));
            t.setInitiationDate(startTransfer);
            t.setCompletionDate(endTransfer);

            transactionEntityRepository.save(t);

            throw new TransferException("Перевод отклонен: пользователь или счет не существует!");
        } else if (sourceBankBook.getAmount().floatValue() < transactionDto.getAmount().floatValue()) {
            LocalDateTime endTransfer = LocalDateTime.now();

            t.setSourceBankBookId(sourceBankBook);
            t.setTargetBankBookId(targetBankBook);
            t.setStatus(statusEntityRepository.findByName("declined"));
            t.setInitiationDate(startTransfer);
            t.setCompletionDate(endTransfer);

            transactionEntityRepository.save(t);

            throw new TransferException("Перевод отклонен: на счете недостаточно средств");
        } else if (!currencyEntityRepository.findByName(sourceBankBook.getCurrency().getName()).getName().
                equals(currencyEntityRepository.findByName(targetBankBook.getCurrency().getName()).getName())) {
            LocalDateTime endTransfer = LocalDateTime.now();

            t.setSourceBankBookId(sourceBankBook);
            t.setTargetBankBookId(targetBankBook);
            t.setStatus(statusEntityRepository.findByName("declined"));
            t.setInitiationDate(startTransfer);
            t.setCompletionDate(endTransfer);

            transactionEntityRepository.save(t);

            throw new TransferException("Перевод отклонен: валюты счетов не совпадают!");
        } else {
            sourceBankBook.setAmount(sourceBankBook.getAmount().subtract(transactionDto.getAmount()));
            bankBookEntityRepository.save(sourceBankBook);
            targetBankBook.setAmount(targetBankBook.getAmount().add(transactionDto.getAmount()));
            bankBookEntityRepository.save(targetBankBook);

            LocalDateTime endTransfer = LocalDateTime.now();

            t.setSourceBankBookId(sourceBankBook);
            t.setTargetBankBookId(targetBankBook);
            t.setStatus(statusEntityRepository.findByName("successful"));
            t.setInitiationDate(startTransfer);
            t.setCompletionDate(endTransfer);

            transactionEntityRepository.save(t);
        }

        TransactionDto tDto = transactionMapper.mapToDto(t);

        return tDto;
    }
}
