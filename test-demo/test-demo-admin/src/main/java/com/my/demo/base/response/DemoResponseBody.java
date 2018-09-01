package com.my.demo.base.response;

public class DemoResponseBody {

	private boolean success;
	private String exceptionMsg;
	private String exceptionStack;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getExceptionMsg() {
		return exceptionMsg;
	}
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	public String getExceptionStack() {
		return exceptionStack;
	}
	public void setExceptionStack(String exceptionStack) {
		this.exceptionStack = exceptionStack;
	}
	
}
