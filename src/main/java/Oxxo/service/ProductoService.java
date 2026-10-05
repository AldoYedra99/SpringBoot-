package Oxxo.service;

import Oxxo.dto.ProductoDTO;
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

    public Producto crearProducto(Producto producto) {

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