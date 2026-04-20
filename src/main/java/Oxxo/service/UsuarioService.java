package Oxxo.service;

import org.springframework.stereotype.Service;
import java.util.List;
import Oxxo.model.Usuario;
import Oxxo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<Usuario> obtenerUsuarios() {
        return repo.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return repo.save(usuario);
    }

}
