package Oxxo.Controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import Oxxo.model.Pedido;
import Oxxo.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pedido> listar() {
        return service.obtenerPedidos();
    }

}