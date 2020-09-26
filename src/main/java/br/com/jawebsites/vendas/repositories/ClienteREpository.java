package br.com.jawebsites.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jawebsites.vendas.domain.Cliente;

public interface ClienteREpository extends JpaRepository<Cliente, Integer> {
	

}
