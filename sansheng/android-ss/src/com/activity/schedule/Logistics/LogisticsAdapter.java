package com.activity.schedule.Logistics;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.schedule.Logistics.FragmengLosistics.UiHandler;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.LogisticsDao;
import com.sansheng.model.Logistics;
import com.view.OprationDilog;

public class LogisticsAdapter extends BaseAdapter {

	private List<Logistics> logisticses;
	private LayoutInflater layoutInflater;
	private Activity activity;
	private LogisticsDao logisticsDao;
	private UiHandler uiHandler;
	private static final int MSG_UPDATE = 1;

	public LogisticsAdapter(Activity context) {
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		logisticses = new ArrayList<Logistics>();
		uiHandler = new UiHandler();
		activity = context;
	}

	@Override
	public int getCount() {
		if (logisticses == null) {
			return 0;
		}
		return logisticses.size();
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
		Logistics logistics = logisticses.get(position);
		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_schedule_logistics, null);

		}
		bindView(convertView, logistics);
		return convertView;
	}

	public void bindView(View view, final Logistics logistics) {
		TextView tvId = (TextView) view.findViewById(R.id.Tv_Logistics_Id);
		TextView tvSatus = (TextView) view
				.findViewById(R.id.Tv_Logistics_Status);
		TextView tvBillSum = (TextView) view.findViewById(R.id.Tv_Bill_Sum);
		TextView tvtime = (TextView) view.findViewById(R.id.Tv_Time);
		Button btnDelete = (Button) view.findViewById(R.id.Btn_Delete);
		if (logistics.getLogistics_id() != null) {
			tvId.setText("编号为" + logistics.getLogistics_id() + "的报单");
		}
		if (logistics.getLogistics_status() != null) {
			tvSatus.setText(logistics.getLogistics_status());
		}
		tvBillSum.setText("报单金额" + logistics.getBill_sum());
		if (logistics.getDelivery_time() != null) {
			tvtime.setText("发货时间:" + logistics.getDelivery_time());
		}
		if (logistics.getRecive_time() != null) {
			tvtime.setText("收获时间:" + logistics.getRecive_time());
		}
		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Log.e("debug", "onclick");
				final OprationDilog dilog = new OprationDilog(activity);
				String content = activity.getResources().getString(
						R.string.sure_delete);

				dilog.setContent(content);
				dilog.onOkCallBack(new OnClickListener() {

					@Override
					public void onClick(View v) {
						delete(logistics);
						dilog.dismiss();
						logisticses.remove(logistics);
						notifyDataSetChanged();
					}
				});
				dilog.show();

			}

		});

	}

	private void delete(final Logistics logistics) {
		try {
			logisticsDao.delete(logistics);
			logisticses.remove(logistics);
			Message msg = new Message();
			msg.what = MSG_UPDATE;
			msg.obj = logisticses;
			uiHandler.sendMessage(msg);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Logistics> getLogistics() {
		return logisticses;
	}

	public void setLogistics(List<Logistics> logistics) {
		this.logisticses = logistics;
		notifyDataSetChanged();
	}

	public LogisticsDao getLogisticsDao() {
		return logisticsDao;
	}

	public void setLogisticsDao(LogisticsDao logisticsDao) {
		this.logisticsDao = logisticsDao;
	}

	public class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {

			case MSG_UPDATE:
				Toast.makeText(activity, "正在更新", Toast.LENGTH_SHORT).show();
				notifyDataSetChanged();

				break;

			default:
				break;
			}

		}
	}

}
