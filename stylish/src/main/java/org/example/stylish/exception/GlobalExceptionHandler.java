package org.example.stylish.exception;

import org.example.stylish.dto.ErrorResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity handleServerException(Exception e) {
//        Map<String, Object> errorResponse = new HashMap<>();
//        errorResponse.put("error", e.getMessage());
//        return new ResponseEntity<>(new ErrorResponse("Server Error Reponse"), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(ProductApiException.class)
    public ResponseEntity<Map<String, Object>> handleProductException(ProductApiException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse("Duplicate email"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InputEmptyBlankException.class)
    public ResponseEntity handleSingUpEmptyBlankException(InputEmptyBlankException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NonExistedAccount.class)
    public ResponseEntity handleNonExistedAccountException(NonExistedAccount e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IncorrectPassword.class)
    public ResponseEntity handleIncorrectPasswordException(IncorrectPassword e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ProviderInvalidException.class)
    public ResponseEntity handleProviderInvalidException(ProviderInvalidException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity handleInvalidTokenException(InvalidTokenException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EmptyTokenException.class)
    public ResponseEntity handleEmptyTokenException(EmptyTokenException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidDetailInputException.class)
    public ResponseEntity handleInvalidDetailInputException(InvalidDetailInputException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Product ID not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity handleHttpClientErrorException(HttpClientErrorException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity handleProductNotExistException(ProductNotExistException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}


//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
//        Map<String, Object> errorResponse = new HashMap<>();
//        errorResponse.put("error", e.getMessage());
//        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity handleDataIntegrityViolationException(DataIntegrityViolationException e) {
//        Map<String, Object> errorResponse = new HashMap<>();
//        errorResponse.put("error", e.getMessage());
//        return new ResponseEntity<>(new ErrorResponse("Input data Can't be null)"), HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException e) {
//        Map<String, Object> errorResponse = new HashMap<>();
//        errorResponse.put("error", e.getMessage());
//        return new ResponseEntity<>(new ErrorResponse("Illegal"), HttpStatus.BAD_REQUEST);
//    }
//}
