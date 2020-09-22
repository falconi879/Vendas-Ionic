package br.com.jawebsites.vendas.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jawebsites.vendas.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	
	@GetMapping
	public List<Categoria> listar() {
		
		Categoria categoria = new Categoria(1, "Informatica");
		Categoria categoria2 = new Categoria(2, "Escritorio");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(categoria);
		lista.add(categoria2);
		return lista;
	}

}
