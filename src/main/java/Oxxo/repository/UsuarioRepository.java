package Oxxo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import Oxxo.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNombre(String nombre);

    Optional<Usuario> findByEmail(String email);

    List<Usuario> id(int id);

    @Query("""
    SELECT DISTINCT 
    u FROM Usuario u
    LEFT JOIN FETCH u.productos
    WHERE u.id = :id
""")
    Optional<Usuario> buscarConProductos(@Param("id")Long id);
}
