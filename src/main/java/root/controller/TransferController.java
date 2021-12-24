package root.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import root.model.dto.TransactionDto;
import root.service.TransferService;
import root.validation.EntityCreated;

import javax.validation.Valid;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService){
        this.transferService = transferService;
    }

    @Validated(EntityCreated.class)
    @PostMapping(value = "/me2me")
    public TransactionDto transfer(@Valid @RequestBody TransactionDto transactionDto) throws Exception {

        return transferService.transfer(transactionDto);
    }

    @Validated(EntityCreated.class)
    @PostMapping(value = "/to-another-user/{user}")
    public TransactionDto transfer(@PathVariable Integer user, @Valid @RequestBody TransactionDto transactionDto) throws Exception {

        return transferService.transfer(transactionDto, user);
    }


}
