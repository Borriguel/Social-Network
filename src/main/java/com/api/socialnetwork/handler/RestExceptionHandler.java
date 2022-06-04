package com.api.socialnetwork.handler;

import com.api.socialnetwork.exception.BadRequestException;
import com.api.socialnetwork.exception.BadRequestExceptionDetails;
import com.api.socialnetwork.exception.ValidationExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException br){
        var badRequestException = new BadRequestExceptionDetails();
        badRequestException.setTempo(LocalDateTime.now());
        badRequestException.setDetalhes(br.getMessage());
        badRequestException.setStatus(HttpStatus.BAD_REQUEST.value());
        badRequestException.setErro("Bad Request Exception, verifique a documentação");
        badRequestException.setMensagemDev(br.getClass().getName());
        return new ResponseEntity<>(badRequestException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException vr){
        List<String> erros = new ArrayList<>();
        for(FieldError error: vr.getBindingResult().getFieldErrors()){
            erros.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for(ObjectError error: vr.getBindingResult().getGlobalErrors()){
            erros.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        var validationException = new ValidationExceptionDetails();
        validationException.setTempo(LocalDateTime.now());
        validationException.setDetalhes(erros.toString());
        validationException.setStatus(HttpStatus.BAD_REQUEST.value());
        validationException.setErro("Method Argument Not Valid Exception, campos inválidos");
        validationException.setMensagemDev(vr.getClass().getName());
        return new ResponseEntity<>(validationException, HttpStatus.BAD_REQUEST);
    }
}
