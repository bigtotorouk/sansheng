package com.http.task;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

import com.activity.CommonActivity;
import com.activity.schedule.CommonFragment;
import com.http.AchivementService;
import com.http.BaseRequest;
import com.http.Bill;
import com.http.RemindService;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.sansheng.model.AchList;
import com.sansheng.model.Achivement;
import com.sansheng.model.FuxiaoPool;
import com.sansheng.model.SalePool;

public class AchivementAsyncTask extends
		AsyncTask<BaseRequest, Void, ViewCommonResponse> {
	private CommonActivity activity;
	private CommonFragment fragment;
	AchivementService achivementService = new AchivementService();

	public AchivementAsyncTask(CommonActivity activity, CommonFragment fragment) {
		this.activity = activity;
		this.fragment = fragment;
	}

	@Override
	protected ViewCommonResponse doInBackground(BaseRequest... params) {
		ViewCommonResponse viewCommonResponse = null;

		if (params[0] != null) {
			int action = params[0].getAction();
			switch (action) {
			case AchivementService.ACHI_LIST:
				viewCommonResponse = achivementService
						.queryAchivmentList(params[0].getParams());
				break;
			case AchivementService.ACHI_MY:
				viewCommonResponse = achivementService.queryMyAchivment(
						new ViewCommonResponse(), params[0].getParams());
				break;
			case AchivementService.ACHI_SALE:
				viewCommonResponse = achivementService.querySalePool(
						new ViewCommonResponse(), params[0].getParams());
				break;
			case AchivementService.ACHI_FUXIAO:
				viewCommonResponse = achivementService.queryHuxiaoPool(
						new ViewCommonResponse(), params[0].getParams());
				break;
			case AchivementService.ACHI_BILL:

				viewCommonResponse = achivementService.querySalePool(
						new ViewCommonResponse(), params[0].getParams());

				ViewCommonResponse v2 = viewCommonResponse = achivementService
						.queryMyAchivment(new ViewCommonResponse(),
								params[0].getParams());
				ViewCommonResponse v3 = achivementService.querySalePool(
						new ViewCommonResponse(), params[0].getParams());
				ViewCommonResponse v4 = achivementService.queryHuxiaoPool(
						new ViewCommonResponse(), params[0].getParams());

				Achivement achivement = (Achivement) v2.getData();
				SalePool salePool = (SalePool) v3.getData();
				FuxiaoPool fuxiaoPool = (FuxiaoPool) v4.getData();
				Bill bill = new Bill();
				bill.setAchivement(achivement);
				bill.setFuxiaoPool(fuxiaoPool);
				bill.setSalePool(salePool);
				viewCommonResponse.setData(bill);
				viewCommonResponse.setHttpCode(200);
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