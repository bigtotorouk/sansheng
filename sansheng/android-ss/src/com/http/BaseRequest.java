package com.http;

import java.util.HashMap;
import java.util.Map;

public class BaseRequest {
	private int action;
	private Map<String,String> params = new HashMap<String, String>();
	
	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	public void setParam(String key,String value){
		params.put(key, value);
	}
	

	public void add(String name, String value) {
		params.put(name, value);
	}

	public String getParam(String key){
		return params.get(key);
	}

	@Override
	public String toString() {
		return "BaseRequest [action=" + action + ", params=" + params + "]";
	}
	
	
}
