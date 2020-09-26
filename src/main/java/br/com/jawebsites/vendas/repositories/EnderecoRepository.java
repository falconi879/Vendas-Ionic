package br.com.jawebsites.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jawebsites.vendas.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
