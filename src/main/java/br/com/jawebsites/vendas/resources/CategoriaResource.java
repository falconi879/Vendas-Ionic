package br.com.jawebsites.vendas.resources;

import java.util.ArrayList;
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
	public List<Categoria> listar() {
		
	
		Categoria categoria = new Categoria(1, "Informatica");
		Categoria categoria2 = new Categoria(2, "Escritorio");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(categoria);
		lista.add(categoria2);
		return lista;
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> Buscar(@PathVariable Integer id) {
		Categoria objeto = servico.buscar(id);
		return ResponseEntity.ok().body(objeto);
	}
	

}
