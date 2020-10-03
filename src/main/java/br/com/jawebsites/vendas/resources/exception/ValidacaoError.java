package br.com.jawebsites.vendas.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoError extends StandarError{

	private static final long serialVersionUID = 1L;
	
	private List<CampoErro> listaErros = new ArrayList<>();

	public ValidacaoError(Integer status, String msg, Long time) {
		super(status, msg, time);
	}

	public List<CampoErro> getListaDeErros() {
		return listaErros;
	}

	public void addErro(String nomeCampo, String message) {
		listaErros.add(new CampoErro(nomeCampo, message));
	}
}
