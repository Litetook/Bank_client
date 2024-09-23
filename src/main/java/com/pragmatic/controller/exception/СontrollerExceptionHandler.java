package com.pragmatic.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        log.error("method validation exception", ex.getMessage());
        ErrorMessage message  = new ErrorMessage("Custom validation error",
                ex.getMessage(),
                "");
        return new  ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> objExists(Exception ex) {
        log.warn("Error when adding to repository");
        log.warn(ex.getMessage());
        ErrorMessage message = new ErrorMessage("obj already exists",
                ex.getMessage(),
                "");

        return  new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorMessage> nullValidation(NullPointerException ex) {
        log.error("null exception");
        ErrorMessage message = new ErrorMessage("data not found",
                ex.getMessage(),
                "");
        return  new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMessage> ForbiddenValidation(ResponseStatusException ex) {
        log.error(ex.getStatusCode());
        return  new ResponseEntity<>(ex.getStatusCode());
    }


    @ExceptionHandler(CustomUrlBrokenTestException.class)
    public ResponseEntity<ErrorMessage> brokenUrlValidate(Exception ex) {
        log.error("custom exception for broken url only");
        ErrorMessage message = new ErrorMessage("broken url error",
                ex.getMessage(),
                "broken url description");
        return  new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex) {
        log.error(ex.getMessage(), ex.getCause(),  ex.getStackTrace());
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                ex.getMessage(),
                "500 custom exception");
        log.error(message.toString());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
