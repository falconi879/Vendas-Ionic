package br.com.jawebsites.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jawebsites.vendas.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
