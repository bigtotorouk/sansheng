package com.http;

import org.json.JSONObject;

import com.http.response.CommonResponse;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-24 下午6:38:30 declare:
 */
public class CommonApi {

	public static CommonResponse getResponse(String content) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			JSONObject jObj = new JSONObject(content);
			int retcode = jObj.getInt("retcode");
			commonResponse.setStateCode(retcode);
			commonResponse.setResponse(content);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return commonResponse;
	}
}
