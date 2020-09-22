package br.com.jawebsites.vendas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jawebsites.vendas.domain.Categoria;
import br.com.jawebsites.vendas.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService servico;
	
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		List<Categoria> objeto = servico.listar();
		return ResponseEntity.ok().body(objeto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> Buscar(@PathVariable Integer id) {
		Categoria objeto = servico.buscar(id);
		return ResponseEntity.ok().body(objeto);
	}
	

}
