package br.com.jawebsites.vendas.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.jawebsites.vendas.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandarError> erros(ObjectNotFoundException msg, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
	
		StandarError err = new StandarError(status.value(), msg.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(status).body(err);
	}

}
