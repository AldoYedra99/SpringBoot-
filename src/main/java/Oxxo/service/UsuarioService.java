package Oxxo.service;

import Oxxo.exeption.UsuarioNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import Oxxo.repository.UsuarioRepository;
import Oxxo.model.Usuario;
import Oxxo.dto.UsuarioDTO;
import Oxxo.exeption.UsuarioNotFoundException;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<UsuarioDTO> obtenerUsuarios(){
        return repo.findAll().stream()
                .map(u -> new UsuarioDTO(
                        (long) u.getId(),
                        u.getNombre(),
                        u.getEmail()
                ))
                .collect(Collectors.toList());
    }
    public List<Usuario> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    public Usuario crearUsuario(Usuario usuario) {
        return repo.save(usuario);
    }
    public void eliminarUsuario(long id) {
        if (!repo.existsById(id)){
            throw new UsuarioNotFoundException(
                    "Usuario con ID " + id + "no encontrado"
            );
        }
        repo.deleteById(id);
    }
    public Usuario buscarUsuarioPorEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() ->
                        new UsuarioNotFoundException(
                                "Usuario no encontrado"
                        )
                );
    }
    public Usuario buscarUsuarioPorId(Long id){
        return repo.findById(id)
                .orElseThrow(() ->
                        new UsuarioNotFoundException(
                                "Usuario con ID " + id + " no encontrado"
                        )
                );
    }
}
