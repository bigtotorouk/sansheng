package com.activity.balance.payment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.balance.BalanceActivity;
import com.activity.balance.unpayment.unPaymentSearchAdapter;
import com.activity.schedule.CommonFragment;
import com.http.AchivementService;
import com.http.BaseRequest;
import com.http.CustomFormService;
import com.http.ViewCommonResponse;
import com.http.task.FormAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.CustomForm;
import com.sansheng.model.User;
import com.util.ProgressDialogUtil;
import com.view.SearchView;

public class PaymentFragment extends CommonFragment implements OnClickListener,
		OnScrollListener {
	protected View view;
	protected LayoutInflater layoutInflater;
	protected List<CustomForm> balance;
	private CommonActivity activity;
	private PaymentAdapter balanceAdapter;
	ListView lvBalance;

	protected long currentPage = 0;
	protected int pageRecords = 10;
	protected int totalPages = 1;

	private com.view.SearchView searchView;
	private PaymentAdapter searchAdapter;
	User user;
	private boolean canUpdate = false;
	public static boolean needUpdate;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.activity = (CommonActivity) getActivity();
		view = (View) inflater.inflate(R.layout.activity_payment_list, null);
		layoutInflater = inflater;
		lvBalance = (ListView) view.findViewById(R.id.Lv_Balance_List);

		balance = new ArrayList<CustomForm>();
		User u = activity.getUser();
		if (u.getLogintype() == 1) {
			balanceAdapter = new PaymentAdapter(activity, this, true);
		} else {
			balanceAdapter = new PaymentAdapter(activity, this, false);
		}
		// CommonActivity comActivity = (CommonActivity) getActivity();
		// BalanceAdapter brandAdapter = new BalanceAdapter(comActivity);
		// brandAdapter.setBalance(balance);
		// lvBalancetype0.setAdapter(brandAdapter);

		lvBalance.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.e("debug", "click");
			}
		});

		searchView = (SearchView) view.findViewById(R.id.SearchView);
		searchView.getBtnSearchView().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String content = searchView.getContent();
				if (!content.equals("")) {
					search(content);
				}
			}
		});
		searchView.getEtSearch().setHint("搜索");
		searchView.getEtSearch().addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (searchView.getContent().equals("")) {
					lvBalance.setAdapter(balanceAdapter);
					balanceAdapter.notifyDataSetChanged();
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

		searchView.getEtSearch().setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				Log.e("debug", "key" + keyCode);
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					String content = searchView.getContent();
					if (!content.equals("")) {
						search(content);
					}
					searchView.getEtSearch().setText(content);
				}
				return false;
			}
		});
		lvBalance.setOnScrollListener(this);
		initData();
		lvBalance.setAdapter(balanceAdapter);
		return view;
	}

	public void search(String sokeys) {

		BaseRequest requert = activity
				.createRequestWithUserId(CustomFormService.FORM_SEARCH);// action名称

		requert.add("querytype", "2");
		if (user != null) {
			requert.add("shopid", user.getShopId());
		}
		requert.add("pageno", "0");
		requert.add("sokeys", sokeys);
		new FormAsyncTask(null, this).execute(requert);
		ProgressDialogUtil.show(activity, "提示", "正在搜索", true, true);
	}

	/**
	 * 初始化数据
	 * 
	 * @param achList
	 */
	public void initData() {
		BaseRequest requert = activity
				.createRequestWithUserId(CustomFormService.FORM_QUERY);// action名称
		requert.add("querytype", "2");

		User user = activity.getUser();
		if (user != null) {
			requert.add("shopid", user.getShopId());
		}
		requert.add("pageno", "0");

		new FormAsyncTask(null, this).execute(requert);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (needUpdate == true) {
			needUpdate = false;
			initData();
		}
	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();

		if (viewCommonResponse.getHttpCode() != 200)
			return;
		switch (action) {
		case CustomFormService.FORM_QUERY:
			BalanceActivity.finish++;
			Log.e("finish", "finish payment" + BalanceActivity.finish);
			if (BalanceActivity.finish >= BalanceActivity.finishCount) {
				ProgressDialogUtil.close();
			}
			balance = (List<CustomForm>) viewCommonResponse.getData();
			if (balance != null) {
				balanceAdapter.setBalance(balance);
				balanceAdapter.notifyDataSetChanged();
				canUpdate = true;
			} else {
			}
			break;

		case CustomFormService.FORM_Sure:
			ProgressDialogUtil.close();
			if (viewCommonResponse.getMsgCode() == 0) {
				balanceAdapter.remove();
				balanceAdapter.notifyDataSetChanged();
				activity.showToast(viewCommonResponse.getMessage());
			} else {
				activity.showToast(viewCommonResponse.getMessage());
			}
			break;
		case CustomFormService.FORM_NOTIFY:
			ProgressDialogUtil.close();
			activity.showToast(viewCommonResponse.getMessage());
			break;
		case CustomFormService.FORM_QUERY_ADD:
			if (BalanceActivity.finish >= BalanceActivity.finishCount) {
				ProgressDialogUtil.close();
			}
			balance = (List<CustomForm>) viewCommonResponse.getData();

			if (balance != null) {
				balanceAdapter.getBalance().addAll(balance);
				balanceAdapter.notifyDataSetChanged();
				if (balance.size() < pageRecords) {
					canUpdate = false;
				}
			} else {
				canUpdate = false;
			}
			break;

		case CustomFormService.FORM_SEARCH:
			ProgressDialogUtil.close();
			balance = (List<CustomForm>) viewCommonResponse.getData();
			if (balance != null) {
				if (activity.getUser().getLogintype() == 1) {
					searchAdapter = new PaymentAdapter(activity, this, true);
				}
				if (activity.getUser().getLogintype() == 0) {
					searchAdapter = new PaymentAdapter(activity, this, false);
				}

				searchAdapter.setBalance(balance);
				searchAdapter.notifyDataSetChanged();
				lvBalance.setAdapter(searchAdapter);

			} else {
				showToast("没有查询到结果");
			}
			break;

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {

		if (canUpdate && arg3 != 0 && arg1 + arg2 == arg3) {
			canUpdate = false;
			BaseRequest requert = activity
					.createRequestWithUserId(CustomFormService.FORM_QUERY_ADD);// action名称
			currentPage++;
			requert.add("querytype", "2");
			if (user != null) {
				requert.add("shopid", user.getShopId());
			}
			requert.add("pageno", currentPage + "");
			requert.add("shopshowid", "all");
			new FormAsyncTask(null, this).execute(requert);
			currentPage++;
		}

		if (currentPage - 1 >= totalPages && totalPages != -1) {
		}
	}
}
