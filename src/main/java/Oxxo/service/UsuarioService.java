package Oxxo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import Oxxo.repository.UsuarioRepository;
import Oxxo.model.Usuario;
import Oxxo.dto.UsuarioDTO;
import Oxxo.exeption.UsuarioNotFoundException;
import Oxxo.dto.ProductoResumenDTO;
import Oxxo.dto.UsuarioProductosDTO;
import Oxxo.dto.UsuarioRolesDTO;
import Oxxo.model.Rol;
import Oxxo.dto.UsuarioResponseDTO;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repo,
                          PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
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
        usuario.setPassword(
                passwordEncoder.encode(usuario.getPassword())
        );
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

    public UsuarioProductosDTO obtenerUsuarioConProductos(Long id){
        Usuario usuario = repo.buscarConProductos(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No se encontro el Usuario con ID: "+id
                        )
                );

        var productosODT = usuario.getProductos()
                .stream()
                .map(producto -> new ProductoResumenDTO(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getPrecio()
                ))
                .collect(Collectors.toList());

        return new UsuarioProductosDTO(
                (long)usuario.getId(),
                usuario.getNombre(),
                productosODT
        );
    }

    public UsuarioRolesDTO obtenerUsuarioConRoles(Long id){
        Usuario usuario = repo.findWithRolesById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No se encontro el usuario con ID:" +id
                        ));
        var nombresRoles = usuario.getRoles()
                .stream()
                .map(Rol::getNombre)
                .collect(Collectors.toSet());

        return new UsuarioRolesDTO(
                (long)usuario.getId(),
                usuario.getNombre(),
                nombresRoles
        );
    }
    public UsuarioResponseDTO convertirAResponseDTO(Usuario usuario) {

        return new UsuarioResponseDTO(
                (long)usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }
}
