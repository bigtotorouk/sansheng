package com.http.task;

import android.os.AsyncTask;

import com.activity.CommonActivity;
import com.http.BaseRequest;
import com.http.RemindService;
import com.http.ShopService;
import com.http.ViewCommonResponse;

public class RemindAsyncTask extends
		AsyncTask<BaseRequest, Void, ViewCommonResponse> {
	private CommonActivity activity;
	RemindService remindService = new RemindService();

	public RemindAsyncTask(CommonActivity activity) {
		this.activity = activity;
	}

	@Override
	protected ViewCommonResponse doInBackground(BaseRequest... params) {
		ViewCommonResponse viewCommonResponse = null;

		if (params[0] != null) {
			int action = params[0].getAction();
			switch (action) {
			case RemindService.REMIND_ADD:
				viewCommonResponse = remindService.addRemind(params[0]
						.getParams());
				break;
			case RemindService.REMIND_DELETE:
				viewCommonResponse = remindService.deleteRemind(params[0]
						.getParams());
				break;
			case RemindService.REMIND_LIST:
				viewCommonResponse = remindService.queryRemind(params[0]
						.getParams());
				break;

			}

			viewCommonResponse.setAction(action);
		}
		return viewCommonResponse;
	}

	@Override
	protected void onPostExecute(ViewCommonResponse result) {
		activity.refresh(result);
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

}