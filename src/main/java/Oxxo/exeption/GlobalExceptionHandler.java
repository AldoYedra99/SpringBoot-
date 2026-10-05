package Oxxo.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<Map<String, String>> usuarioNoEncontrado(
            UsuarioNotFoundException ex) {

        Map<String, String> respuesta = new HashMap<>();

        respuesta.put("error", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(respuesta);
    }
    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<Map<String, String>> productoNoEncontrado(
            ProductoNotFoundException ex) {

        Map<String, String> respuesta = new HashMap<>();

        respuesta.put("error", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(respuesta);
    }
}
