package com.activity.company.introduce;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.http.company.AnnouncementApi;
import com.http.company.IntroduceApi;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-8 下午1:39:55 declare:
 */
public class IntroduceAcitity extends CommonActivity implements OnClickListener {

	private LocalInfoDao localInfoDao;
	private static final int MSG_UPDATE = 1;
	private UiHandler uiHandler;
	IntroduceAdapter introduceAdapter;
	List<LocalInfo> localInfos;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_company_introduce);
		uiHandler = new UiHandler();
		// localInfoDao = getOrmDateBaseHelper().getLocalInfoDao();
		// List<LocalInfo> localInfos = null;
		//
		// localInfos = localInfoDao.getLoclInfosByType(InfoType.introduce);

		ListView lvIntroduceAdapter = (ListView) findViewById(R.id.Lv_Introduce);
		introduceAdapter = new IntroduceAdapter(this);
		introduceAdapter.setLocalInfos(localInfos);
		lvIntroduceAdapter.setAdapter(introduceAdapter);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.company_Indroduction));
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
				List<LocalInfo> localInfos = IntroduceApi.getIndustries();
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
//			i = new Intent(this, CompanyIndexActivity.class);
//			startActivity(i);
//			finish();
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
				introduceAdapter.setLocalInfos(localInfos);
				introduceAdapter.notifyDataSetChanged();
				break;

			default:
				break;
			}

		}
	}
}
