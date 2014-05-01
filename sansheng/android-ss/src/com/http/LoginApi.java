package com.http;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Paint.Join;

import com.sansheng.model.User;
import com.util.Constance;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-18 上午10:49:36 declare:
 */
public class LoginApi extends CommonApi {

	private static String url = Constance.domain
			+ "?g=appserver&m=index&cmd=login";

	public static HttpCommonResponse Login(User user, String deviceInfo) {
		HttpCommonResponse commonResponse = null;
		Map<String, String> nvs = toNV(user, deviceInfo);
		commonResponse = HttpUtil.post(url, nvs);
		System.out.println(commonResponse);
		return commonResponse;
	}

	public static Map<String, String> toNV(User user, String devicesInfo) {
		Map<String, String> p = new HashMap<String, String>();
		p.put("username", "" + user.getUsername());
		p.put("password", user.getPassword());
		p.put("logintype", "" + user.getLogintype());
		p.put("terminalinfo", devicesInfo);
		p.put("token", user.getToken());
		return p;
	}

	public static User getResponseUser(String jsonStr) {
		JSONObject json;
		if (jsonStr == null) {
			return null;
		}
		User user = new User();
		try {
			json = new JSONObject(jsonStr);
			String retmsg = json.getString("retmsg");
			String code = json.getString("retcode");
			user.setCode(code);
			JSONObject jsonUser = json.getJSONObject("userinfo");
			String userid = jsonUser.getString("userid");
			String name = jsonUser.getString("name");
			String shopId = jsonUser.getString("shopid");
			String shopName = jsonUser.getString("shopname");
			String shopAddess = jsonUser.getString("shopadds");
			String shopbalance = jsonUser.getString("shopbalance");
			String userlevel = jsonUser.getString("userlevel");
			String rpv = jsonUser.getString("rpv");

			user.setShopadds(shopAddess);
			user.setShopbalance(shopbalance);
			user.setUserlevel(userlevel);
			user.setUserId(userid);
			user.setName(name);
			user.setShopId(shopId);
			user.setShopName(shopName);
			user.setRpv(rpv);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
