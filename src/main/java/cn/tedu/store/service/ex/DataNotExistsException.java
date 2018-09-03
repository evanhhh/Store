package cn.tedu.store.service.ex;

public class DataNotExistsException extends ServiceException{

	private static final long serialVersionUID = 8039551960812568424L;

	public DataNotExistsException() {
		super();
		
	}

	public DataNotExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public DataNotExistsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public DataNotExistsException(String message) {
		super(message);
		
	}

	public DataNotExistsException(Throwable cause) {
		super(cause);
		
	}
	
}
