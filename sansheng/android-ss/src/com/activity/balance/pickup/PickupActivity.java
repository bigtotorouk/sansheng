package com.activity.balance.pickup;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.ActionBar;
import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.activity.schedule.TabsAdapter;
import com.activity.shop.cosmetic.CosmeticFragment;
import com.activity.shop.home.HealthFragment;
import com.activity.shop.nurse.NurseFragment;
import com.lekoko.sansheng.R;
import com.sansheng.model.Balance;
import com.view.BtnTab;
import com.view.HeadBar;
import com.view.TabController;
import com.view.HeadBar.BtnType;
import com.view.TabController.TabListenner;

public class PickupActivity extends CommonActivity implements OnClickListener {
	private TabController tabController;
	private ViewPager viewPager;
	public static List<Balance> bList;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_balance);
		HeadBar headBar = (HeadBar) findViewById(R.id.HeadBar);

		headBar.setTitle("已支付报单详情报单查询");
		headBar.setRightType(BtnType.empty);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		tabController = new TabController();
		headBar.setWidgetClickListener(this);

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

}
