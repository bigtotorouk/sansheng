package com.activity.achievement;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.ActionBar;
import com.activity.CommonActivity;
import com.activity.schedule.TabsAdapter;
import com.http.AchivementService;
import com.http.BaseRequest;
import com.http.Bill;
import com.http.ViewCommonResponse;
import com.http.task.AchivementAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.AchList;
import com.util.ProgressDialogUtil;
import com.view.BtnTab;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.TabController;
import com.view.TabController.TabListenner;

public class AchievementActivity extends CommonActivity implements
		OnClickListener {
	private TabController tabController;
	private ViewPager viewPager;

	public static List<AchList> achLists;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_results_tab);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);

		headBar.setTitle(getStr(R.string.results_query));
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabController = new TabController();

		BtnTab tabBtn_MyResults = (BtnTab) findViewById(R.id.Btn_MyResults);
		BtnTab tabBtn_Retail = (BtnTab) findViewById(R.id.Btn_Retail);
		BtnTab tabBtn_Apo = (BtnTab) findViewById(R.id.Btn_Apo);

		viewPager = (ViewPager) findViewById(R.id.ViewPaper_Content);
		TabsAdapter tabsAdapter = new TabsAdapter(this, viewPager);
		tabsAdapter.addTab(actionBar.newTab(), MyAchievement.class, null);
		tabsAdapter.addTab(actionBar.newTab(), SalePollFragment.class, null);
		tabsAdapter.addTab(actionBar.newTab(), ResalePoolFragment.class, null);

		tabController.addTab(tabBtn_MyResults);
		tabController.addTab(tabBtn_Retail);
		tabController.addTab(tabBtn_Apo);
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
		initDataList();
		viewPager.setOffscreenPageLimit(3);
	}

	/**
	 * 初始化
	 */
	public void initDataList() {
		BaseRequest requert = createRequestWithUserId(AchivementService.ACHI_LIST);
		new AchivementAsyncTask(this, null).execute(requert);
		ProgressDialogUtil.show(this, "提示", "正在加载数据", true, true);
	}

	/*
	 * 继承函数 网络请求会在这回调
	 */
	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		switch (action) {
		case AchivementService.ACHI_LIST:
			ProgressDialogUtil.close();
			achLists = (List<AchList>) viewCommonResponse.getData();
			if (achLists != null && achLists.size() >= 1) {
			} else {
				ProgressDialogUtil.close();
			}
			break;
		case AchivementService.ACHI_BILL:
			AchList achList = achLists.get(0);
			ProgressDialogUtil.close();
			Bill bill = (Bill) viewCommonResponse.getData();
			achLists = bill.getAchLists();
			break;

		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent i = null;
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;

		default:
			break;
		}
	}
}
