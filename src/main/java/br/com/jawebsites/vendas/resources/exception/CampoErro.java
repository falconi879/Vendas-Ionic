package br.com.jawebsites.vendas.resources.exception;

import java.io.Serializable;

public class CampoErro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nomeCampo;
	private String message;
	
	public CampoErro() {
		
	}
	public CampoErro(String nomeCampo, String message) {
	
		this.nomeCampo = nomeCampo;
		this.message = message;
	}
	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
