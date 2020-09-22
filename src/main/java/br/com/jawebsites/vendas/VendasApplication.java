package br.com.jawebsites.vendas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jawebsites.vendas.domain.Categoria;
import br.com.jawebsites.vendas.domain.Produto;
import br.com.jawebsites.vendas.repositories.CategoriaRepository;
import br.com.jawebsites.vendas.repositories.ProdutoRepository;

@SpringBootApplication
public class VendasApplication implements CommandLineRunner  {

	@Autowired
	private CategoriaRepository repositorioCategoria;
	
	@Autowired
	private ProdutoRepository repositorioProduto;
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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
		
		
	}
	

}
