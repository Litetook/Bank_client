package com.pragmatic.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

@Log4j2
@RestControllerAdvice
@ResponseBody
public class СontrollerExceptionHandler {

    @ExceptionHandler({
            HandlerMethodValidationException.class,
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class, //обов'язкові параметри
            HandlerMethodValidationException.class //помилки валідації в самому контроллері.
            })
    public ResponseEntity<ErrorMessage> validateError(Exception ex) {
        ErrorMessage message  = new ErrorMessage("Custom validation error",
                ex.getMessage(),
                "");
        return new  ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }
//    TODO як зробити так , щоб повертався бед реквест, на валідації. Це навчить обробляти певний вид виключень.
    //    TODO Створити едпоінт болванку, який завжди буде кидати кастомний експешен,
//     який я створю сам, і який оброблю в контроллер екс хендлер, і буде статус код 204.

    @ExceptionHandler(CustomUrlBrokenTestException.class)
    public ResponseEntity<ErrorMessage> brokenUrlValidate(Exception ex) {
        ErrorMessage message = new ErrorMessage("broken url error",
                ex.getMessage(),
                "broken url description");

        return  new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex) {
        log.error(ex.getMessage(), ex.getCause());
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                ex.getMessage(),
                "500 custom exception");
        log.error(message.toString());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }





}
