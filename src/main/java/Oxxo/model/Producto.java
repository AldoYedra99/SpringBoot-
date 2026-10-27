package Oxxo.model;

import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

import java.math.BigDecimal;

@Entity
@Table(name = "Producto")
public class Producto {

    @Id
    private Long id;

    private String nombre;

    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "UsuarioID", nullable = false)
    private Usuario usuario;

    public Producto(){
    }

    public Producto(Long id, String nombre, BigDecimal precio){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public BigDecimal getPrecio(){
        return precio;
    }
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    public Usuario getUsuario(){
        return usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
}