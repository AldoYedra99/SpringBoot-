package Oxxo.exeption;

public class ProductoNotFoundException extends RuntimeException {

    public ProductoNotFoundException(Long id) {
        super("No se encontro el producto con ID:" +id);
    }
}