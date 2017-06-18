package cn.haoxy.restful;

import java.io.Serializable;

public class RestResponse<T> implements Serializable {
	
	private static final long serialVersionUID = -8657317762031936050L;

	private String status;

	private int code;

	private T body;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

}
