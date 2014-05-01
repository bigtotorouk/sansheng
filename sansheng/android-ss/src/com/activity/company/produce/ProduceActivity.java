package com.activity.company.produce;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.http.company.ProductApi;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

public class ProduceActivity extends CommonActivity implements OnClickListener {
	private static final int MSG_UPDATE = 1;
	private UiHandler uiHandler;
	ProduceAdapter produceAdapter;
	List<LocalInfo> localInfos;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_company_produce);
		uiHandler = new UiHandler();

		ListView lvQuality = (ListView) findViewById(R.id.Lv_Produce);
		produceAdapter = new ProduceAdapter(this);
		produceAdapter.setLocalInfos(localInfos);
		lvQuality.setAdapter(produceAdapter);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.produce_introduce));
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		update();
		// lvQuality.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		// long arg3) {
		// // TODO Auto-generated method stub
		// TextView txt = (TextView) arg1
		// .findViewById(R.id.Tv_CenterContent);
		// ImageView img = (ImageView) arg1
		// .findViewById(R.id.Iv_CenterImg);
		// if (txt.getVisibility() > 0) {
		// txt.setVisibility(View.VISIBLE);
		// img.setImageResource(R.drawable.products_arrow2);
		//
		// } else {
		// txt.setVisibility(View.GONE);
		// img.setImageResource(R.drawable.products_arrow1);
		// }
		// }
		// });
	}

	/**
	 * 网络更新数据
	 */
	private void update() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				List<LocalInfo> localInfos = ProductApi.getProuduces();
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
				produceAdapter.setLocalInfos(localInfos);
				produceAdapter.notifyDataSetChanged();
				break;

			default:
				break;
			}

		}
	}
}
