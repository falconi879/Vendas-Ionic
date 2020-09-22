package br.com.jawebsites.vendas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jawebsites.vendas.domain.Categoria;
import br.com.jawebsites.vendas.repositories.CategoriaRepository;

@SpringBootApplication
public class VendasApplication implements CommandLineRunner  {

	@Autowired
	private CategoriaRepository repositorioCategoria;
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoria1 = new Categoria(null, "Informatica");
		Categoria categoria2 = new Categoria(null, "Escritorio");
		Categoria categoria3 = new Categoria(null, "Outros");
		
		repositorioCategoria.saveAll(Arrays.asList(categoria1,categoria2,categoria3));
	}
	

}
