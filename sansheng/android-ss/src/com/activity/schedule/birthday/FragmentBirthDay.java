package com.activity.schedule.birthday;

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
import com.activity.schedule.visite.FragmentVisit;
import com.activity.schedule.visite.VisiteAdapter;
import com.application.CommonApplication;
import com.http.RemindService;
import com.http.ViewCommonResponse;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.model.Remind;
import com.sansheng.model.Schedule;
import com.sansheng.model.Schedule.Type;
import com.util.ProgressDialogUtil;

public class FragmentBirthDay extends CommonFragment {

	private ListView lvVisite;
	private BirthDayAdapter brithDayAdapter;
	private View view;
	private CommonActivity commonActivity;
	private ScheduleDao scheduleDao;

	private FragmentVisit fVisit;

	private UiHandler uiHandler;
	static List<Remind> reminds;

	RemindService remindService;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_remind_birthday, null);
		initWidget();
		return view;
	}

	public void initWidget() {
		uiHandler = new UiHandler();
		remindService = new RemindService();
		commonActivity = (CommonActivity) getActivity();
		CommonApplication comapp = (CommonApplication) commonActivity
				.getApplication();
		lvVisite = (ListView) view.findViewById(R.id.Lv_Visite);
		brithDayAdapter = new BirthDayAdapter(commonActivity);
		brithDayAdapter.setScheduleDao(scheduleDao);
		brithDayAdapter.fragmentVisit = this;
		lvVisite.setAdapter(brithDayAdapter);
		lvVisite.setAdapter(brithDayAdapter);
		update();
		/** 原先设计点击进入详情 **/
		// lvVisite.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View arg1,
		// int position, long arg3) {
		//
		// Log.e("debug", "onItenmClick");
		// Schedule schedule = brithDayAdapter.getSchedules()
		// .get(position);
		// Intent intent = new Intent(commonActivity,
		// AddScheduleActivity.class);
		// Bundle bundle = new Bundle();
		// bundle.putSerializable("schedule", schedule);
		// intent.putExtras(bundle);
		// startActivity(intent);
		// commonActivity.finish();
		// }
		// });

	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case 0:
				ProgressDialogUtil.close();
				List<Remind> reminds = (List<Remind>) msg.obj;
				brithDayAdapter.setSchedules(reminds);
				brithDayAdapter.notifyDataSetChanged();
				break;

			case 1:
				ProgressDialogUtil.close();
				Remind remind = brithDayAdapter.curRemind;
				brithDayAdapter.getSchedules().remove(remind);
				brithDayAdapter.notifyDataSetChanged();
				break;
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		ProgressDialogUtil.show(commonActivity, "提示", "加载数据中", true, true);
		new Thread() {
			public void run() {

				Map<String, String> p = new HashMap<String, String>();
				CommonActivity activity = (CommonActivity) getActivity();
				p.put("userid", activity.getUserId());
				p.put("type", "2");
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
				p.put("msid", brithDayAdapter.curRemind.getRemindid());
				CommonActivity activity = (CommonActivity) getActivity();
				p.put("userid", activity.getUserId());
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
