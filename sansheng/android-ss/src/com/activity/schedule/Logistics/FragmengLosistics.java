package com.activity.schedule.Logistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.schedule.CommonFragment;
import com.activity.schedule.visite.VisiteAdapter;
import com.application.CommonApplication;
import com.http.RemindService;
import com.http.ViewCommonResponse;
import com.lekoko.sansheng.R;
import com.sansheng.model.Remind;
import com.util.ProgressDialogUtil;

//修改
public class FragmengLosistics extends CommonFragment {

	private View view;
	public static final int MSG_DATE = 1;
	private ListView lvLogistics;
	LogisticsAdapter logisticsAdapter;
	private CommonActivity commonActivity;

	private UiHandler uiHandler;
	static List<Remind> reminds;
	private LogisticAdapter visiteAdapter;

	RemindService remindService;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = (View) inflater
				.inflate(R.layout.fragment_remind_logistics, null);
		initWidget();
		return view;
	}

	public void initWidget() {
		remindService = new RemindService();
		commonActivity = (CommonActivity) getActivity();
		CommonApplication comapp = (CommonApplication) commonActivity
				.getApplication();

		lvLogistics = (ListView) view.findViewById(R.id.Lv_Logistics);
		logisticsAdapter = new LogisticsAdapter(this.getActivity());
		lvLogistics.setAdapter(logisticsAdapter);
		uiHandler = new UiHandler();

		visiteAdapter = new LogisticAdapter(commonActivity);
		visiteAdapter.fragmentVisit = this;
		lvLogistics.setAdapter(visiteAdapter);
		update();

	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case 0:
				List<Remind> reminds = (List<Remind>) msg.obj;
				visiteAdapter.setSchedules(reminds);
				visiteAdapter.notifyDataSetChanged();
				break;

			case 1:
				ProgressDialogUtil.close();
				Remind remind = visiteAdapter.curRemind;
				visiteAdapter.getSchedules().remove(remind);
				visiteAdapter.notifyDataSetChanged();
				break;
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		new Thread() {
			public void run() {
				Map<String, String> p = new HashMap<String, String>();
				p.put("userid", commonActivity.getUserId());
				p.put("type", "4");
				ViewCommonResponse resp = remindService.queryAllRemind(p);
				reminds = (List<Remind>) resp.getData();
				Message msg = new Message();
				msg.obj = reminds;
				msg.what = 0;
				uiHandler.sendMessage(msg); 
			};
		}.start();
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		super.delete();
		ProgressDialogUtil.show(this.getActivity(), "删除中", "", true, true);

		new Thread() {
			public void run() {
				Map<String, String> p = new HashMap<String, String>();
				p.put("msid", visiteAdapter.curRemind.getRemindid());
				p.put("userid", "H7Mud3IiaWjWqdL4J4qEJA==");
				ViewCommonResponse resp = remindService.deleteRemind(p);

				if (resp.getHttpCode() == 200) {
					reminds = (List<Remind>) resp.getData();
					Message msg = new Message();
					msg.what = 1;
					uiHandler.sendMessage(msg);
				}
			};
		}.start();
	}

}
