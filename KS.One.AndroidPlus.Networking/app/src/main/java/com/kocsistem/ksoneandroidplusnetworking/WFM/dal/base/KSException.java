package com.kocsistem.ksoneandroidplusnetworking.WFM.dal.base;


public class KSException extends Exception
{
	private static final long serialVersionUID = 1L;
	private ErrorType errorType;
	private String friendlyMessage;
	private String exceptionDetail;

	public ErrorType getErrorType()
	{
		return errorType;
	}

	public void setErrorType(ErrorType errorType)
	{
		this.errorType = errorType;
	}

	public String getFriendlyMessage()
	{
		return friendlyMessage;
	}

	public void setFriendlyMessage(String friendlyMessage)
	{
		this.friendlyMessage = friendlyMessage;
	}

	public String getExceptionDetail()
	{
		return exceptionDetail;
	}

	public void setExceptionDetail(String exceptionDetail)
	{
		this.exceptionDetail = exceptionDetail;
	}

	public KSException(String message, Exception ex, ErrorType errorType)
	{
		super(message, ex);
		this.friendlyMessage = message;
		if (ex != null)
			this.exceptionDetail = ex.getClass().getName() + "-" + (ex.getMessage() == null ? "" : ex.getMessage());
		this.errorType = errorType;
		ex.printStackTrace();
	}

	public KSException(String message, ErrorType errorType)
	{
		super(message, null);
		this.friendlyMessage = message;
		this.errorType = errorType;
	}
}
