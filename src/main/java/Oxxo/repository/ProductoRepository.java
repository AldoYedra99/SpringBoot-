package Oxxo.repository;

import Oxxo.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;


public interface ProductoRepository
        extends JpaRepository<Producto, Long> {

    @Query("""
        SELECT p FROM Producto p
        WHERE (:nombre IS NULL
        OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
       AND (:precioMin IS NULL OR p.precio >= :precioMin)
       AND (:precioMax IS NULL OR p.precio <= :precioMax)
    """)
    Page<Producto> buscarConFiltros(
            @Param("nombre") String nombre,
            @Param("precioMin") BigDecimal precioMin,
            @Param("precioMax") BigDecimal precioMax,
            Pageable pageable
    );
}
