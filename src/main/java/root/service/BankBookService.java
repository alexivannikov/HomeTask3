package root.service;

import root.model.BankBookDto;

import java.util.List;

public interface BankBookService {
    public List<BankBookDto> findAllBankBookDtoOfUser(int userId) throws Exception;

    public BankBookDto findBankBookDtoById(int bankBookId) throws Exception;

    public BankBookDto createBankBookDto(BankBookDto bankBookDto) throws Exception;

    public BankBookDto updateBankBookDto(BankBookDto bankBookDto) throws Exception;

    public Integer deleteBankBookDtoById(int bankBookId) throws Exception;

    public int deleteAllBankBookDtoOfUser(int userId) throws Exception;
}
