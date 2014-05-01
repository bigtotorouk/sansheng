package com.activity.schedule.plan;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.application.CommonApplication;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.PlanDao;
import com.sansheng.model.Plan;

//修改
public class FragmentPlan extends Fragment {

	private View view;
	public static final int MSG_DATE = 1;
	private ListView lvLogistics;
	PlanAdapter planAdapter;
	private UiHandler uiHandler;
	private CommonActivity commonActivity;
	private PlanDao planDao;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = (View) inflater
				.inflate(R.layout.fragment_remind_logistics, null);
		initWidget();
		return view;
	}

	public void initWidget() {
		commonActivity = (CommonActivity) getActivity();
		CommonApplication comapp = (CommonApplication) commonActivity
				.getApplication();
		planDao = comapp.getOrmDateBaseHelper().getPlanDao();

		// for (int i = 0; i < 3; i++) {
		// Plan plan = new Plan();
		// plan.setSum("23");
		// plan.setPlan_data(DateUtil.Format(new java.util.Date()));
		// try {
		// planDao.create(plan);
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		lvLogistics = (ListView) view.findViewById(R.id.Lv_Logistics);
		planAdapter = new PlanAdapter((CommonActivity) this.getActivity());
		lvLogistics.setAdapter(planAdapter);
		planAdapter.setLogisticsDao(planDao);
		uiHandler = new UiHandler();

	}

	public class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_DATE:
				List<Plan> plans = (List<Plan>) msg.obj;
				planAdapter.setLogistics(plans);
				break;

			default:
				break;
			}

		}

	}
}
