package com.http.company;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.R.integer;
import android.util.Log;

import com.http.BaseNetService;
import com.http.HttpCommonResponse;
import com.http.HttpUtil;
import com.http.HttpUtilOld;
import com.http.response.CommonResponse;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.util.Constance;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-18 下午9:34:01 declare:促销接口
 */
public class SaleApi {

	private static String url = BaseNetService.Promotion;

	public static List<LocalInfo> getSales(int index) {
		List<LocalInfo> locaInfos = new ArrayList<LocalInfo>();
		List<NameValuePair> nvs = new ArrayList<NameValuePair>();
		boolean hasnext = true;
		int i = 0;
		while (hasnext) {
			nvs.add(new BasicNameValuePair("pageno", "" + i));
			url += "&pageno=" + i;
			i++;
			HttpCommonResponse response = HttpUtil.get(url, null);
			List<LocalInfo> infos = toLocalInfo(response.getResponse());
			if (infos.size() != 0) {
				locaInfos.addAll(infos);
			} else {
				hasnext = false;
			}
		}
		return locaInfos;
	}

	public static List<LocalInfo> toLocalInfo(String body) {
		List<LocalInfo> localInfos = null;
		try {
			localInfos = new ArrayList<LocalInfo>();
			JSONObject jsonObject = new JSONObject(body);
			String retCode = jsonObject.getString("retcode");
			String retMsg = jsonObject.getString("retmsg");
			String list = jsonObject.getString("list");
			JSONArray jList = new JSONArray(list);
			for (int i = 0; i < jList.length(); i++) {
				LocalInfo localInfo = new LocalInfo();
				JSONObject jObj = jList.getJSONObject(i);
				String newId = jObj.getString("news_id");
				String newsTitle = jObj.getString("news_title");
				String newsInfo = jObj.getString("news_info");
				String newDate = jObj.getString("news_date");
				String newStatus = jObj.getString("news_status");
				String newsUrl = jObj.getString("Url");

				String sImgshow = jObj.getString("news_simgshow");
				String bImgshow = jObj.getString("news_bimgshow");

				localInfo.setNews_bimgshow(bImgshow);
				localInfo.setNews_simgshow(sImgshow);

				localInfo.setType(InfoType.sales);
				localInfo.setInfoId(newId);
				localInfo.setTitle(newsTitle);
				localInfo.setContent(newsInfo);
				localInfo.setData(newDate);
				localInfo.setUrl(newsUrl);
				localInfo.setStatus(newStatus);
				localInfos.add(localInfo);
				Log.e("debug", "index:" + i + "  " + "localInfo:" + localInfo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return localInfos;
	}

}
