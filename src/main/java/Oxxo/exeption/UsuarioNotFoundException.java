package Oxxo.exeption;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException (String mensaje){
        super(mensaje);
    }
}
