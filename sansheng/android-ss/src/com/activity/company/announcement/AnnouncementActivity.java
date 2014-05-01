package com.activity.company.announcement;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.activity.company.InfoDetailActivity;
import com.http.company.AnnouncementApi;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu
 * 
 *         公司公告界面
 * 
 */
public class AnnouncementActivity extends CommonActivity implements
		android.view.View.OnClickListener {
	private static final int MSG_UPDATE = 1;
	private UiHandler uiHandler;
	AnnouncementAdapter announcementAdapter;
	private Activity activity;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_announcement);
		activity = this;
		List<LocalInfo> localInfos = null;
		ListView lvAnnouncement = (ListView) findViewById(R.id.Lv_Announcement);
		announcementAdapter = new AnnouncementAdapter(this);
		announcementAdapter.setLocalInfos(localInfos);
		lvAnnouncement.setAdapter(announcementAdapter);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.latest_news));
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		uiHandler = new UiHandler();
		update();
		lvAnnouncement.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				LocalInfo localInfo = announcementAdapter.getLocalInfos().get(
						position);
				Log.e("debug", "url");
				Intent i = new Intent(activity, InfoDetailActivity.class);
				i.putExtra(InfoDetailActivity.TITLE, "公告详情");
				i.putExtra(InfoDetailActivity.URL, localInfo.getUrl());
				startActivity(i);
			}
		});

	}

	/**
	 * 网络更新数据
	 */
	public void update() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				List<LocalInfo> localInfos = AnnouncementApi.getAnnouncment(0);
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
				final List<LocalInfo> localInfos = (List<LocalInfo>) msg.obj;
				announcementAdapter.setLocalInfos(localInfos);
				announcementAdapter.notifyDataSetChanged();

				break;

			default:
				break;
			}

		}
	}

}
