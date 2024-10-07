package dev.marcosbd.simple_twitter_backend.handler.globalException;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.DuplicateKeyException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ExceptionMessage> argumentNotValidHandler(MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        Map<String, String> errorMap = new HashMap<>();
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        for (FieldError fieldError : errors) {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        exceptionMessage.setError(errorMap);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ExceptionMessage> httpMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        Map<String, String> errorMap = new HashMap<>();
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setError(errorMap);
        errorMap.put("json", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage);
    }


    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ExceptionMessage> duplicateKeyException(DuplicateKeyException exception) {
        Map<String, String> errorMap = new HashMap<>();
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setError(errorMap);
        errorMap.put("owner", "owner already exists");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ResponseEntity<ExceptionMessage> handleGenericException(Exception exception) {
        Map<String, String> errorMap = new HashMap<>();
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        errorMap.put("error", exception.getMessage() != null ? exception.getMessage() : "An unexpected error occurred");
        exceptionMessage.setError(errorMap);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionMessage);
    }


    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class ExceptionMessage {

        private Map<String, String> error;

        public ExceptionMessage() {
            this.error = new HashMap<>();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Error Messages:\n");
            error.forEach((field, message) -> sb.append(field).append(": ").append(message).append("\n"));
            return sb.toString();
        }

    }


}
