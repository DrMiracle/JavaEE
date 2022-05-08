package kma.topic5.springwebsample;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Set<String>> handleError(ConstraintViolationException exception){
        Set<String> errors = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toSet());
        return ResponseEntity.badRequest().body(errors);
    }
}