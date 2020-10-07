package br.com.jawebsites.vendas.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.jawebsites.vendas.domain.Cliente;

public class ClienteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotNull(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "Tamanho minimo 5 e maximo 120 caracteres")
	private String nome;
	@NotNull(message = "Preenchimento obrigatório")
	@Email(message = "Email invalido")
	private String email;
	
	public ClienteDTO() {
		
	}
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = getNome();
		email = getEmail();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
