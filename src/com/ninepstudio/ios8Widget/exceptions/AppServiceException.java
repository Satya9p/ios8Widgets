package com.ninepstudio.ios8Widget.exceptions;

public class AppServiceException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppServiceException(String errCode, String errMsg){
		super(errCode, errMsg);
	}

	public AppServiceException(String errCode, String errMsg, Throwable e){
		super(errCode, errMsg, e);
	}

	public AppServiceException(Throwable e){
		super(e);
	}

}