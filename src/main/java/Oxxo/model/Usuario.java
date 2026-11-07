package Oxxo.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

    @OneToMany(
            mappedBy = "usuario",
            fetch = FetchType.LAZY
    )
    private List<Producto> productos = new ArrayList<>();

    public Usuario() {}

    public Usuario(Integer id, String nombre, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }
    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//UsuariosONETOMANY
    public List<Producto> getProductos(){
        return productos;
    }
    public void setProductos(List<Producto> productos){
        this.productos = productos;
    }

    //ROL
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Usuario_Rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();

    public Set<Rol> getRoles(){
        return roles;
    }
    public void setRoles(Set<Rol> roles){
        this.roles = roles;
    }
}

