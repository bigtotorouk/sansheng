package com.http.task;

import java.util.List;
import java.util.Map;

import android.os.AsyncTask;

import com.activity.CommonActivity;
import com.http.BaseRequest;
import com.http.CustomeService;
import com.http.RemindService;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.sansheng.model.Contact;
import com.util.ContactUtil;

public class CustomeAsynctask extends
		AsyncTask<BaseRequest, Void, ViewCommonResponse> {
	private CommonActivity activity;
	CustomeService customeService = new CustomeService();

	private List<Contact> contacts;

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public CustomeAsynctask(CommonActivity activity) {
		this.activity = activity;
	}

	public final static int CUSTOME_ADD = 0;
	public final static int CUSTOME_QUERY = 1;
	public final static int CUSTOME_EDIT = 2;
	public final static int CUSTOME_DELETE = 3;
	public final static int CUSTOME_INFO = 4;
	public final static int CUSTOME_SYNC = 5;

	@Override
	protected ViewCommonResponse doInBackground(BaseRequest... params) {
		ViewCommonResponse viewCommonResponse = null;

		if (params[0] != null) {
			int action = params[0].getAction();
			switch (action) {
			case CUSTOME_ADD:
				viewCommonResponse = customeService.addCustome(params[0]
						.getParams());
				break;
			case CUSTOME_QUERY:
				viewCommonResponse = customeService.queryCustome(params[0]
						.getParams());
				break;
			case CUSTOME_EDIT:
				viewCommonResponse = customeService.editCustome(params[0]
						.getParams());
				break;
			case CUSTOME_DELETE:
				viewCommonResponse = customeService.deleteCustome(params[0]
						.getParams());
				break;
			case CUSTOME_INFO:
				viewCommonResponse = customeService.queryCustomeInfo(params[0]
						.getParams());
				break;  
			case CUSTOME_SYNC:
				ContactUtil contactUtil = new ContactUtil(activity);
				// List<Contact> syncContacts = contactUtil
				// .getBackUpContact(contacts);
				// System.out.println("to be  insert:" + syncContacts);

				contactUtil.inserList(contacts); 
				viewCommonResponse = new ViewCommonResponse();
				viewCommonResponse.setHttpCode(200);
				viewCommonResponse.setMsgCode(0);
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