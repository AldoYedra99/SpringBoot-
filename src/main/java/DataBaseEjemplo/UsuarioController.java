package DataBaseEjemplo;

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
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return repo.save(usuario);
    }
    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id){
        return repo.findById(id).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id){
        repo.deleteById(id);
    }
}
