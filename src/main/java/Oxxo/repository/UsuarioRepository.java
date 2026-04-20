package Oxxo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Oxxo.model.Usuario;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNombre(String nombre);
}
