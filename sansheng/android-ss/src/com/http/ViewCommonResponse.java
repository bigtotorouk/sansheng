package com.http;

/**
 * 统一数据返回包装类
 * 
 * @author binshenchen
 * 
 */
public class ViewCommonResponse {
	private int action;
	private int httpCode;
	private int msgCode;
	private Object data;
	private Page page;
	private String message;
	private int retcode;
	private String retmsg;

	public ViewCommonResponse() {

	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public int getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(int msgCode) {
		this.msgCode = msgCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRetcode() {
		return retcode;
	}

	public void setRetcode(int retcode) {
		this.retcode = retcode;
	}

	public String getRetmsg() {
		return retmsg;
	}

	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}

	@Override
	public String toString() {
		return "ViewCommonResponse [action=" + action + ", httpCode="
				+ httpCode + ", msgCode=" + msgCode + ", data=" + data
				+ ", page=" + page + ", message=" + message + ", retcode="
				+ retcode + ", retmsg=" + retmsg + "]";
	}
	

}
