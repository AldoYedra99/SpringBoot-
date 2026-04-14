package DataBaseEjemplo;

import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository repo;

    public UsuarioController(UsuarioRepository repo){
        this.repo = repo;
    }
    @GetMapping
    public List<Usuario> obtenerUsuarios(){
        return repo.findAll();
    }
    @PostMapping
    public Usuario crearUsuario(@Valid @RequestBody UsuarioDTO dto){
        Usuario usuario = new Usuario(
                dto.getNombre(),
                dto.getEmail()
        );
        return repo.save(usuario) ;
    }
    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id){
        return repo.findById(id).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id){
        repo.deleteById(id);
    }
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario u = repo.findById(id).orElse(null);

        if(u != null){
            u.setNombre(usuario.getNombre());
            u.setEmail(usuario.getEmail());
            return repo.save(u);
        }

        return null;
    }
}
