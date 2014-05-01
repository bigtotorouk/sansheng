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
import com.http.ViewCommonResponse;
import com.sansheng.model.AchList;
import com.sansheng.model.Achivement;
import com.sansheng.model.FuxiaoPool;
import com.sansheng.model.SalePool;

public class FormAsyncTask extends
		AsyncTask<BaseRequest, Void, ViewCommonResponse> {
	private CommonActivity activity;
	private CommonFragment fragment;
	CustomFormService formService = new CustomFormService();

	public FormAsyncTask(CommonActivity activity, CommonFragment fragment) {
		this.activity = activity;
		this.fragment = fragment;
	}

	@Override
	protected ViewCommonResponse doInBackground(BaseRequest... params) {
		ViewCommonResponse viewCommonResponse = null;

		if (params[0] != null) {
			int action = params[0].getAction();
			switch (action) {
			case CustomFormService.FORM_QUERY:
				viewCommonResponse = formService.queryForm(params[0]
						.getParams());
				break;
			case CustomFormService.FORM_SEARCH:
				viewCommonResponse = formService.queryForm(params[0]
						.getParams());
				break;
			case CustomFormService.FORM_DETAIL:
				viewCommonResponse = formService.queryFormDetail(params[0]
						.getParams());
				break;
			case CustomFormService.FORM_COMMENT:
				viewCommonResponse = formService.queryFormComment(params[0]
						.getParams());
				break;
			case CustomFormService.FORM_DELETE:
				viewCommonResponse = formService.deleteFrom(params[0]
						.getParams());
				break;

			case CustomFormService.FORM_QUERY_ADD:
				viewCommonResponse = formService.queryForm(params[0]
						.getParams());
				break;

			case CustomFormService.FORM_Sure:
				viewCommonResponse = formService
						.SureFrom(params[0].getParams());
				break;

			case CustomFormService.FORM_PAY:
				viewCommonResponse = formService.ShoperPay(params[0]
						.getParams());
				break;

			case CustomFormService.FORM_NOTIFY:
				viewCommonResponse = formService.notify(params[0].getParams());
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