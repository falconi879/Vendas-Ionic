package br.com.jawebsites.vendas.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jawebsites.vendas.domain.Categoria;
import br.com.jawebsites.vendas.domain.dto.CategoriaDTO;
import br.com.jawebsites.vendas.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService servico;
	
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> listar() {
		List<Categoria> objeto = servico.listar();
		List<CategoriaDTO> lista = objeto.stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> Buscar(@PathVariable Integer id) {
		Categoria objeto = servico.buscar(id);
		return ResponseEntity.ok().body(objeto);
	}
	/**Iniciando CRUD**/
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Categoria obj){
		obj = servico.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Categoria obj,@PathVariable Integer id ){
		obj.setId(id); // garante o id correto
		obj = servico.alterar(obj);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Categoria> deletar(@PathVariable Integer id) {
	
		servico.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
