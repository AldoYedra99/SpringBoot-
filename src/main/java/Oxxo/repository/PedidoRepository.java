package Oxxo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Oxxo.model.Pedido;
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}