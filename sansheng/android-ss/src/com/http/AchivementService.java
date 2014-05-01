package com.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.R.interpolator;

import com.sansheng.model.AchList;
import com.sansheng.model.Achivement;
import com.sansheng.model.FuxiaoPool;
import com.sansheng.model.Remind;
import com.sansheng.model.SalePool;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-20 上午11:01:23 declare:
 */
public class AchivementService {

	public static final int ACHI_LIST = 1001;
	public static final int ACHI_MY = 1002;
	public static final int ACHI_SALE = 1003;
	public static final int ACHI_FUXIAO = 1004;
	public static final int ACHI_BILL = 1005;

	private ViewCommonResponse response = new ViewCommonResponse();;

	/**
	 * 2.6.1提价提醒接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse queryAchivmentList(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_ACH_LIST, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			List<AchList> achLists = new ArrayList<AchList>();
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(response, json.toString());
			achLists = JsonUtil.json2ObList(json.toString(), "list",
					AchList.class);
			System.out.println(achLists);
			response.setData(achLists);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * 2.6.1提价提醒接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse queryMyAchivment(ViewCommonResponse  response,Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_ACH_MY, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			Achivement achivement; 
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			JsonUtil.json2Object(response, json.toString(), Achivement.class);
			achivement = (Achivement) response.getData();
			System.out.println(achivement);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public ViewCommonResponse querySalePool(ViewCommonResponse  response,Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_ACH_SALE_POOL, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			SalePool salePool;
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			JsonUtil.json2Object(response, json.toString(), SalePool.class);
			salePool = (SalePool) response.getData();
			System.out.println(salePool);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public ViewCommonResponse queryHuxiaoPool(ViewCommonResponse  response,Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_ACH_FUXIAO_POOL, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			FuxiaoPool fuxiaoPool;
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			JsonUtil.json2Object(response, json.toString(), FuxiaoPool.class);
			fuxiaoPool = (FuxiaoPool) response.getData();
			System.out.println(fuxiaoPool);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public ViewCommonResponse queryAllRemind(Map<String, String> params) {

		List<Remind> reminds = new ArrayList<Remind>();
		boolean hasnext = true;
		int pageNum = 0;
		while (hasnext) {
			params.put("pageno", "" + pageNum);
			HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
					BaseNetService.URL_REMINDS_QUERY, params);
			response.setHttpCode(httpCommonResponse.getStateCode());

			pageNum++;
			try {

				JSONObject json = new JSONObject(
						httpCommonResponse.getResponse());
				response = JsonUtil.commonParser(response, json.toString());
				if (response.getHttpCode() == 200 && response.getMsgCode() == 0) {
					List<Remind> remindResult = (ArrayList<Remind>) JsonUtil
							.json2ObList(json.toString(), "list", Remind.class);
					reminds.addAll(remindResult);

					System.out.println(reminds);
				} else {
					hasnext = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		response.setData(reminds);
		return response;
	}

	/**
	 * 2.6.3 删除提醒接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse deleteRemind(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_REMINDS_DELETE, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

}
