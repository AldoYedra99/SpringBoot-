package Oxxo.Controller;

import Oxxo.dto.ProductoDTO;
import Oxxo.dto.ProductoRequest;
import Oxxo.model.Producto;
import Oxxo.service.ProductoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.math.BigDecimal;

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
            @Valid @RequestBody ProductoRequest request) {

        Producto producto = service.crearProducto(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(
            @PathVariable Long id) {

        service.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Page<Producto>> obtenerProductosPaginados(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamaño,
            @RequestParam(defaultValue = "id") String ordenarPor,
            @RequestParam(defaultValue = "asc") String direccion){

        if(pagina < 0 || tamaño < 1 || tamaño > 100){
            throw new IllegalArgumentException(
                    "La página debe ser >= y el tamaño debe estar entre 1 y 100");
        }

        Sort sort = direccion.equalsIgnoreCase("desc")
                ? Sort.by(ordenarPor).descending()
                : Sort.by(ordenarPor).ascending();

        Pageable pageable = PageRequest.of(pagina,tamaño,sort);

        return ResponseEntity.ok(
                service.obtenerProducosPaginados(pageable)
        );
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Producto>> buscarProductos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) BigDecimal precioMin,
            @RequestParam(required = false) BigDecimal precioMax,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamaño,
            @RequestParam(defaultValue = "id") String ordenarPor,
            @RequestParam(defaultValue = "asc") String direccion){

        if (pagina < 0 || tamaño < 1 || tamaño > 100){
            throw new IllegalArgumentException(
                    "La págin debe ser >=0 y el tamaño debe estar entre 1 y 100");
        }

        if(!ordenarPor.equals("id")
            && !ordenarPor.equals("nombre")
            && !ordenarPor.equals("precio")){
            throw new IllegalArgumentException(
                    "El campo de ordenamiento no es valido");
        }

        if (!direccion.equalsIgnoreCase("asc")
                && !direccion.equalsIgnoreCase("desc")) {
            throw new IllegalArgumentException(
                    "La direccion debe ser asc o desc");
        }

        Sort sort = direccion.equalsIgnoreCase("desc")
                ? Sort.by(ordenarPor).descending()
                : Sort.by(ordenarPor).ascending();

        Pageable pageable = PageRequest.of(pagina, tamaño, sort);

        return ResponseEntity.ok(
                service.buscarConFiltros(
                        nombre, precioMin, precioMax , pageable)
        );
    }
}

