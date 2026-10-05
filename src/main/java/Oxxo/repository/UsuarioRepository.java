package Oxxo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Oxxo.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNombre(String nombre);

    Optional<Usuario> findByEmail(String email);

    List<Usuario> id(int id);
}
