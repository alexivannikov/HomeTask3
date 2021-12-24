package root.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import root.model.dto.BankBookDto;
import root.service.BankBookService;
import root.validation.EntityCreated;
import root.validation.EntityUpdated;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bank-book")
public class BankBookController {
    private final BankBookService bankBookService;

    public BankBookController(BankBookService bankBookService){
        this.bankBookService = bankBookService;
    }

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity <List<BankBookDto>> findAllBankBookDtoOfUser(@PathVariable Integer userId) throws Exception {

        return ResponseEntity.ok(bankBookService.findAllBankBookDtoOfUser(userId));
    }

    @GetMapping("{bankBookId}")
    public ResponseEntity <BankBookDto> findBankBookDtoById (@PathVariable Integer bankBookId) throws Exception{

        return ResponseEntity.ok(bankBookService.findBankBookDtoById(bankBookId));

    }

    @Validated(EntityCreated.class)
    @PostMapping
    public ResponseEntity <BankBookDto> createBankBookDto(@Valid @RequestBody BankBookDto bankBookDto) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(bankBookService.createBankBookDto(bankBookDto));
    }

    @Validated(EntityUpdated.class)
    @PutMapping
    public BankBookDto updateBankBookDto(@Valid @RequestBody BankBookDto bankBookDto) throws Exception{

        return bankBookService.updateBankBookDto(bankBookDto);
    }

    @DeleteMapping("/{bankBookId}")
    public ResponseEntity <Integer> deleteBankBookDtoById(@PathVariable Integer bankBookId) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(bankBookService.deleteBankBookDtoById(bankBookId));
    }

    @DeleteMapping("/by-user-id/{userId}")
    public ResponseEntity <Integer> deleteAllBankBookDtoOfUser(@PathVariable Integer userId) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(bankBookService.deleteAllBankBookDtoOfUser(userId));
    }

}
