package br.com.jawebsites.vendas.domain;

import javax.persistence.Entity;

import br.com.jawebsites.vendas.domain.enuns.EstadoPagamento;
@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {
		
	}
	public PagamentoComCartao(Integer id, EstadoPagamento estado, Endereco enderecoEntrega, Cliente cliente,
			Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, enderecoEntrega, cliente, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}



	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
}
