package rocha.andre.project.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataValidationError::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidJsonException) {
            return ResponseEntity.badRequest().body(ex.getCause().getMessage());
        } else {
            return ResponseEntity.badRequest().body("Erro de leitura do JSON: " + ex.getMessage());
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handleAuthenticationException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro no processo de autorização do usuário.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado.");
    }


    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity handleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Erro no processo de autorização do usuário.\nSenha e/ou login errados.");
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity handlePropertyReferenceException(PropertyReferenceException ex) {
        return ResponseEntity.badRequest().body("Erro de consulta: " + ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleValidationException(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record DataValidationError(String field, String message) {
        public DataValidationError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A URL não foi encontrada.");
    }

    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public ResponseEntity handleNoResourceFoundException(org.springframework.web.servlet.resource.NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A URL não foi encontrada.");
    }

    @ExceptionHandler(DontHaveEnoughFunds.class)
    public ResponseEntity handleDontHaveEnoughFunds(DontHaveEnoughFunds ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
    }
}
