package com.pragmatic.controller;
import com.pragmatic.controller.exception.CustomUrlBrokenTestException;
import com.pragmatic.controller.exception.ObjAlreadyExistsException;
import com.pragmatic.dto.AccountDto;
import com.pragmatic.model.Account;
import com.pragmatic.service.AccountServiceImpl;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@Log4j2
@AllArgsConstructor
public class AccountController {

    @Autowired
    private ApplicationContext appContext;
    AccountServiceImpl accountServiceImpl;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "Test") String name) {
        return String.format("Hello %s!", name);
    }

    @RequestMapping("/")
    public String home() {
        log.info("Hello world called");
        return "Hello World";
    }

    @GetMapping(value = "/getAccountById", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<AccountDto> getAccountById(
            @RequestParam(value = "id", defaultValue = "id")
            @Min(1)
            @Max(Integer.MAX_VALUE)
            Integer id)  {
        var account =  this.accountServiceImpl.getAccountById(id).orElseThrow(()->
                new NullPointerException("There is no acc by requested data"));
        log.info("getaccbyid got accountid");
        AccountDto accountDto = new AccountDto(account);
        log.info("getaccbyid reformatted acc to accdto to provide api response");
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @GetMapping(value = "/getBadRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBadRequest() throws CustomUrlBrokenTestException {
        var apiObj = Optional.ofNullable(null).orElseThrow(()-> new CustomUrlBrokenTestException("controller message"));
        return new ResponseEntity<>(new Object(), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDto>> getAllAccounts() {

        log.info("Receiving acc list from acc service");

        List<Account> accountList = this.accountServiceImpl.getAllAccounts();

        log.info("creating dto based on each acc info, to retun on api level");

        List<AccountDto> AccountDtos = accountList.stream()
                .map(account -> new AccountDto(account))
                .collect(Collectors.toList());

        return  new ResponseEntity<>(AccountDtos, HttpStatus.OK);
    }

    @PostMapping(value = "/createAccount", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccount(@Validated @RequestBody AccountDto inputAPIaccountDTO) throws ObjAlreadyExistsException {
        //не міняти тут нічого.
        Optional<Account> existingAcc =  accountServiceImpl.findAccountsByUserAndAccId(inputAPIaccountDTO.getUserId(), inputAPIaccountDTO.getCurrencyId());

        if (existingAcc.isEmpty() ) {
            return new  ResponseEntity<>(this.accountServiceImpl.createAccFromDto(inputAPIaccountDTO), HttpStatus.CREATED);
        }
        else {
            throw new ObjAlreadyExistsException(String.format("account with userid %d and currencyId %d",
                    inputAPIaccountDTO.getUserId(), inputAPIaccountDTO.getCurrencyId()));
        }
    }

    @GetMapping(value = "/getForbidden")
    public void getForbidden() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "/getNotFound")
    public void getNotFound() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/getPermanentRedirect")
    public ResponseEntity<?> getPermanentRedirect() throws URISyntaxException {
        return ResponseEntity.status(301).location(new URI("/")).build();
    }

    @GetMapping(value = "/getRedirect")
    public ResponseEntity<?> getRedirect() throws URISyntaxException {
        return ResponseEntity.status(302).location(new URI("/")).build();
    }

    //503 SERVICE_UNAVAILABLE
    @GetMapping(value = "/maintenance")
    public ResponseEntity<?> maintenance() throws URISyntaxException {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    //401 UNAUTHORIZED
    @GetMapping(value = "/authorization")
    public ResponseEntity<?> authorization() throws URISyntaxException {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    //ще в тасці були: 500 - сервер еррор ,201 - в методі існує,


}
