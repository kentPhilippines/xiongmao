package com.payProject.config.exception;

/**
 * <p>发生其他错误时候的异常</p>
 * @author K
 *	2019-08-09
 */
public class OtherErrors extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public	OtherErrors(){}
	public OtherErrors(String message){
        super(message);
    }
}
