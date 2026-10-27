package Oxxo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;

import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    private int id;
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

    public Usuario(int id, String nombre, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }
    public int getId() {
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
}

