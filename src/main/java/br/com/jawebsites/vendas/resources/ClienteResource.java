package br.com.jawebsites.vendas.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jawebsites.vendas.domain.Cliente;
import br.com.jawebsites.vendas.domain.dto.ClienteDTO;
import br.com.jawebsites.vendas.domain.dto.NovoClienteDTO;
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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@Valid @RequestBody ClienteDTO objDTO,@PathVariable Integer id ){
		Cliente obj = servico.aPartirDTO(objDTO);
		obj.setId(id); // garante o id correto
		obj = servico.alterar(obj);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cliente> deletar(@PathVariable Integer id) {
	
		servico.deletar(id);
		return ResponseEntity.noContent().build();
	}
	@GetMapping(value = "/paginacao")
	public ResponseEntity<Page<ClienteDTO>> paginacao (
			@RequestParam(value = "paginacao", defaultValue = "0") Integer pagina, 
			@RequestParam(value = "linhaPorPagina", defaultValue = "24")Integer linhaPorPagina, 
			@RequestParam(value = "ordem", defaultValue = "nome")String ordem, 
			@RequestParam(value = "direcao", defaultValue = "DESC")String direcao){
		Page<Cliente> objeto = servico.paginacao(pagina, linhaPorPagina, ordem, direcao);
		Page<ClienteDTO> lista = objeto.map(x -> new ClienteDTO(x));
		return ResponseEntity.ok().body(lista);
	}
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@Valid @RequestBody NovoClienteDTO objDTO){
		Cliente objeto = servico.aPartirDTO(objDTO);
		objeto = servico.inserir(objeto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(objeto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	

}
