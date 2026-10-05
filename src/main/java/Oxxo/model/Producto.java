package Oxxo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "Producto")
public class Producto {

    @Id
    private Long id;

    private String nombre;

    private BigDecimal precio;

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
}