package br.com.jawebsites.vendas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jawebsites.vendas.domain.Pedido;
import br.com.jawebsites.vendas.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService servico;
	
	
	@GetMapping
	public ResponseEntity<List<Pedido>> listar() {
		List<Pedido> objeto = servico.listar();
		return ResponseEntity.ok().body(objeto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> Buscar(@PathVariable Integer id) {
		Pedido objeto = servico.buscar(id);
		return ResponseEntity.ok().body(objeto);
	}
	

}
