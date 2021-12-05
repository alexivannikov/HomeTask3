package root.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import root.model.BankBookException;
import root.model.ErrorDto;

@RestControllerAdvice
public class BankBookControllerException {
    @ExceptionHandler(BankBookException.class)
    public ResponseEntity<ErrorDto> handleBankBookException(BankBookException bankBookException){
        ErrorDto errorDto = new ErrorDto(bankBookException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}
