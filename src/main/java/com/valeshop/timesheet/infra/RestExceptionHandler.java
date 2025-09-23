package com.valeshop.timesheet.infra;

import com.valeshop.timesheet.exceptions.DemandNotFoundExeption;
import com.valeshop.timesheet.exceptions.InvalidPasswordException;
import com.valeshop.timesheet.exceptions.UserAlreadyExistsException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<RestResponseMessage> userAlreadyExistsHandler(UserAlreadyExistsException exception) {
        RestResponseMessage threatResponse = new RestResponseMessage(HttpStatus.CONFLICT, exception.getMessage(), 409);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    private ResponseEntity<RestResponseMessage> handleDataIntegrityViolation(InvalidPasswordException exception) {
        String message = "Email ou senha incorreto";
        RestResponseMessage responseMessage = new RestResponseMessage(HttpStatus.CONFLICT, message, 409);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<RestResponseMessage> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        String message = "O email fornecido já está em uso.";
        RestResponseMessage responseMessage = new RestResponseMessage(HttpStatus.CONFLICT, message, 409);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseMessage);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    private ResponseEntity<RestResponseMessage> handleIndexOutOfBoundsException(IndexOutOfBoundsException exception){
        String message = "O index especificado não existe.";
        RestResponseMessage responseMessage = new RestResponseMessage(HttpStatus.NOT_FOUND, message, 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }
    @ExceptionHandler(DemandNotFoundExeption.class)
    private ResponseEntity<RestResponseMessage> handleDemandNotFoundExeption(DemandNotFoundExeption exception){
        String message = "A demanda especificada não existe.";
        RestResponseMessage responseMessage = new RestResponseMessage(HttpStatus.NOT_FOUND, message, 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

