package root.service;

import root.model.dto.BankBookDto;

import java.util.List;

public interface BankBookService {
    public List<BankBookDto> findAllBankBookDtoOfUser(Integer userId) throws Exception;

    public BankBookDto findBankBookDtoById(Integer bankBookId) throws Exception;

    public BankBookDto createBankBookDto(BankBookDto bankBookDto) throws Exception;

    public BankBookDto updateBankBookDto(BankBookDto bankBookDto) throws Exception;

    public Integer deleteBankBookDtoById(Integer bankBookId) throws Exception;

    public Integer deleteAllBankBookDtoOfUser(Integer userId) throws Exception;
}
