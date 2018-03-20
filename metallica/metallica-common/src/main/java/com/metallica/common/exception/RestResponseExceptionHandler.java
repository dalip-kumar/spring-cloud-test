/**
 * 
 */
package com.metallica.common.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * @author dkum74
 *
 */
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ RecordNotFoundException.class })
	public ResponseEntity<Object> handleResoureNotFoundException (Exception ex, WebRequest request){
		return new ResponseEntity<Object>(
				ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
}
