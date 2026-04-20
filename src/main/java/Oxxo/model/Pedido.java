package Oxxo.model;

import Oxxo.model.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {

    @Id
    private int id;

    private String producto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Pedido() {
    }
    public Pedido(int id, String producto, Usuario usuario) {
        this.id = id;
        this.producto = producto;
    }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getProducto(){
            return producto;
        }
        public void setProducto(String producto){
        this.producto = producto;

    }
}
