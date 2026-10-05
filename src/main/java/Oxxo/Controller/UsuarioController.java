package Oxxo.Controller;

import Oxxo.dto.UsuarioDTO;
import Oxxo.dto.UsuarioRequest;
import Oxxo.model.Usuario;
import Oxxo.service.UsuarioService;
import Oxxo.exeption.UsuarioNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

   /* @GetMapping
    public List<UsuarioDTO> obtenerUsuarios() {
        return service.obtenerUsuarios();
    }*/
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios(){
        return ResponseEntity.ok(
                service.obtenerUsuarios()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity <Usuario> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                service.buscarUsuarioPorId(id)
        );
    }

   @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioRequest req) {
        Usuario usuario = new Usuario();

        usuario.setId(req.getId());
        usuario.setNombre(req.getNombre());
        usuario.setEmail(req.getEmail());
        usuario.setPassword(req.getPassword());

        Usuario usuarioGuardado =
                service.crearUsuario(usuario);

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                (long) usuarioGuardado.getId(),
                usuarioGuardado.getNombre(),
                usuarioGuardado.getEmail()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        service.eliminarUsuario(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/buscar")
    public List<Usuario> buscar(@RequestParam String nombre) {
        return service.buscarPorNombre(nombre);
    }
}