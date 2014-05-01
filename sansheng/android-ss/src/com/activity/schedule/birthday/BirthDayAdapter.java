package com.activity.schedule.birthday;

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
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.model.Remind;
import com.sansheng.model.Schedule;
import com.util.DateUtil;
import com.view.OprationDilog;

public class BirthDayAdapter extends BaseAdapter {

	private ListView lvVisite;
	private List<Remind> schedules;
	Activity activity;
	LayoutInflater layoutInflater;
	private ScheduleDao scheduleDao;
	public CommonFragment fragmentVisit;

	public Remind curRemind;

	public BirthDayAdapter(Activity a) {
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
			View view = layoutInflater.inflate(
					R.layout.layout_schedule_birthday, null);
			TextView tvDay = (TextView) view.findViewById(R.id.Tv_Day);
			TextView tvCustome = (TextView) view.findViewById(R.id.Tv_Custome);

			if (schedule.getRemindtime() != null) {
				tvCustome.setText(schedule.getRemindtime() + "生日");
			}

			if (schedule.getRemindtime() != null) {
				Date dateSchedule = DateUtil.parse(schedule.getRemindtime());
				long dayDiff = DateUtil.getDateDiff(dateSchedule, new Date()) + 1;
				tvDay.setText(dayDiff + "天后");
			}
			convertView = view;
		}

		Button btnCall = (Button) convertView.findViewById(R.id.Btn_Call);

		Button btnSMS = (Button) convertView.findViewById(R.id.Btn_SMS);
		Button btnDelete = (Button) convertView.findViewById(R.id.Btn_Delete);
		if (schedule.getPhoneNumber() == null
				|| schedule.getPhoneNumber().equals("")) {
			btnCall.setVisibility(View.INVISIBLE);
			btnSMS.setVisibility(View.INVISIBLE);
		}

		btnCall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.e("debug", "phone:" + schedule.getPhoneNumber());

				Uri uri = Uri.parse("tel:" + schedule.getPhoneNumber());
				Intent intent = new Intent(Intent.ACTION_CALL, uri);
				activity.startActivity(intent);
			}
		});
		btnSMS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri smsToUri = Uri.parse("smsto://" + schedule.getPhoneNumber());
				Intent mIntent = new Intent(
						android.content.Intent.ACTION_SENDTO, smsToUri);
				activity.startActivity(mIntent);
			}
		});

		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				curRemind = schedule;
				System.out.print("" + schedule);
				Log.e("debug", schedule.toString() + "");
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

	public Remind getCurRemind() {
		return curRemind;
	}

	public void setCurRemind(Remind curRemind) {
		this.curRemind = curRemind;
	}

}
