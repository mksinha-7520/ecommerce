package com.ecommerce.common;

import com.prj.ecomm.products.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(value={ConstraintViolationException.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValid(ConstraintViolationException  ex, WebRequest request) {

      String errMsg =   ex.getConstraintViolations().stream().map(c->  c.getMessage())
              .collect(Collectors.joining(";"));
        return new ResponseEntity<Object>(errMsg, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value={ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildResponseEntity(new ClientErrorMessage(ex.getMessage(),HttpStatus.NOT_FOUND ));
    }

    private ResponseEntity buildResponseEntity(ClientErrorMessage ce){
    return new ResponseEntity<>(ce, ce.getStatus());
    }
    @ExceptionHandler(value={Exception.class})
    public ResponseEntity handleUnKnownException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

    }


}

