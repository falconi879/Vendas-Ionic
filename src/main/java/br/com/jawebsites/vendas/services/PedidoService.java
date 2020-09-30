package br.com.jawebsites.vendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jawebsites.vendas.domain.Pedido;
import br.com.jawebsites.vendas.repositories.PedidoRepository;
import br.com.jawebsites.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repositorio;

	public List<Pedido> listar() {
		return repositorio.findAll();
		
	}
	public Pedido buscar(Integer id) {
		Optional<Pedido> objeto = repositorio.findById(id);
		return objeto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"+ id +" Tipo" + Pedido.class.getName())); 
	}
}
