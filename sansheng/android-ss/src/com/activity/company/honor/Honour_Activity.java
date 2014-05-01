package com.activity.company.honor;

import java.util.List;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.http.company.HonourApi;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ListView;

public class Honour_Activity extends CommonActivity implements OnClickListener {
	private ListView listview = null;
	private HonourAdapter adapter = null;
	List<LocalInfo> localInfos;
	private static final int MSG_UPDATE = 1;
	private UiHandler uiHandler;

	@Override
	protected void onCreate(android.os.Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_company_honor);
		uiHandler = new UiHandler();

		this.listview = (ListView) findViewById(R.id.Lv_HonorYear);
		this.adapter = new HonourAdapter(this);
		adapter.setLocalInfos(localInfos);
		this.listview.setAdapter(adapter);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.company_honor));
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		update();
	}

	/**
	 * 网络更新数据
	 */
	public void update() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				List<LocalInfo> localInfos = HonourApi.getHonour();
				Message msg = new Message();
				msg.what = MSG_UPDATE;
				msg.obj = localInfos;
				uiHandler.sendMessage(msg);

			};
		}.start();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent i = null;
		switch (id) {
		case R.id.Btn_Back:
			// i = new Intent(this, CompanyIndexActivity.class);
			// startActivity(i);
			// finish();
			onBackPressed();
			break;

		default:
			break;
		}
	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_UPDATE:
				List<LocalInfo> localInfos = (List<LocalInfo>) msg.obj;
				adapter.setLocalInfos(localInfos);
				adapter.notifyDataSetChanged();
				// localInfoDao.deleteByType(InfoType.honor);
				// localInfoDao.batchInsert(localInfos);
				break;

			default:
				break;
			}

		}
	}
}
