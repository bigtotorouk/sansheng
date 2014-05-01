package com.activity.schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.custome.CustomInfoActivity;
import com.activity.custome.CustomeAdapter;
import com.activity.custome.SearchCustomeAdapter;
import com.http.BaseRequest;
import com.http.ViewCommonResponse;
import com.http.task.CustomeAsynctask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Contact;
import com.util.PingYinUtil;
import com.util.PinyinComparator;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.ListViewSearch;
import com.view.LoadingDilog;
import com.view.SearchView;
import com.view.SideBar;

public class SelectCustomeActivity extends CommonActivity implements
		android.view.View.OnClickListener {

	private ListViewSearch lvCustome;
	private static final int MSG_NEW_DATA = 1;
	CustomeAdapter customeAdapter;
	private UIHandler uiHandler;
	private LoadingDilog lodingDilog;
	private SearchView searchView;
	private static final int resultCode = 0;
	private CommonActivity commonActivity;
	private Intent intent;

	private ListView lvSearch;
	private SearchCustomeAdapter searchAdapter;
	List<Contact> contacts;

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_select_custome);
		initWidget();
	}

	public void initWidget() {
		commonActivity = this;

		lodingDilog = new LoadingDilog(this);

		uiHandler = new UIHandler();
		lvCustome = (com.view.ListViewSearch) findViewById(R.id.ListView_Custome);
		searchView = (com.view.SearchView) findViewById(R.id.SearchView);

		lvSearch = (ListView) findViewById(R.id.Lv_Search);
		searchAdapter = new SearchCustomeAdapter(this);
		lvSearch.setAdapter(searchAdapter);
		customeAdapter = new CustomeAdapter(this);

		lvCustome.setAdapter(customeAdapter);
		intent = getIntent();

		TextView tvDialog = (TextView) findViewById(R.id.alphaText);
		SideBar sideBar = (SideBar) findViewById(R.id.sideBar);
		sideBar.setTextView(tvDialog);
		sideBar.setListView(lvCustome);
		lvCustome.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				Contact contact = customeAdapter.getContacts().get(position);
				Bundle bundle = new Bundle();
				bundle.putSerializable("contact", contact);
				intent.putExtras(bundle);
				SelectCustomeActivity.this.setResult(resultCode, intent);
				SelectCustomeActivity.this.finish();
			}
		});

		lvSearch.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				Contact contact = searchAdapter.getContacts().get(position);
				Bundle bundle = new Bundle();
				bundle.putSerializable("contact", contact);
				intent.putExtras(bundle);
				SelectCustomeActivity.this.setResult(resultCode, intent);
				SelectCustomeActivity.this.finish();
			}
		});
		// new Thread() {
		// public void run() {
		//
		// Message msg = new Message();
		// msg.what = MSG_NEW_DATA;
		// List<Contact> contacts = getContacts();
		// msg.obj = contacts;
		// uiHandler.sendMessage(msg);
		// };
		// }.start();

		searchView.getEtSearch().addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				Log.e("text", "change" + s);
				// BaseRequest baseRequest =
				// createRequest(ShopService.PRODUCT_WORD_SEARCH);
				// baseRequest.add("keysword", s.toString());
				// new ShopAsyncTask(activity).execute(baseRequest);
				if (!s.toString().equals("")) {
					searchWord(s.toString());
				} else {
					showCustomList();
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("客户管理");
		headBar.setRightType(BtnType.image);
		headBar.setRightImg(R.drawable.address_edit);
		headBar.setWidgetClickListener(this);

		update();

	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		switch (action) {
		case CustomeAsynctask.CUSTOME_QUERY:
			ProgressDialogUtil.close();
			if (viewCommonResponse.getRetcode() == 0) {
				contacts = (List<Contact>) viewCommonResponse.getData();

				for (Contact contact : contacts) {
					String py = contact.getName();
					py = PingYinUtil.getPingYin(py);
					contact.setPingying(py);
				}

				Collections.sort(contacts, new PinyinComparator());

				customeAdapter.setContacts(contacts);

				// lodingDilog.dismiss();
				// List<Contact> contacts = (List<Contact>) msg.obj;
				// customeAdapter.setContacts(contacts);
			} else {
				// showToast("");
			}
			break;

		}
	}

	private void update() {
		BaseRequest baseRequest = createRequestWithUserId(CustomeAsynctask.CUSTOME_QUERY);
		new CustomeAsynctask(this).execute(baseRequest);
		ProgressDialogUtil.show(this, "提示", "正在加载数据", true, true);
	}

	public void searchWord(String s) {
		lvSearch.setVisibility(View.VISIBLE);
		lvCustome.setVisibility(View.INVISIBLE);
		List<Contact> result = new ArrayList<Contact>();
		List<Contact> contacts = customeAdapter.getContacts();
		for (int i = 0; i < contacts.size(); i++) {
			Contact c = contacts.get(i);
			if (c.getPingying().contains(s) || c.getName().contains(s)) {
				result.add(c);
			}
		}
		searchAdapter.setContacts(result);
		searchAdapter.notifyDataSetChanged();
		// lvSearch.setAdapter(result);

	}

	public void showCustomList() {
		lvSearch.setVisibility(View.INVISIBLE);
		lvCustome.setVisibility(View.VISIBLE);

	}

	class UIHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_NEW_DATA:
				lodingDilog.dismiss();

				contacts = (List<Contact>) msg.obj;
				customeAdapter.setContacts(contacts);
				break;

			default:
				break;
			}

		}
	}

	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.Img_Right:
			Intent intent = new Intent(this, CustomInfoActivity.class);
			startActivity(intent);
			break;

		case R.id.Btn_Back:
			finish();
			break;
		}
	}

}
