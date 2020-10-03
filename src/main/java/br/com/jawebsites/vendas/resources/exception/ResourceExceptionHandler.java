package br.com.jawebsites.vendas.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.jawebsites.vendas.services.exceptions.DataIntegrityException;
import br.com.jawebsites.vendas.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandarError> erros(ObjectNotFoundException msg, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
	
		StandarError err = new StandarError(status.value(), msg.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandarError> Integridade(DataIntegrityException msg, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
	
		StandarError err = new StandarError(status.value(), msg.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandarError> validacao(MethodArgumentNotValidException msg, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
			ValidacaoError err = new ValidacaoError(status.value(), "Erro de validação", System.currentTimeMillis());
		
		 for (FieldError x: msg.getBindingResult().getFieldErrors()) {
			err.addErro(x.getField(), x.getDefaultMessage());; 
			
		}
		return ResponseEntity.status(status).body(err);
	}
	
}
