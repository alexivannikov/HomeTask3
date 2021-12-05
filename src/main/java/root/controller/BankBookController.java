package root.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import root.model.BankBookDto;
import root.model.BankBookException;
import root.service.BankBookService;

import java.util.List;

@RestController
@RequestMapping("/bank-book")
public class BankBookController {
    private final BankBookService bankBookService;

    public BankBookController(BankBookService bankBookService){
        this.bankBookService = bankBookService;
    }

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity <List<BankBookDto>> findAllBankBookDtoOfUser(@PathVariable int userId) throws Exception {

        return ResponseEntity.ok(bankBookService.findAllBankBookDtoOfUser(userId));
    }

    @GetMapping("{bankBookId}")
    public ResponseEntity <BankBookDto> findBankBookDtoById (@PathVariable int bankBookId) throws Exception{

        return ResponseEntity.ok(bankBookService.findBankBookDtoById(bankBookId));

    }

    @PostMapping
    public ResponseEntity <BankBookDto> createBankBookDto(@RequestBody BankBookDto bankBookDto) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(bankBookService.createBankBookDto(bankBookDto));
    }

    @PutMapping
    public BankBookDto updateBankBookDto(@RequestBody BankBookDto bankBookDto) throws Exception{

        return bankBookService.updateBankBookDto(bankBookDto);
    }

    @DeleteMapping("/{bankBookId}")
    public ResponseEntity <Integer> deleteBankBookDtoById(@PathVariable int bankBookId) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(bankBookService.deleteBankBookDtoById(bankBookId));
    }

    @DeleteMapping("/by-user-id/{userId}")
    public ResponseEntity <Integer> deleteAllBankBookDtoOfUser(@PathVariable int userId) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(bankBookService.deleteAllBankBookDtoOfUser(userId));
    }

}
