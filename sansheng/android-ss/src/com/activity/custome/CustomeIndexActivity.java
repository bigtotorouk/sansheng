package com.activity.custome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.CommonActivity;
import com.http.BaseRequest;
import com.http.ViewCommonResponse;
import com.http.task.CustomeAsynctask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Contact;
import com.util.ContactUtil;
import com.util.PingYinUtil;
import com.util.PinyinComparator;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.ListViewSearch;
import com.view.LoadingDilog;
import com.view.SearchView;
import com.view.SideBar;

public class CustomeIndexActivity extends CommonActivity implements
		android.view.View.OnClickListener {

	private ListViewSearch lvCustome;
	private static final int MSG_NEW_DATA = 1;
	CustomeAdapter customeAdapter;

	private LoadingDilog lodingDilog;
	private SearchView searchView;
	private static final int resultCode = 0;
	private CommonActivity commonActivity;
	private Intent intent;
	private ListView lvSearch;
	private SearchCustomeAdapter searchAdapter;

	public static boolean need = false;
	public static final String INTENT_NEEDREFERSH = "NEED_REFERSH";
	private View bottomView;
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
		bottomView = (View) findViewById(R.id.Layout_Bottom);
		bottomView.setOnClickListener(this);
		sideBar.setTextView(tvDialog);
		sideBar.setListView(lvCustome);
		lvCustome.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				intent = new Intent(commonActivity, CustomInfoActivity.class);
	 			Contact contact = customeAdapter.getContacts().get(position);
				Bundle bundle = new Bundle();
				bundle.putSerializable("contact", contact);
				intent.putExtras(bundle);
				CustomeIndexActivity.this.setResult(resultCode, intent);
				startActivity(intent);

			}
		});

		lvSearch.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				intent = new Intent(commonActivity, CustomInfoActivity.class);
				Contact contact = searchAdapter.getContacts().get(position);
				Bundle bundle = new Bundle();
				bundle.putSerializable("contact", contact);
				intent.putExtras(bundle);
				startActivity(intent);

			}
		});

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
		headBar.setRightImg(R.drawable.btn_edit_bg);
		headBar.setWidgetClickListener(this);

		update();

	}

	private void update() {
		BaseRequest baseRequest = createRequestWithUserId(CustomeAsynctask.CUSTOME_QUERY);
		new CustomeAsynctask(this).execute(baseRequest);
		ProgressDialogUtil.show(this, "提示", "正在加载数据", true, true);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (need == true) {
			update();
			need = false;
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		need = false;
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		Intent i = getIntent();
		if (i != null) {
			Bundle bundlle = i.getExtras();
			if (bundlle != null) {
				Boolean need = bundlle.getBoolean(INTENT_NEEDREFERSH, false);
				if (need == true) {
					update();
				}
			}
		}
	}

	public void searchWord(String s) {
		lvSearch.setVisibility(View.VISIBLE);
		lvCustome.setVisibility(View.INVISIBLE);
		List<Contact> result = new ArrayList<Contact>();
		List<Contact> contacts = customeAdapter.getContacts();
		if (contacts == null) {
			return;
		}
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

			} else {
				customeAdapter.setContacts(null);
			}
			break;

		case CustomeAsynctask.CUSTOME_SYNC:
			if (viewCommonResponse.getRetcode() == 0) {
				ProgressDialogUtil.close();
				Toast.makeText(this, "同步完成", Toast.LENGTH_SHORT).show();
			}

			break;

		}
	}

	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.Img_Right:
			Intent intent = new Intent(this, CustomeDetailActivity.class);

			Bundle bundle = new Bundle();
			bundle.putInt(CustomeDetailActivity.intentMode, 0);
			intent.putExtras(bundle);
			startActivity(intent);
			break;

		case R.id.Btn_Back:
			finish();
			break;
		case R.id.Layout_Bottom:
			syncLocalDialog();
			break;
		}
	}

	// 同步本地联系人
	public void syncLocalDialog() {
		final AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("同步客户到本地通讯录?");

		builder.setTitle("提示");

		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if (contacts != null) {
					syncLocalContact();
				}
				arg0.dismiss();

			}
		});

		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});
		builder.create().show();
	}

	public void syncLocalContact() {
		// ContactUtil contactUtil = new ContactUtil(commonActivity);
		// List<Contact> syncContacts = contactUtil.getBackUpContact(contacts);
		// System.out.println("to be  insert:" + syncContacts);
		BaseRequest baseRequest = createRequestWithUserId(CustomeAsynctask.CUSTOME_SYNC);
		CustomeAsynctask customeAsynctask = new CustomeAsynctask(this);
		customeAsynctask.setContacts(contacts);
		customeAsynctask.execute(baseRequest);
		ProgressDialogUtil.show(this, "提示", "同步中", true, true);

	}

}
