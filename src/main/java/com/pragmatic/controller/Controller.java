package com.pragmatic.controller;
import com.pragmatic.controller.exception.CustomException;
import com.pragmatic.controller.exception.CustomUrlBrokenTestException;
import com.pragmatic.dto.AccountDto;
import com.pragmatic.model.Account;
import com.pragmatic.service.AccountServiceImpl;
import jakarta.validation.constraints.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@EnableAutoConfiguration
public class Controller {

    final AccountServiceImpl AccountServiceImpl;

//    сюди інжектити сервіси, не репозиторії
//    в сервысы мын валыдацыю, а вже в ньому дьоргати репо
    public Controller(AccountServiceImpl AccountServiceImpl) {
        this.AccountServiceImpl = AccountServiceImpl;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "Teest") String name) {
        return String.format("Hello %s!", name);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello World " + AccountServiceImpl.getAccount(1);
    }

    @GetMapping(value = "/getAccountById", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<AccountDto> getAccountById(
            @RequestParam(value = "id", defaultValue = "id")
            @Min(1)
            @Max(Integer.MAX_VALUE)
            Integer id)  {
//        Неправильно обробляю валідацію.
        var account =  this.AccountServiceImpl.getAccountById(id).orElseThrow(()-> new CustomException("blabla"));
        AccountDto accountDto = new AccountDto(account.getId(), account.getUserId(), account.getCurrencyId(), account.getBalance());
       return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @GetMapping(value = "/getBadRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBadRequest() throws CustomUrlBrokenTestException {
        var apiObj = Optional.ofNullable(null).orElseThrow(()-> new CustomUrlBrokenTestException("controlller message"));
        return new ResponseEntity<>(new Object(), HttpStatus.OK);
    }


    @GetMapping(value = "/getAllAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<Account> accountList = this.AccountServiceImpl.getAllAccounts();
        List<AccountDto> AccountDtos = accountList.stream()
                .map(account -> new AccountDto(account.getId(), account.getUserId(), account.getCurrencyId(), account.getBalance()))
                .collect(Collectors.toList());
        return  new ResponseEntity<>(AccountDtos, HttpStatus.OK);
    }





//   Те що зовні - не в базу, мають бути різні об'єкти перед передачою. Наприклад дто, наприклад акк дто ( дата транас обж)
//    Не завжди треба віддаватти всю інфу як в базі
//    один на рівні моделі ( те що в базу)
//    дто це те що віддаю наверх
//
//    TODO
//    @PostMapping(path= "addAccount", consumes = "application/json", produces = "application/json" )
//    public ResponseEntity<Account> addAccount(@RequestBody Account account) {

//        тут віддати аккаунт дто
//        окремо реквест, окремо резпонз
//      request, response, і те що до бази.
//        акк сервіс буде віддавати модель. її треба буде перегнати в дто
//        створення - контроллер (валідація на рівні арі), сервіс (передаю сирі дані,
//        валідація на те чи все ок з акком загалом,
//        чи є, чи нема, пробувати кидати через ексепшени),
//        далі репо. Почитати за екс зендлери, щоб створити для аккаунта свій
//        гет - контроллер, сервіс, і репозиторій. Якщо по ід і помилки нема - то треба віддати помилку. Спробувати віддати ліст ( наприклад всіх користувачів)
//        mvc описано спрінговий
//        update можна спробувати



//        Account newAccount = accountRepository.addAccount(account);
//        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
//    }


}
