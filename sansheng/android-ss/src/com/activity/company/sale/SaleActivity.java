package com.activity.company.sale;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.activity.company.InfoDetailActivity;
import com.http.company.SaleApi;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-8 下午3:43:11 declare:
 */
public class SaleActivity extends CommonActivity implements OnClickListener {
	private LocalInfoDao localInfoDao;
	private static final int MSG_UPDATE = 1;
	SaleAdapter saleAdapter;
	private UiHandler uiHandler;
	private Activity activity;

	protected void onCreate(android.os.Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_company_sale);

		activity = this;
		localInfoDao = getOrmDateBaseHelper().getLocalInfoDao();

		ListView lvSale = (ListView) findViewById(R.id.Lv_Announcement);
		lvSale.setDivider(null);

		saleAdapter = new SaleAdapter(this);
		saleAdapter.activity = this;
		lvSale.setAdapter(saleAdapter);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.hot_promotions));
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);
		uiHandler = new UiHandler();
		initWidget();
		update();

	}

	public void initWidget() {

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

	/**
	 * 网络更新数据
	 */
	public void update() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				List<LocalInfo> localInfos = SaleApi.getSales(0);
				Message msg = new Message();
				msg.what = MSG_UPDATE;
				msg.obj = localInfos;
				uiHandler.sendMessage(msg);

			};
		}.start();
	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_UPDATE:
				List<LocalInfo> localInfos = (List<LocalInfo>) msg.obj;
				saleAdapter.setLocalInfos(localInfos);
				saleAdapter.notifyDataSetChanged();
				break;

			default:
				break;
			}

		}
	}

}
