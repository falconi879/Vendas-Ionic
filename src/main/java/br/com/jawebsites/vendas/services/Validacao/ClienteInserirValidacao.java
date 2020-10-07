package br.com.jawebsites.vendas.services.Validacao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.jawebsites.vendas.domain.dto.NovoClienteDTO;
import br.com.jawebsites.vendas.domain.enuns.TipoCliente;
import br.com.jawebsites.vendas.resources.exception.CampoErro;
import br.com.jawebsites.vendas.services.Validacao.utils.BR;

public class ClienteInserirValidacao implements ConstraintValidator<ClienteInserir, NovoClienteDTO> {
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
		
		for (CampoErro e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getNomeCampo())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}