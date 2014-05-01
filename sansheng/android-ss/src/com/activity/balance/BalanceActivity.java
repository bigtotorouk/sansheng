package com.activity.balance;

import java.util.List;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.ActionBar;
import com.activity.CommonActivity;
import com.activity.balance.payment.PaymentFragment;
import com.activity.balance.pre.PrepaymentFragment;
import com.activity.balance.unpayment.UnpaymentFragment;
import com.activity.company.CompanyIndexActivity;
import com.activity.schedule.TabsAdapter;
import com.activity.shop.cosmetic.CosmeticFragment;
import com.activity.shop.home.HealthFragment;
import com.activity.shop.nurse.NurseFragment;
import com.lekoko.sansheng.R;
import com.sansheng.model.Balance;
import com.sansheng.model.User;
import com.view.BtnTab;
import com.view.HeadBar;
import com.view.TabController;
import com.view.HeadBar.BtnType;
import com.view.TabController.TabListenner;

public class BalanceActivity extends CommonActivity implements OnClickListener {
	private TabController tabController;
	private ViewPager viewPager;
	public static List<Balance> bList;

	public static int finish;
	public static int finishCount;

	public static String ACTION_PUSH = "push";
	public static String META_TYPE = "id";
	public int push_id = 0;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		finish = 0;
		setContentView(R.layout.activity_balance);
		HeadBar headBar = (HeadBar) findViewById(R.id.HeadBar);
		headBar.setTitle("报单查询");
		headBar.setRightType(BtnType.empty);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		tabController = new TabController();
		headBar.setWidgetClickListener(this);
		BtnTab Btn_querytype0 = (BtnTab) findViewById(R.id.Btn_querytype0);
		BtnTab Btn_querytype1 = (BtnTab) findViewById(R.id.Btn_querytype1);

		viewPager = (ViewPager) findViewById(R.id.ViewPaper_Content);
		TabsAdapter tabsAdapter = new TabsAdapter(this, viewPager);
		tabsAdapter.addTab(actionBar.newTab(), UnpaymentFragment.class, null);

		User user = getUser();
		if (user == null) {
			return;
		}
		if (user.getLogintype() == 0) {
			finishCount = 2;
		} else {
			finishCount = 3;
		}
		tabController.addTab(Btn_querytype0);
		viewPager.setOffscreenPageLimit(2);
		if (user.getLogintype() == 1) {
			BtnTab Btn_Pre = (BtnTab) findViewById(R.id.Btn_Pre);
			Btn_Pre.setVisibility(View.VISIBLE);
			tabsAdapter.addTab(actionBar.newTab(), PrepaymentFragment.class,
					null);
			tabController.addTab(Btn_Pre);
			viewPager.setOffscreenPageLimit(3);
		}
		tabsAdapter.addTab(actionBar.newTab(), PaymentFragment.class, null);

		tabController.addTab(Btn_querytype1);
		tabController.setTabListenner(new TabListenner() {

			@Override
			public void onTabClick(final int i) {
				Log.e("debug", "index" + i);
				viewPager.setCurrentItem(i);
			}

		});
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int item) {
				viewPager.setCurrentItem(item);

				tabController.selected(item);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		initData();
	
	}

	public void initData() {
		Intent i = getIntent();
		if (i != null) {
			if (i.getAction() != null && i.getAction().equals(ACTION_PUSH)) {
				Bundle b = i.getExtras();
				if (b != null) {
					if (b.containsKey(META_TYPE)) {
						String type = b.getString(META_TYPE);
						int id = Integer.parseInt(type);
						if (id == 1) {
							viewPager.setCurrentItem(1);
						}
						if (id == 2) {
							viewPager.setCurrentItem(2);
						}
						if (id == 3) {
							viewPager.setCurrentItem(0);
						}

					}
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		Intent i = null;
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;

		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		Log.e("debug", "down  key:" + keyCode);
		return super.onKeyDown(keyCode, event);

	}

}
