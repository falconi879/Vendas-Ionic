package br.com.jawebsites.vendas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jawebsites.vendas.domain.Categoria;
import br.com.jawebsites.vendas.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repositorio;

	public Categoria buscar(Integer id) {
		
		Optional<Categoria> objeto = repositorio.findById(id);
		return objeto.orElse(null); 
	}
}
