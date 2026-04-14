package DataBaseEjemplo;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String manejarErrores(MethodArgumentNotValidException ex){
        return ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();
    }

}