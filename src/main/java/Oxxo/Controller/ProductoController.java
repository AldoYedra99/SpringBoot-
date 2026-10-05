package Oxxo.Controller;

import Oxxo.dto.ProductoDTO;
import Oxxo.dto.ProductoRequest;
import Oxxo.model.Producto;
import Oxxo.service.ProductoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerProductos() {

        return ResponseEntity.ok(
                service.obtenerProductos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarProductoPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(
            @RequestBody ProductoRequest request) {

        Producto producto = new Producto(
                request.getId(),
                request.getNombre(),
                request.getPrecio()
        );

        Producto productoGuardado =
                service.crearProducto(producto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(
            @PathVariable Long id) {

        service.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }
}
