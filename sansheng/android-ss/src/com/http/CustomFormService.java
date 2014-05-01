package com.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.sansheng.model.CustomForm;
import com.sansheng.model.FormDetail;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-20 上午11:01:23 declare:
 * 
 * 
 *          报单接口
 */
public class CustomFormService {

	private ViewCommonResponse response = new ViewCommonResponse();;

	// 报单 查询
	public final static int FORM_QUERY = 1001;
	public final static int FORM_SEARCH = 1011;

	public final static int FORM_QUERY_ADD = 1008;
	// 报单详情
	public final static int FORM_DETAIL = 1002;
	// 评论接口
	public final static int FORM_COMMENT = 1003;
	// 报单删除接口
	public final static int FORM_DELETE = 1004;
	// 确认提货接口
	public final static int FORM_Sure = 1005;

	// 支付
	public final static int FORM_PAY = 1006;

	// 提醒
	public final static int FORM_NOTIFY = 1007;

	/**
	 * 2.5.1、 工作室查询接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse queryForm(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_FORM_QUERY, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(response, json.toString());
			List<CustomForm> customForms = new ArrayList<CustomForm>();
			customForms = (ArrayList<CustomForm>) JsonUtil.json2ObList(
					json.toString(), "querylist", CustomForm.class);
			response.setData(customForms);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * 2.5.2、 工作室详情查询接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse queryFormDetail(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_FORM_DETAIL, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());

			response = JsonUtil.json2Object(response, json.toString(),
					FormDetail.class, "detailinfo");
			FormDetail customForm = (FormDetail) response.getData();
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * 2.5.3、产品评价接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse queryFormComment(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_FROM_COMMENT, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(response, json.toString());

			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	// 2.5.4、报单删除接口
	public ViewCommonResponse deleteFrom(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_FROM_DELETE, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(response, json.toString());

			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	// 2.5.5、提货确认接口
	public ViewCommonResponse SureFrom(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_FROM_SURE, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(response, json.toString());

			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	// 店长支付
	public ViewCommonResponse ShoperPay(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_SHOPER_PAY, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			String s = httpCommonResponse.getResponse();
			JSONObject json = new JSONObject(s);
			response = JsonUtil.commonParser(response, json.toString());

			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	// 提醒支付
	public ViewCommonResponse notify(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_NOTIFY, params);
		response.setHttpCode(httpCommonResponse.getStateCode());
		try {
			String s = httpCommonResponse.getResponse();
			JSONObject json = new JSONObject(s);
			response = JsonUtil.commonParser(response, json.toString());

			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
}
