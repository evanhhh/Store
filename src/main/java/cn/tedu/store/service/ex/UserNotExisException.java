package cn.tedu.store.service.ex;

public class UserNotExisException extends ServiceException {

	private static final long serialVersionUID = -849102219774161502L;

	public UserNotExisException() {
		super();
	}

	public UserNotExisException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserNotExisException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotExisException(String message) {
		super(message);
	}

	public UserNotExisException(Throwable cause) {
		super(cause);
	}
	
}
