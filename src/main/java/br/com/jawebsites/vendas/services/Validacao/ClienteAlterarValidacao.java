package br.com.jawebsites.vendas.services.Validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.jawebsites.vendas.domain.Cliente;
import br.com.jawebsites.vendas.domain.dto.ClienteDTO;
import br.com.jawebsites.vendas.repositories.ClienteRepository;
import br.com.jawebsites.vendas.resources.exception.CampoErro;

public class ClienteAlterarValidacao implements ConstraintValidator<ClienteAlterar, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest requisicaoURI; //para conseguir pegar o ID na URI
	
	@Autowired
	private ClienteRepository repositorioCliente;
	
	@Override
	public void initialize(ClienteAlterar ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> mapeamentoURI = (Map<String, String>) requisicaoURI.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 
		Integer uriId = Integer.parseInt(mapeamentoURI.get("id"));
		
		List<CampoErro> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		Cliente auxiliar = repositorioCliente.findByEmail(objDto.getEmail());
		if(auxiliar != null && !auxiliar.getId().equals(uriId)) {
			list.add(new CampoErro("email", "Email ja existente!"));
		}
		
		for (CampoErro e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getNomeCampo())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}