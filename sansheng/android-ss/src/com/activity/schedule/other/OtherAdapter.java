package com.activity.schedule.other;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.activity.schedule.CommonFragment;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.model.Remind;
import com.sansheng.model.Schedule;
import com.util.DateUtil;
import com.view.OprationDilog;

public class OtherAdapter extends BaseAdapter {

	private ListView lvVisite;
	private List<Remind> schedules;
	Activity activity;
	LayoutInflater layoutInflater;
	private ScheduleDao scheduleDao;

	public Remind curRemind;

	public CommonFragment fragmentVisit;

	public OtherAdapter(Activity a) {
		activity = a;
		layoutInflater = (LayoutInflater) a
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {

		if (schedules == null) {
			return 0;
		} else {
			return schedules.size();
		}
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final Remind schedule = schedules.get(position);

		if (convertView == null) {
			View view = layoutInflater.inflate(R.layout.layout_schedule_other,
					null); 

			convertView = view;
		}
		bindView(convertView, schedule);

		return convertView;
	}

	public void bindView(View view, final Remind schedule) {

		TextView tvDay = (TextView) view.findViewById(R.id.Tv_Day);
		TextView tvCustome = (TextView) view.findViewById(R.id.Tv_Custome);
		TextView tvContent = (TextView) view.findViewById(R.id.Tv_Content);
		 
		ImageButton btnDelete = (ImageButton) view
				.findViewById(R.id.Btn_Delete);
		if (schedule.getRemindtitle() != null) {
			tvCustome.setText(schedule.getRemindtitle());
		}

		if (schedule.getRemindtime() != null) {
			tvDay.setText(schedule.getRemindtime());
		}
		if (schedule.getRemindinfo() != null) {
			tvContent.setText("地点:" + schedule.getRemindinfo());
		}
		btnDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				curRemind = schedule;

				AlertDialog.Builder builder = new Builder(fragmentVisit
						.getActivity());
				builder.setMessage("删除该提醒？");
				builder.setTitle("提示");
				builder.setPositiveButton("确认",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								fragmentVisit.delete();
							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				builder.show();

			}
		});

	}

	public void delete(Schedule schedule) {
		try {
			scheduleDao.delete(schedule);
			notifyDataSetChanged();
			Log.e("debug", "btnDelete  click");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Remind> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Remind> schedules) {
		this.schedules = schedules;
	}

	public ScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	public void setScheduleDao(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	public CommonFragment getFragmentVisit() {
		return fragmentVisit;
	}

	public void setFragmentVisit(CommonFragment fragmentVisit) {
		this.fragmentVisit = fragmentVisit;
	}

}
