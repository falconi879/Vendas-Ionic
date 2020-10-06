package br.com.jawebsites.vendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.jawebsites.vendas.domain.Categoria;
import br.com.jawebsites.vendas.domain.dto.CategoriaDTO;
import br.com.jawebsites.vendas.repositories.CategoriaRepository;
import br.com.jawebsites.vendas.services.exceptions.DataIntegrityException;
import br.com.jawebsites.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repositorio;

	public List<Categoria> listar() {
		return repositorio.findAll();
		
	}
	public Categoria buscar(Integer id) {
		Optional<Categoria> objeto = repositorio.findById(id);
		return objeto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"+ id +" Tipo" + Categoria.class.getName())); 
	}
	
	public Categoria inserir(Categoria obj) {
		obj.setId(null);// garantir novo id
		return repositorio.save(obj);
	}
	public Categoria alterar(Categoria obj) {
		Categoria novoObjeto = buscar(obj.getId());// ja faz a verificação do ID
		atualizar(novoObjeto, obj);
		return repositorio.save(novoObjeto);
	}
	 
	public void deletar(Integer id) {
		buscar(id); // usando o metodo para verificar se existe o id
		try {
		repositorio.deleteById(id);
		}
		catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("A Categoria não pode ser Excluida porque tem produtos nela");
		}
	}
	public Page<Categoria> paginacao (Integer pagina, Integer linhaPorPagina, String ordem, String direcao){
		PageRequest paginaRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(direcao),ordem);
		return repositorio.findAll(paginaRequest);
	}
	public Categoria aPartirDTO(CategoriaDTO objetoDTO) {
		return new Categoria(objetoDTO.getId(),objetoDTO.getNome());
	}
	private void atualizar(Categoria novoCliente, Categoria obj) {
		novoCliente.setNome(obj.getNome());
	}
	
	
}
 