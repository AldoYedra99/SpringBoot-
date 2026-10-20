package Oxxo.service;

import Oxxo.dto.ProductoDTO;
import Oxxo.dto.ProductoRequest;
import Oxxo.exeption.ProductoNotFoundException;
import Oxxo.model.Producto;
import Oxxo.repository.ProductoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<ProductoDTO> obtenerProductos() {

        return repo.findAll()
                .stream()
                .map(p -> new ProductoDTO(
                        p.getId(),
                        p.getNombre(),
                        p.getPrecio()
                ))
                .toList();
    }

    public Producto buscarProductoPorId(Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new ProductoNotFoundException(
                                "Producto con ID " + id + " no encontrado"
                        )
                );
    }

    public Producto crearProducto(ProductoRequest request) {
        Producto producto = new Producto();

        producto.setId(request.getId());
        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());

        return repo.save(producto);
    }

    public void eliminarProducto(Long id) {

        if (!repo.existsById(id)) {

            throw new ProductoNotFoundException(
                    "Producto con ID " + id + " no encontrado"
            );
        }

        repo.deleteById(id);
    }
}