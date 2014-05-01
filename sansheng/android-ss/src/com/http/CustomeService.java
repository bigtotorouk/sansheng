package com.http;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.R.interpolator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sansheng.model.Contact;

public class CustomeService {
	private ViewCommonResponse response = new ViewCommonResponse();;

	 
	public ViewCommonResponse addCustome(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_CUSTOME_ADD, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(response, json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public ViewCommonResponse queryCustome(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_CUSTOME_QUERY, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(response, json.toString());
			List<Contact> contacts = new ArrayList<Contact>();

			// String jsonContacts=json.getString("clientlist");

			JSONObject jsonObject;
			try {
				Gson gson = new Gson();
				jsonObject = json.getJSONObject("clientlist");
				// JSONArray jsonarray =
				// json.getJSONArray(jsonObject.toString());
				// JSONArray jsonArray = jsonObject.getJSONArray("");

				for (int i = 0; i < alpha.length; i++) {
					if (jsonObject.has(alpha[i])) {
						String s = jsonObject.getString(alpha[i]);
						Type type = new TypeToken<List<Contact>>() {
						}.getType();
						List<Contact> cs = (ArrayList<Contact>) gson.fromJson(
								s, type);
						contacts.addAll(cs);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// contacts = (ArrayList<Contact>) JsonUtil.json2ObList(
			// json.toString(), "", Contact.class);
			response.setData(contacts);
			System.out.println(contacts);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	static String[] alpha = new String[] { "", "A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z" };

	public ViewCommonResponse deleteCustome(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_CUSTOME_DELETE, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(response, json.toString());
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public ViewCommonResponse queryCustomeInfo(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_CUSTOME_INFO, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(response, json.toString());
			JSONObject jsonContact = json.getJSONObject("clientinfo");
			Contact c = new Contact();
			if (jsonContact.has("name")) {
				c.setName(jsonContact.getString("name"));
			}
			if (jsonContact.has("cardno")) {
				c.setCardNum(jsonContact.getString("cardno"));
			}
			if (jsonContact.has("addtimer")) {
				c.setAddtimer(jsonContact.getString("addtimer"));
			}
			if (jsonContact.has("mobilephone")) {
				c.setMobilephone(jsonContact.getString("mobilephone"));
			}
			if (jsonContact.has("homephone")) {

				String content = jsonContact.getString("homephone");
				String[] phone = content.replace("|", ",").split(",");
				c.setHomephone(phone);
			}
			if (jsonContact.has("email")) {
				c.setEmail(jsonContact.getString("email"));
			}
			if (jsonContact.has("address")) {
				c.setAddress(jsonContact.getString("address"));
			}
			if (jsonContact.has("sex")) {
				c.setSex(jsonContact.getString("sex"));
			}
			if (jsonContact.has("birthday")) {
				c.setBirthday(jsonContact.getString("birthday"));
			}
			if (jsonContact.has("work")) {
				c.setWork(jsonContact.getString("work"));
			}
			if (jsonContact.has("mark")) {
				c.setMark(jsonContact.getString("mark"));
			}
			response.setData(c);
			System.out.println(c);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public ViewCommonResponse editCustome(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.CUSTOME_EDIT_COMMIT, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(response, json.toString());
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

}
