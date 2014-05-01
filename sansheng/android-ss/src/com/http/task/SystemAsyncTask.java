package com.http.task;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

import com.activity.CommonActivity;
import com.activity.schedule.CommonFragment;
import com.http.AchivementService;
import com.http.BaseRequest;
import com.http.Bill;
import com.http.CustomFormService;
import com.http.CustomeService;
import com.http.RemindService;
import com.http.ShopService;
import com.http.SystemService;
import com.http.ViewCommonResponse;
import com.sansheng.model.AchList;
import com.sansheng.model.Achivement;
import com.sansheng.model.FuxiaoPool;
import com.sansheng.model.SalePool;

public class SystemAsyncTask extends
		AsyncTask<BaseRequest, Void, ViewCommonResponse> {
	private CommonActivity activity;
	private CommonFragment fragment;
	SystemService systemService = new SystemService();

	public SystemAsyncTask(CommonActivity activity, CommonFragment fragment) {
		this.activity = activity;
		this.fragment = fragment;
	}
 
	@Override
	protected ViewCommonResponse doInBackground(BaseRequest... params) {
		ViewCommonResponse viewCommonResponse = null;

		if (params[0] != null) {
			int action = params[0].getAction();
			switch (action) {
			case SystemService.SYS_FEED_BACK:
				viewCommonResponse = systemService.addFeedBack(params[0]
						.getParams());
				break;
			case SystemService.SYS_VERSION:
				viewCommonResponse = systemService.getVersionk(params[0]
						.getParams());
				break;
			case SystemService.SYS_CHECK:
				viewCommonResponse = systemService.checkUser(params[0]
						.getParams());
				break;

			case SystemService.NEWS_AD:
				viewCommonResponse = systemService.getNewsAd(params[0]
						.getParams());
				break;
			case SystemService.NEWS_BENEFIT:
				viewCommonResponse = systemService.getBenefitAd(params[0]
						.getParams());
				break;

			}

			viewCommonResponse.setAction(action);
		}
		return viewCommonResponse;
	}

	@Override
	protected void onPostExecute(ViewCommonResponse result) {
		if (activity != null) {
			activity.refresh(result);
		}
		if (fragment != null) {
			fragment.refresh(result);
		}
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

}