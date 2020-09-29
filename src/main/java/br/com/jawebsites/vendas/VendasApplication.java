package br.com.jawebsites.vendas;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jawebsites.vendas.domain.Categoria;
import br.com.jawebsites.vendas.domain.Cidade;
import br.com.jawebsites.vendas.domain.Cliente;
import br.com.jawebsites.vendas.domain.Endereco;
import br.com.jawebsites.vendas.domain.Estado;
import br.com.jawebsites.vendas.domain.Pagamento;
import br.com.jawebsites.vendas.domain.PagamentoComBoleto;
import br.com.jawebsites.vendas.domain.PagamentoComCartao;
import br.com.jawebsites.vendas.domain.Pedido;
import br.com.jawebsites.vendas.domain.Produto;
import br.com.jawebsites.vendas.domain.enuns.EstadoPagamento;
import br.com.jawebsites.vendas.domain.enuns.TipoCliente;
import br.com.jawebsites.vendas.repositories.CategoriaRepository;
import br.com.jawebsites.vendas.repositories.CidadeRepository;
import br.com.jawebsites.vendas.repositories.ClienteRepository;
import br.com.jawebsites.vendas.repositories.EnderecoRepository;
import br.com.jawebsites.vendas.repositories.EstadoREpository;
import br.com.jawebsites.vendas.repositories.PagamentoRepository;
import br.com.jawebsites.vendas.repositories.PedidoRepository;
import br.com.jawebsites.vendas.repositories.ProdutoRepository;

@SpringBootApplication
public class VendasApplication implements CommandLineRunner  {

	@Autowired
	private CategoriaRepository repositorioCategoria;
	
	@Autowired
	private ProdutoRepository repositorioProduto;
	
	@Autowired
	private CidadeRepository repositorioCidade;
	
	@Autowired
	private EstadoREpository repositorioEstado;
	
	@Autowired
	private ClienteRepository repositoriCliente;
	
	@Autowired
	private EnderecoRepository repositorioEndereco;
	
	@Autowired
	private PedidoRepository repositorioPedido;
	
	@Autowired
	private PagamentoRepository repositorioPagamento;
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

	@Override
	public void run(String... args ) throws Exception {
		Categoria categoria1 = new Categoria(null, "Informatica");
		Categoria categoria2 = new Categoria(null, "Escritorio");
		Categoria categoria3 = new Categoria(null, "Outros");
		
		Produto produto1 = new Produto(null, "TV 40 Polegadas", 2.000);
		Produto produto2 = new Produto(null, "Computador Dell", 3.000);
		Produto produto3 = new Produto(null, "mouse", 54.50);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto2,produto3 ));
		categoria3.getProdutos().addAll(Arrays.asList(produto1 ));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria3));
		produto2.getCategorias().addAll(Arrays.asList(categoria1));
		produto3.getCategorias().addAll(Arrays.asList(categoria1)); 
		
		
		repositorioCategoria.saveAll(Arrays.asList(categoria1,categoria2,categoria3));
		repositorioProduto.saveAll(Arrays.asList(produto1,produto2,produto3));
		
		Estado estado1 = new Estado(null,"São Paulo");
		Estado estado2 = new Estado(null,"Rio de Janeiro");
		Estado estado3 = new Estado(null,"Minas Gerais");
		Estado estado4 = new Estado(null,"Espirito Santo");
		
		Cidade cidade1 = new Cidade(null, "Itaquaquecetuba", estado1);
		Cidade cidade2 = new Cidade(null, "Suzano", estado1);
		Cidade cidade3 = new Cidade(null, "Niterói", estado2);
		Cidade cidade4 = new Cidade(null, "Cariaçica", estado2);
		Cidade cidade5 = new Cidade(null, "Belo Horizonte", estado3);
		Cidade cidade6 = new Cidade(null, "Caldas Nova", estado3);
		Cidade cidade7 = new Cidade(null, "Governador Lindemberg", estado4);
		Cidade cidade8 = new Cidade(null, "Colatina", estado4);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1,cidade2));
		estado2.getCidades().addAll(Arrays.asList(cidade3,cidade4));
		estado3.getCidades().addAll(Arrays.asList(cidade5,cidade6));
		estado4.getCidades().addAll(Arrays.asList(cidade7,cidade8));
		
		repositorioEstado.saveAll(Arrays.asList(estado1,estado2,estado3,estado4));
		repositorioCidade.saveAll(Arrays.asList(cidade1,cidade2,cidade3,cidade4,cidade5,cidade6,cidade7,cidade8));
		
		Cliente cliente1 = new Cliente(null, "Vicente Falconi", "vicente@gmail.com", "123456789", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("2222222222","33333333333","999999999999"));
		
		Endereco endereco1 = new Endereco(null, "Rua Salesólolis", "234", "casa 2", "Vila Bartira", "08577470", cliente1, cidade1);

		Endereco endereco2 = new Endereco(null, "Av Itaquaquecetuba", "567", "casa 2", "Monte Belo", "08577000", cliente1, cidade1);
		cliente1.getEndereco().addAll(Arrays.asList(endereco1,endereco2));
		
		repositoriCliente.saveAll(Arrays.asList(cliente1));
		repositorioEndereco.saveAll(Arrays.asList(endereco1,endereco2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido pedido1 = new Pedido(null, sdf.parse("30/10/2019 10:25"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null,sdf.parse("30/11/2019 12:25") , cliente1, endereco2);
		
		Pagamento pag2 = new PagamentoComCartao(null, EstadoPagamento.AGUARDANDO,  pedido2, 3);
		pedido1.setPagamento(pag2);
		
		Pagamento pag1 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE,  pedido1, sdf.parse("29/10/2019 11:23"), null);
		pedido2.setPagamento(pag1);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1,pedido2));
		
		repositorioPedido.saveAll(Arrays.asList(pedido1,pedido2));
		repositorioPagamento.saveAll(Arrays.asList(pag1,pag2));
 	}
}
