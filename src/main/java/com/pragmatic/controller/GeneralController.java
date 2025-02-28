package com.pragmatic.controller;
import com.pragmatic.controller.exception.CustomUrlBrokenTestException;
import com.pragmatic.controller.exception.MoneyTransferException;
import com.pragmatic.controller.exception.ObjAlreadyExistsException;
import com.pragmatic.controller.exception.ObjNotFoundException;
import com.pragmatic.dto.AccountDto;
import com.pragmatic.dto.UserDto;
import com.pragmatic.controller.dto.request.MoneyTransferRequest;
import com.pragmatic.controller.dto.request.TransactionsByRangeRequest;
import com.pragmatic.controller.dto.request.UserCreateRequest;
import com.pragmatic.model.Account;
import com.pragmatic.model.Transaction;
import com.pragmatic.service.AccountService;
import com.pragmatic.service.UserService;
import com.pragmatic.service.impl.TransactionServiceImpl;
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


@RestController
@Log4j2
@AllArgsConstructor
public class GeneralController {

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    AccountService accountServiceImpl;

    @Autowired
    UserService userService;

    @Autowired
    TransactionServiceImpl transactionService;


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "Test") String name) {
        return String.format("Hello %s!", name);
    }

    @RequestMapping("/")
    public String home() {
        log.info("Hello world called");
        return "Hello World";
    }

    @PostMapping(value = "/createUser", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@Validated @RequestBody UserCreateRequest userRequest) throws ObjAlreadyExistsException {
        UserDto newUser = userService.createUserFromRequest(userRequest);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @GetMapping(value = "/getUserById")
    public ResponseEntity<UserDto> getUserById(
            @RequestParam(value = "id")
            @Min(1)
            @NotNull
            Long userId
    ) throws ObjNotFoundException {
        UserDto userDto = userService.findUserById(userId);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(value="/getAllUsers")
    public ResponseEntity<List<UserDto>> getAccountsByUserId() {
        List<UserDto> userDtoList = userService.findAll();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/createAccount", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccount(@Validated @RequestBody AccountDto inputAPIaccountDTO) throws ObjAlreadyExistsException {
        //не міняти тут нічого.
        Optional<Account> existingAcc =  accountServiceImpl.findExistAccountByParams(inputAPIaccountDTO.getCurrencyId(), inputAPIaccountDTO.getUserId());

        if (existingAcc.isEmpty() ) {
            return new  ResponseEntity<>(this.accountServiceImpl.createAccountFromDto(inputAPIaccountDTO), HttpStatus.CREATED);
        }
        else {
            throw new ObjAlreadyExistsException(String.format("account with userid %d and currencyId %d",
                    inputAPIaccountDTO.getUserId(), inputAPIaccountDTO.getCurrencyId()));
        }
    }

    @GetMapping(value = "/getAccountById", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<AccountDto> getAccountById(
            @RequestParam(value = "id", defaultValue = "id")
            @Min(1)
            @Max(Long.MAX_VALUE)
            Long id) throws ObjNotFoundException {
        return new ResponseEntity<>(accountServiceImpl.getAccountById(id), HttpStatus.OK);
    }

//    @GetMapping(value = "/getBadRequest", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> getBadRequest() throws CustomUrlBrokenTestException {
//        var apiObj = Optional.ofNullable(null).orElseThrow(()-> new CustomUrlBrokenTestException("controller message"));
//        return new ResponseEntity<>(new Object(), HttpStatus.OK);
//    }


    @GetMapping(value="/getAccountsByUserId")
    public ResponseEntity<List<AccountDto>> getAccountsByUserId(
            @RequestParam(value="userId", defaultValue = "userId")
            Long userId ) {
                List<AccountDto> accountDtoList = accountServiceImpl.findAccountsByUserId(userId);
                return new ResponseEntity<>(accountDtoList, HttpStatus.OK);
    }


    @PostMapping(value = "/getTransactionsByDateRange")
    public ResponseEntity<List<Transaction>> getTransactionsByDateRange(@Validated @RequestBody TransactionsByRangeRequest transactionDateRangeRequest) {
        List<Transaction> transactionList =  transactionService.findTransactionsByDateRange(transactionDateRangeRequest);
        return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }


    @PostMapping(value =  "/moneyTransfer")
    public ResponseEntity<Transaction> moneyTransfer(@Validated @RequestBody MoneyTransferRequest moneyTransferRequest) throws ObjNotFoundException, MoneyTransferException {
        Transaction transaction = accountServiceImpl.moneyTransfer(moneyTransferRequest);
        return new  ResponseEntity<>(transaction, HttpStatus.OK);
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
