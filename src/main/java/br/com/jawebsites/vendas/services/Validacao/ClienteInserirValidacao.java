package br.com.jawebsites.vendas.services.Validacao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.jawebsites.vendas.domain.Cliente;
import br.com.jawebsites.vendas.domain.dto.NovoClienteDTO;
import br.com.jawebsites.vendas.domain.enuns.TipoCliente;
import br.com.jawebsites.vendas.repositories.ClienteRepository;
import br.com.jawebsites.vendas.resources.exception.CampoErro;
import br.com.jawebsites.vendas.services.Validacao.utils.BR;

public class ClienteInserirValidacao implements ConstraintValidator<ClienteInserir, NovoClienteDTO> {
	
	@Autowired
	private ClienteRepository repositorioCliente;
	@Override
	public void initialize(ClienteInserir ann) {
	}

	@Override
	public boolean isValid(NovoClienteDTO objDto, ConstraintValidatorContext context) {
		List<CampoErro> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfCnpj())) {
			list.add(new CampoErro("cpfCnpj", "CPF inválido!"));
		}
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfCnpj())) {
			list.add(new CampoErro("cpfCnpj", "CNPJ inválido!"));
		}
		Cliente auxiliar = repositorioCliente.findByEmail(objDto.getEmail());
		if(auxiliar != null) {
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