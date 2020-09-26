package br.com.jawebsites.vendas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jawebsites.vendas.domain.Cliente;
import br.com.jawebsites.vendas.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService servico;
	
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		List<Cliente> objeto = servico.listar();
		return ResponseEntity.ok().body(objeto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> Buscar(@PathVariable Integer id) {
		Cliente objeto = servico.buscar(id);
		return ResponseEntity.ok().body(objeto);
	}
	

}
