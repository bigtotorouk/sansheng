package com.http;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.sansheng.model.LocalInfo;

public class SystemService {
	private ViewCommonResponse response = new ViewCommonResponse();;

	public static final int SYS_FEED_BACK = 1001;
	public static final int SYS_VERSION = 1002;
	public static final int SYS_CHECK = 1003;

	public static final int NEWS_AD = 1004;
	public static final int NEWS_BENEFIT = 1005;

	public ViewCommonResponse addFeedBack(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_FEED_BACK, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(response, json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public ViewCommonResponse getVersionk(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_VISION, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(response, json.toString());

			if (json.has("versionurl")) {
				String version = json.getString("versionurl");
				response.setData(version);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public ViewCommonResponse checkUser(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.CHECK_USER, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());

			if (json.has("username")) {
				String username = json.getString("username");
				response.setData(username);
			}
			response = JsonUtil.commonParser(response, json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public ViewCommonResponse getNewsAd(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.NEW_AD, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		List<LocalInfo> localInfos;
		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			localInfos = JsonUtil.json2ObList(httpCommonResponse.getResponse(),
					"clist", LocalInfo.class);
			response.setData(localInfos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public ViewCommonResponse getBenefitAd(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.BENEFIT_AD, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		List<LocalInfo> localInfos;
		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			localInfos = JsonUtil.json2ObList(httpCommonResponse.getResponse(),
					"clist", LocalInfo.class);
			response.setData(localInfos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

}
