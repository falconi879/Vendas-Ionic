package br.com.jawebsites.vendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.jawebsites.vendas.domain.Cliente;
import br.com.jawebsites.vendas.domain.dto.ClienteDTO;
import br.com.jawebsites.vendas.repositories.ClienteRepository;
import br.com.jawebsites.vendas.services.exceptions.DataIntegrityException;
import br.com.jawebsites.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repositorio;

	public List<Cliente> listar() {
		return repositorio.findAll();
		
	}
	public Cliente buscar(Integer id) {
		Optional<Cliente> objeto = repositorio.findById(id);
		return objeto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"+ id +" Tipo" + Cliente.class.getName())); 
	}
	public Cliente alterar(Cliente obj) {
		Cliente novoObjeto = buscar(obj.getId());// ja faz a verificação do ID
		atualizar(novoObjeto, obj);
		return repositorio.save(novoObjeto);
	}
	 
	public void deletar(Integer id) {
		buscar(id); // usando o metodo para verificar se existe o id
		try {
		repositorio.deleteById(id);
		}
		catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("A Cliente não pode ser Excluida porque tem produtos nela");
		}
	}
	public Page<Cliente> paginacao (Integer pagina, Integer linhaPorPagina, String ordem, String direcao){
		PageRequest paginaRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(direcao),ordem);
		return repositorio.findAll(paginaRequest);
	}
	public Cliente aPartirDTO(ClienteDTO objetoDTO) {
		return new Cliente(objetoDTO.getId(), objetoDTO.getNome(), objetoDTO.getEmail(), null, null); 
	}
	private void atualizar(Cliente novoCliente, Cliente obj) {
		novoCliente.setNome(obj.getNome());
		novoCliente.setEmail(obj.getEmail());
	}

}
