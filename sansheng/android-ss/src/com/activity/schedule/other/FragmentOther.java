package com.activity.schedule.other;

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
import com.application.CommonApplication;
import com.http.RemindService;
import com.http.ViewCommonResponse;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.model.Remind;
import com.util.ProgressDialogUtil;

/*
 * 其他日程界面
 * 
 */
public class FragmentOther extends CommonFragment {
	private ListView lvOther;
	private OtherAdapter otherAdapter;
	private View view;
	private CommonActivity commonActivity;

	private UiHandler uiHandler;
	static List<Remind> reminds;
	RemindService remindService;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_remind_visit, null);
		initWidget();
		return view;
	}

	public void initWidget() {
		uiHandler = new UiHandler();
		remindService = new RemindService();
		commonActivity = (CommonActivity) getActivity();
		CommonApplication comapp = (CommonApplication) commonActivity
				.getApplication();

		lvOther = (ListView) view.findViewById(R.id.Lv_Visite);
		otherAdapter = new OtherAdapter(commonActivity);
		lvOther.setAdapter(otherAdapter);
		otherAdapter.setFragmentVisit(this);
		update();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		new Thread() {
			public void run() {
				Map<String, String> p = new HashMap<String, String>();
				CommonActivity activity = (CommonActivity) getActivity();
				p.put("userid", activity.getUserId());
				p.put("type", "3");
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
				p.put("msid", otherAdapter.curRemind.getRemindid());
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
				otherAdapter.setSchedules(reminds);
				otherAdapter.notifyDataSetChanged();
				break;

			case 1:
				ProgressDialogUtil.close();
				Remind remind = otherAdapter.curRemind;
				otherAdapter.getSchedules().remove(remind);
				otherAdapter.notifyDataSetChanged();
				break;
			}
		}
	}

}
