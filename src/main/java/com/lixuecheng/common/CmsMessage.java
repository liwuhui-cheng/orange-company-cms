package com.lixuecheng.common;

import java.io.Serializable;

public class CmsMessage implements Serializable{

	/**
	 * 消息统一处理
	 */
	private static final long serialVersionUID = 1470056768685338254L;
	
	private   int  code;   //1  成功   2 失败
	
	private   String  error;  //错误原因
	
	private   Object   data;  //成功的情况返回的数据

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CmsMessage [code=" + code + ", error=" + error + ", data=" + data + "]";
	}

	public CmsMessage(int code, String error, Object data) {
		super();
		this.code = code;
		this.error = error;
		this.data = data;
	}
	
	
	
}
