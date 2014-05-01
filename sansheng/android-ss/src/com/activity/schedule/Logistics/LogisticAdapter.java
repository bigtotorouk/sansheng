package com.activity.schedule.Logistics;

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
import android.widget.ListView;
import android.widget.TextView;

import com.activity.schedule.CommonFragment;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.model.Remind;
import com.sansheng.model.Schedule;
import com.util.DateUtil;
import com.util.ProgressDialogUtil;
import com.view.OprationDilog;

public class LogisticAdapter extends BaseAdapter {

	private ListView lvVisite;
	private List<Remind> schedules;
	Activity activity;
	private ScheduleDao scheduleDao;
	LayoutInflater layoutInflater;

	public Remind curRemind;

	public CommonFragment fragmentVisit;

	public LogisticAdapter(Activity a) {
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

		final Remind remind = schedules.get(position);

		if (convertView == null) {
			View view = layoutInflater.inflate(
					R.layout.layout_schedule_logistic, null);

			convertView = view;
		}

		TextView tvCode = (TextView) convertView.findViewById(R.id.Tv_Day);
		TextView tvStatues = (TextView) convertView
				.findViewById(R.id.Tv_Status);
		TextView tvPrice = (TextView) convertView.findViewById(R.id.Tv_Status);
		TextView tvTime = (TextView) convertView.findViewById(R.id.Tv_Time);

		if (remind.getRemindtitle() != null) {
			tvCode.setText(remind.getRemindtitle());
		}

		if (remind.getRemindstatus() != null) {
			tvStatues.setText(remind.getRemindstatus());
		}

		if (remind.getRemindpv() != null) {
			tvPrice.setText(remind.getRemindtitle());
		}

		if (remind.getRemindtime() != null) {
			tvTime.setText(remind.getRemindtime());
		}

		Button btnDelete = (Button) convertView.findViewById(R.id.Btn_Delet);

		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				curRemind = remind;

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

		return convertView;
	}

	public void delete(Schedule schedule) {
		try {
			scheduleDao.delete(schedule);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schedules.remove(schedule);
		notifyDataSetChanged();
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

}
