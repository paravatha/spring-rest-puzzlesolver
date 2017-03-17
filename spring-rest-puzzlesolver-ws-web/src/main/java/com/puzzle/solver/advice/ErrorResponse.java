package com.puzzle.solver.advice;

import java.io.Serializable;

/**
 * Error response domain class
 * @author Prasad Paravatha
 *
 */
public class ErrorResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String error;
	private String message;
	
	public ErrorResponse(Integer code, String error, String message) {
		this.code = code;
		this.error = error;
		this.message = message;
	}
	
	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
    public String toString() {
        return String.format(
                "ErrorResponse[code=%s, error='%s', message='%s]",
                code.toString(), error, message );
    }	
}
