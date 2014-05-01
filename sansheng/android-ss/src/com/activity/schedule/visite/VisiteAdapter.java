package com.activity.schedule.visite;

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

public class VisiteAdapter extends BaseAdapter {

	private ListView lvVisite;
	private List<Remind> schedules;
	Activity activity;
	private ScheduleDao scheduleDao;
	LayoutInflater layoutInflater;

	public Remind curRemind;

	public CommonFragment fragmentVisit;

	public VisiteAdapter(Activity a) {
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
			View view = layoutInflater.inflate(R.layout.layout_schedule_visite,
					null);

			convertView = view;
		}

		TextView tvDay = (TextView) convertView.findViewById(R.id.Tv_Day);
		TextView tvCustome = (TextView) convertView
				.findViewById(R.id.Tv_Custome);
		TextView tvContent = (TextView) convertView
				.findViewById(R.id.Tv_Visite_Conten);
		if (remind.getRemindtitle() != null) {
			tvCustome.setText(remind.getRemindtitle());
		}
		if (remind.getRemindinfo() != null) {
			tvContent.setText(remind.getRemindinfo());
		}

		if (remind.getRemindtime() != null) {
			Date dateSchedule = DateUtil.parse(remind.getRemindtime());
			long dayDiff = DateUtil.getDateDiff(dateSchedule, new Date()) + 1;
			tvDay.setText(dayDiff + "天后");
		}

		Button btnCall = (Button) convertView.findViewById(R.id.Btn_Cal);

		Button btnSMS = (Button) convertView.findViewById(R.id.Btn_SM);

		Button btnDelete = (Button) convertView.findViewById(R.id.Btn_Delet);

		if (remind.getMobilephone() == null
				|| remind.getMobilephone().equals("")) {
			btnCall.setVisibility(View.GONE);
			btnSMS.setVisibility(View.GONE);
		}

		btnCall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.e("debug", "phone:" + remind.getPhoneNumber());

				Uri uri = Uri.parse("tel:" + remind.getMobilephone());
				Intent intent = new Intent(Intent.ACTION_CALL, uri);
				activity.startActivity(intent);
			}
		});
		btnSMS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri smsToUri = Uri.parse("smsto://" + remind.getMobilephone());
				Intent mIntent = new Intent(
						android.content.Intent.ACTION_SENDTO, smsToUri);
				activity.startActivity(mIntent);
			}
		});

		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// final OprationDilog dilog = new OprationDilog(activity);
				// String content = activity.getResources().getString(
				// R.string.sure_delete);
				//
				// dilog.setContent(content);
				// dilog.onOkCallBack(new OnClickListener() {
				//
				// @Override
				// public void onClick(View v) {
				// curRemind = remind;
				// fragmentVisit.delete();
				// // delete(schedule);
				// // dilog.dismiss();
				// // schedules.remove(schedule);
				// // notifyDataSetChanged();
				// }
				// });
				// dilog.show();
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
