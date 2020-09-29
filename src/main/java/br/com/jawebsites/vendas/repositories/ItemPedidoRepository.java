package br.com.jawebsites.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jawebsites.vendas.domain.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
