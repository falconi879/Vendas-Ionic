package br.com.jawebsites.vendas.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.jawebsites.vendas.services.Validacao.ClienteInserir;

@ClienteInserir
public class NovoClienteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "Tamanho minimo 5 e maximo 120 caracteres")
	private String nome;

	@NotNull(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "Tamanho minimo 5 e maximo 120 caracteres")
	private String email;
	
	@NotNull(message = "Preenchimento obrigatório")
	private String cpfCnpj;
	
	private Integer tipoCliente;
	
	@NotNull(message = "Preenchimento obrigatório")
	private String rua;
	
	@NotNull(message = "Preenchimento obrigatório")
	private String numero;
	
	
	private String complemento;
	 	
	private String bairro;
	
	@NotNull(message = "Preenchimento obrigatório")
	@Length(min = 5,message = "Tamanho minimo 5 e maximo 120 caracteres")
	private String cep;
	
	@NotNull(message = "Preenchimento obrigatório")
	@Length(min = 5,message = "Tamanho minimo 5 e maximo 120 caracteres")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;
	
	public NovoClienteDTO() {
		
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
}
