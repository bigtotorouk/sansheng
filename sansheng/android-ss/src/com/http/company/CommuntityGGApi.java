package com.http.company;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.http.BaseNetService;
import com.http.HttpUtilOld;
import com.http.response.CommonResponse;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.Lovenewsadsopen;
import com.sansheng.model.LocalInfo.InfoType;

public class CommuntityGGApi {
	private static String url = BaseNetService.CommunitGG;

	public static List<Lovenewsadsopen> getNewss(int index) {
		List<Lovenewsadsopen> Lovenewsadsopen = null;
		List<NameValuePair> nvs = new ArrayList<NameValuePair>();
		nvs.add(new BasicNameValuePair("pageno", "" + index));
		CommonResponse response = HttpUtilOld.postReq(url, nvs);
		Lovenewsadsopen = toLocalInfo(response.getResponse());
		return Lovenewsadsopen;
	}

	public static List<Lovenewsadsopen> toLocalInfo(String body) {
		List<Lovenewsadsopen> lovenewsadsopen = null;
		try {
			lovenewsadsopen = new ArrayList<Lovenewsadsopen>();
			JSONObject jsonObject = new JSONObject(body);
			String retCode = jsonObject.getString("retcode");
			String retMsg = jsonObject.getString("retmsg");
			String list = jsonObject.getString("clist");
			JSONArray jList = new JSONArray(list);
			for (int i = 0; i < jList.length(); i++) {
				Lovenewsadsopen lovenewsads = new Lovenewsadsopen();
				JSONObject jObj = jList.getJSONObject(i);
				String type = jObj.getString("type");
				String title = jObj.getString("title");
				String img = jObj.getString("img");
				String info = jObj.getString("info");

				lovenewsads.setImg(img);
				lovenewsads.setInfo(info);
				lovenewsads.setTitle(title);
				lovenewsads.setType(type);
				lovenewsadsopen.add(lovenewsads);
				Log.e("debug", "index:" + i + "  " + "localInfo:" + lovenewsads);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lovenewsadsopen;
	}
}
