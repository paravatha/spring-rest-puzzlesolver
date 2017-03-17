package com.puzzle.solver.advice;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Controller advice class to handle errors
 * @author Prasad Paravatha
 */
@ControllerAdvice
public class GlobalControllerAdvice {
	private static final Logger LOGGER = Logger.getLogger(GlobalControllerAdvice.class);
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ErrorResponse> handleBadRequests(NumberFormatException numberFormatException) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), 
				HttpStatus.BAD_REQUEST.getReasonPhrase(), "Check your input : " + numberFormatException.getMessage());
		LOGGER.error(numberFormatException.getMessage());
	    return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleBadRequests1(IllegalArgumentException illegalArgumentException) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), 
				HttpStatus.BAD_REQUEST.getReasonPhrase(), illegalArgumentException.getMessage());
		LOGGER.error(illegalArgumentException.getMessage());
	    return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleError404(HttpServletRequest request, NoHandlerFoundException noHandlerFoundException)   {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), 
				HttpStatus.NOT_FOUND.getReasonPhrase(), noHandlerFoundException.getMessage());
		LOGGER.error(noHandlerFoundException.getMessage());
	    return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleUnknownExceptions(Exception exception) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "Unexpected error, please try again later");
		LOGGER.error(exception.getMessage());
		LOGGER.error(exception.getCause().toString());
	    return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);		
	}
}
