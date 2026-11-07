package Oxxo.service;

import Oxxo.dto.ProductoDTO;
import Oxxo.dto.ProductoRequest;
import Oxxo.exeption.ProductoNotFoundException;
import Oxxo.model.Producto;
import Oxxo.repository.ProductoRepository;
import Oxxo.dto.ProductoResponseDTO;
import Oxxo.dto.ProductoActualizarRequest;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.math.BigDecimal;

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
                        new ProductoNotFoundException(id));
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

            throw new ProductoNotFoundException(id);
        }

        repo.deleteById(id);
    }

    public Page<Producto> obtenerProducosPaginados(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Page<Producto> buscarConFiltros(
            String nombre,
            BigDecimal precioMin,
            BigDecimal precioMax,
            Pageable pageable) {

        if (precioMin != null
                && precioMax != null
                && precioMin.compareTo(precioMax) > 0) {
            throw new IllegalArgumentException(
                    "El precio minimo no se puede superar al precio maximo");
        }

        return repo.buscarConFiltros(
                nombre, precioMin, precioMax, pageable);
    }

    public ProductoResponseDTO convertitADTO(Producto producto) {
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getUsuario().getId(),
                producto.getUsuario().getNombre()
        );
    }

    //Usuario   Productos
    public List<ProductoResponseDTO> obtenerProductosConUsuario() {
        return repo.buscarProductosConUsuario();
    }

    //BuscarporProducto
    public List<ProductoResponseDTO> buscarPorNombreConUsuario(
            String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre de busqueda no puede estar vacio"
            );
        }
        return repo.buscarPorNombreConUsuario(
                nombre.trim()
        );
    }

    @Transactional
    public ProductoResponseDTO actualizarProducto(
            Long id,
            ProductoActualizarRequest request) {

        Producto producto = repo.findById(id)
                .orElseThrow(() ->
                        new ProductoNotFoundException(id)
                );
        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());

        Producto actualizado = repo.save(producto);

        return new ProductoResponseDTO(
                actualizado.getId(),
                actualizado.getNombre(),
                actualizado.getPrecio(),
                actualizado.getUsuario().getId(),
                actualizado.getUsuario().getNombre()
        );
    }
}