package com.huawei.hicloud.common;

/**
 * Controller response result.
 * @author dell
 *
 */
public class ResponseResult {
	// response status code
	private Integer statusCode;
	// response message
	private String msg;
	// response data
	private Object data;
	
	
	public ResponseResult(Integer statusCode, String msg, Object data) {
		super();
		this.statusCode = statusCode;
		this.msg = msg;
		this.data = data;
	}

	// Universal
	public static ResponseResult build(Integer statusCode, String msg, Object data) {
		return new ResponseResult(statusCode, msg, data);
	}
	// Response ok.
	public static ResponseResult ok() {
		return new ResponseResult(200, "OK", null);
	}
	public static ResponseResult ok(Object data) {
		return new ResponseResult(200, "OK", data);
	}
	

	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "ResponseResult [statusCode=" + statusCode + ", msg=" + msg + ", data=" + data + "]";
	}
	
}
