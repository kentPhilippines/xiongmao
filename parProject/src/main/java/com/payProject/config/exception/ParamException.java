package com.payProject.config.exception;

/**
 * <p>参数为null的异常</p>
 * @author K
 * 2019-08-03
 *
 */
public class ParamException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public	ParamException(){}
	public ParamException(String message){
        super(message);
    }
}
