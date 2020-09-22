package br.com.jawebsites.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jawebsites.vendas.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
