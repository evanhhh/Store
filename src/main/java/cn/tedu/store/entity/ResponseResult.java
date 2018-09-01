package cn.tedu.store.entity;

public class ResponseResult<T> {
	public static final int STATE_OK=1;
	public static final int STATE_ERR=0;
	private Integer state;//成功（1）或失败（0）
	private String message;//仅当失败时封装错误信息
	private T data;//数据
	
	public ResponseResult() {
		super();
	}

	public ResponseResult(Integer state) {
		super();
		this.state = state;
	}

	public ResponseResult(Integer state, String message) {
		super();
		this.state = state;
		this.message = message;
	}

	public ResponseResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}
	
	public ResponseResult(Throwable throwable) {
		super();
		this.state = STATE_ERR;
		this.message = throwable.getMessage();
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
