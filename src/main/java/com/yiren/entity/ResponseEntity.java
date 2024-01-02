package com.yiren.entity;

import java.io.Serializable;

/**
 * 统一响应对象
 * 
 */
public class ResponseEntity<T> implements Serializable
{

	public static enum StatusInfo
	{

		REQUEST_SUCCESS("200", "OK");
		private final String code;
		private final String message;

		private StatusInfo(String code, String message)
		{
			this.code = code;
			this.message = message;
		}

		public String getCode()
		{
			return code;
		}

		public String getMessage()
		{
			return message;
		}

	}

	// 状态代码
	protected String code = StatusInfo.REQUEST_SUCCESS.getCode();
	// 消息内容
	protected String message = StatusInfo.REQUEST_SUCCESS.getMessage();
	// 消息主体业务对象
	protected T data;

	protected StatusInfo statusInfo = StatusInfo.REQUEST_SUCCESS;

	// 简单构造，返回一个成功信息，不带业务对象
	public ResponseEntity()
	{
		this.setStatusInfo(StatusInfo.REQUEST_SUCCESS);
	}

	// 简单构造，返回执行结果信息，不带业务对象
	public ResponseEntity(String code, String msg)
	{
		this.code = code;
		this.message = msg;
	}

	// 简单构造，返回执行结果信息，带业务对象
	public ResponseEntity(String code, String msg, T data)
	{
		this.code = code;
		this.message = msg;
		this.data = data;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{
		this.data = data;
	}

	public StatusInfo getStatusInfo()
	{
		return statusInfo;
	}

	public void setStatusInfo(StatusInfo statusInfo)
	{
		this.statusInfo = statusInfo;
		this.code = statusInfo.getCode();
		this.message = statusInfo.getMessage();
	}
	
	public void setAll(String code , T data , String message)
	{
		this.code = code;
		this.message = message;
		this.data = data;
	}

}
