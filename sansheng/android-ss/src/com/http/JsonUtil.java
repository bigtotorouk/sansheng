package com.http;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

public class JsonUtil {

	/**
	 * 
	 * @param json
	 *            the json data
	 * @param name
	 *            the key name of the jsonArray
	 * @param c
	 *            class name
	 * @return
	 */
	public static <T> List<T> json2ObList(String json, String name, Class<T> c) {
		JSONObject jsonObject;
		List<T> obList = new ArrayList<T>();
		try {
			jsonObject = new JSONObject(json);
			if (jsonObject.has(name) == true) {
				JSONArray jsonArray = jsonObject.getJSONArray(name);
				Gson gson = new Gson();

				for (int i = 0; i < jsonArray.length(); i++) {
					obList.add(gson.fromJson(jsonArray.getString(i), c));
				}
				return obList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 单个对象解析
	 * 
	 * @param json
	 * @param className
	 * @return
	 */
	public static ViewCommonResponse json2Object(String json, Class className,
			String jsonName) {
		ViewCommonResponse response = commonParser(json);
		try {
			JSONObject jsonObject = new JSONObject(json);
			String dataObj = jsonObject.getString(jsonName);
			if (dataObj == null || "".equals(dataObj) || "null".equals(dataObj)) {
				return response;
			}
			// System.out.println(jsonObject.getString("data"));
			// System.out.println(jsonObject.getString("data") == null);
			// System.out.println(jsonObject.getString("data").equals(""));
			jsonObject = jsonObject.getJSONObject(jsonName);
			Gson gson = new Gson();
			Object obj = gson.fromJson(jsonObject.toString(), className);
			response.setData(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * 单个对象解析
	 * 
	 * @param json
	 * @param className
	 * @return
	 */
	public static ViewCommonResponse json2Object(ViewCommonResponse response,
			String json, Class className) {
		try {
			JSONObject jsonObject = new JSONObject(json);
			if (jsonObject == null || "".equals(jsonObject)
					|| "null".equals(jsonObject)) {
				return response;
			}
			// System.out.println(jsonObject.getString("data"));
			// System.out.println(jsonObject.getString("data") == null);
			// System.out.println(jsonObject.getString("data").equals(""));
			Gson gson = new Gson();
			Object obj = gson.fromJson(jsonObject.toString(), className);
			response.setData(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public static ViewCommonResponse commonParser(String json) {
		ViewCommonResponse response = new ViewCommonResponse();
		try {
			JSONObject jsonObject = new JSONObject(json);
			int retcode = jsonObject.getInt("retcode");
			String retmsg = jsonObject.getString("retmsg");
			if (jsonObject.has("page")) {
				String pageObj = jsonObject.getString("page");
				if (pageObj != null && !"".equals(pageObj)
						&& !"null".equals(pageObj)) {
					Gson gson = new Gson();
					Page page = gson.fromJson(pageObj.toString(), Page.class);
					response.setPage(page);
				}
			}
			response.setMessage(retmsg);
			response.setMsgCode(retcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 单个对象解析
	 * 
	 * @param json
	 * @param className
	 * @return
	 */
	public static ViewCommonResponse json2Object(ViewCommonResponse response,
			String json, Class className, String jsonName) {
		response = commonParser(response, json);
		try {
			JSONObject jsonObject = new JSONObject(json);
			String dataObj = jsonObject.getString(jsonName);
			if (dataObj == null || "".equals(dataObj) || "null".equals(dataObj)) {
				return response;
			}
			// System.out.println(jsonObject.getString("data"));
			// System.out.println(jsonObject.getString("data") == null);
			// System.out.println(jsonObject.getString("data").equals(""));
			jsonObject = jsonObject.getJSONObject(jsonName);
			Gson gson = new Gson();
			Object obj = gson.fromJson(jsonObject.toString(), className);
			response.setData(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public static ViewCommonResponse commonParser(ViewCommonResponse response,
			String json) {
		try {
			JSONObject jsonObject = new JSONObject(json);
			int msgCode = jsonObject.getInt("retcode");
			String message = jsonObject.getString("retmsg");
			if (jsonObject.has("page")) {
				String pageObj = jsonObject.getString("page");
				if (pageObj != null && !"".equals(pageObj)
						&& !"null".equals(pageObj)) {
					Gson gson = new Gson();
					Page page = gson.fromJson(pageObj.toString(), Page.class);
					response.setPage(page);
				}
			}
			response.setRetcode(msgCode);
			response.setRetmsg(message);
			response.setMessage(message);
			response.setMsgCode(msgCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
