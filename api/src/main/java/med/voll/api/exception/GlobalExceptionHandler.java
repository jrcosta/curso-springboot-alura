package med.voll.api.exception;

import jakarta.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

import med.voll.api.security.controller.DadosTokenJWT;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        var errors =
                exception.getBindingResult().getFieldErrors().stream()
                        .collect(
                                Collectors.toMap(
                                        error -> error.getField().toUpperCase(),
                                        error -> capitalize(error.getDefaultMessage())));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleSQLIntegrityConstraintViolationException(
            SQLIntegrityConstraintViolationException exception) {
        return ResponseEntity.badRequest()
                .body(Map.of("mensagem", "Ocorreu um erro de integridade de dados"));
    }

    private String capitalize(String message) {
        if (message == null || message.isEmpty()) {
            return "";
        }
        return message.substring(0, 1).toUpperCase() + message.substring(1);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<DadosTokenJWT> handleInvalidCredentials(InvalidCredentialsException ex) {
        return ResponseEntity.status(401).body(new DadosTokenJWT(ex.getMessage()));
    }
}
