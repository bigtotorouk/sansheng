package com.activity.schedule.plan;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
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

import com.activity.CommonActivity;
import com.activity.schedule.CommonFragment;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.PlanDao;
import com.sansheng.model.Plan;
import com.sansheng.model.User;
import com.view.OprationDilog;

public class PlanAdapter extends BaseAdapter {

	private List<Plan> plans;
	private LayoutInflater layoutInflater;
	private CommonActivity activity;
	private PlanDao planDao;
	private UiHandler uiHandler;
	private static final int MSG_UPDATE = 1;
	public CommonFragment fragmentVisit;

	public PlanAdapter(CommonActivity context) {
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		plans = new ArrayList<Plan>();
		uiHandler = new UiHandler();
		activity = context;
	}

	@Override
	public int getCount() {
		if (plans == null) {
			return 1;
		}
		return 1;
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

	private class ViewHolder {
		public TextView tvContent;
		public Button btnDelet;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder hview = new ViewHolder();
		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_schedule_plan, null);
			hview.tvContent = (TextView) convertView
					.findViewById(R.id.Tv_Content);
			hview.btnDelet = (Button) convertView.findViewById(R.id.Btn_Delete);
			convertView.setTag(hview);
		}
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		bindView(viewHolder, null);
		return convertView;
	}

	public void bindView(ViewHolder viewHolder, final Plan plan) {
		// User u = activity.getUser();
		viewHolder.tvContent.setText("您当月复消额为" + "");// u.getRpv()

		viewHolder.btnDelet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Log.e("debug", "onclick");
//				final OprationDilog dilog = new OprationDilog(activity);
//				String content = activity.getResources().getString(
//						R.string.sure_delete);
//
//				dilog.setContent(content);
//				dilog.onOkCallBack(new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//						deletePlan(plan);
//						dilog.dismiss();
//						plans.remove(plan);
//						notifyDataSetChanged();
//					}
//				});
//				dilog.show();
				AlertDialog.Builder builder = new Builder(activity);
				builder.setMessage("删除该提醒？");
				builder.setTitle("提示");
				builder.setPositiveButton("确认",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int arg1) {
								// fragmentVisit.delete();
								deletePlan(plan);
								dialog.dismiss();
								plans.remove(plan);
								notifyDataSetChanged();
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

	private void deletePlan(final Plan plan) {
		try {
			planDao.delete(plan);
			plans.remove(plan);
			Message msg = new Message();
			msg.what = MSG_UPDATE;
			msg.obj = plans;
			uiHandler.sendMessage(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Plan> getLogistics() {
		return plans;
	}

	public void setLogistics(List<Plan> plans) {
		this.plans = plans;
		notifyDataSetChanged();
	}

	public PlanDao getLogisticsDao() {
		return planDao;
	}

	public void setLogisticsDao(PlanDao planDao) {
		this.planDao = planDao;
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
