package Oxxo.service;

import org.springframework.stereotype.Service;
import java.util.List;
import Oxxo.model.Pedido;
import Oxxo.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository repo;

    public PedidoService(PedidoRepository repo) {
        this.repo = repo;
    }

    public List<Pedido> obtenerPedidos() {
        return repo.findAll();
    }

    public Pedido guardar(Pedido pedido) {
        return repo.save(pedido);
    }

}
