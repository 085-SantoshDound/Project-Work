package com.app.exception_handling;


import java.io.ObjectInputStream.GetField;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.app.dto.ErrorResponse;

@ControllerAdvice // necesarry to tell sc that this is globally exp handling class and intecept all exception
//method and all rest controller type exception is handled
public class GloballyExceptionHandling extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exc,
			HttpHeaders headers, HttpStatus status, WebRequest req)
	{
		System.out.println("In global exc handler");
		Map<String, String> errorMap=exc.getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField,
				FieldError::getDefaultMessage));
	//	Map<String , String> errorMap=new HashMap<>();
		//	for(FieldError e : ex.getFieldErrors())
		//	errorMap.put(e.getField(), e.getDefaultMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleRunTimeException(Exception e)
	{
		System.out.println("In handle runtime exp method 2nd ");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
				body(new ErrorResponse(e.getMessage()));
		
	}
	
}
