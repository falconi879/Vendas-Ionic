package br.com.jawebsites.vendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jawebsites.vendas.domain.Cidade;
import br.com.jawebsites.vendas.domain.Cliente;
import br.com.jawebsites.vendas.domain.Endereco;
import br.com.jawebsites.vendas.domain.dto.ClienteDTO;
import br.com.jawebsites.vendas.domain.dto.NovoClienteDTO;
import br.com.jawebsites.vendas.domain.enuns.TipoCliente;
import br.com.jawebsites.vendas.repositories.ClienteRepository;
import br.com.jawebsites.vendas.repositories.EnderecoRepository;
import br.com.jawebsites.vendas.services.exceptions.DataIntegrityException;
import br.com.jawebsites.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repositorio;
	
	@Autowired
	private EnderecoRepository enderecoRepositorio;

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
	@Transactional
	public Cliente inserir(Cliente obj) {
		obj.setId(null);// garantir novo id
		obj = repositorio.save(obj);
		enderecoRepositorio.saveAll(obj.getEndereco());
		return obj;
	}
	
	 
	public void deletar(Integer id) {
		buscar(id); // usando o metodo para verificar se existe o id
		try {
		repositorio.deleteById(id);
		}
		catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("O Cliente não pode ser Excluida, Ele contem Pedidos");
		}
	}
	public Page<Cliente> paginacao (Integer pagina, Integer linhaPorPagina, String ordem, String direcao){
		PageRequest paginaRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(direcao),ordem);
		return repositorio.findAll(paginaRequest);
	}
	public Cliente aPartirDTO(ClienteDTO objetoDTO) {
		return new Cliente(objetoDTO.getId(), objetoDTO.getNome(), objetoDTO.getEmail(), null, null); 
	}
	
	public Cliente aPartirDTO(NovoClienteDTO objetoDTO) {
		Cliente cliente = new Cliente(null, objetoDTO.getNome(), objetoDTO.getEmail(), objetoDTO.getCpfCnpj(), TipoCliente.toEnum(objetoDTO.getTipoCliente()));
		Cidade cidade = new Cidade(objetoDTO.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, objetoDTO.getRua(), objetoDTO.getNumero(), objetoDTO.getComplemento(), objetoDTO.getBairro(), objetoDTO.getCep(), cliente, cidade);
		cliente.getEndereco().add(endereco);
		cliente.getTelefones().add(objetoDTO.getTelefone1());
		if(objetoDTO.getTelefone2()!=null) {
			cliente.getTelefones().add(objetoDTO.getTelefone2());
		}
		if(objetoDTO.getTelefone3()!=null) {
			cliente.getTelefones().add(objetoDTO.getTelefone3());
		}
		return cliente;
	}
	private void atualizar(Cliente novoCliente, Cliente obj) {
		novoCliente.setNome(obj.getNome());
		novoCliente.setEmail(obj.getEmail());
	}

}
