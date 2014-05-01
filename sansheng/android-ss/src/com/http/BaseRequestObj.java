package com.http;

import java.util.HashMap;
import java.util.Map;

public class BaseRequestObj {
	private int action;
	private Map<String, Object> params = new HashMap<String, Object>();

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void setParam(String key, String value) {
		params.put(key, value);
	}

	public void add(String name, Object value) {
		params.put(name, value);
	}

	public String getParam(Object key) {
		return (String) params.get(key);
	}

	@Override
	public String toString() {
		return "BaseRequest [action=" + action + ", params=" + params + "]";
	}

}
