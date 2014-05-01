package com.activity.balance.unpayment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.balance.UnPaymentDetailActivity;
import com.activity.schedule.CommonFragment;
import com.activity.shop.payment.PaymentActivity;
import com.http.BaseRequest;
import com.http.CustomFormService;
import com.http.task.FormAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.CustomForm;
import com.util.ProgressDialogUtil;

public class unPaymentSearchAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater;
	private CommonActivity activity;
	private Uihandler uihandler;
	private List<CustomForm> balance;
	public int currentP;
	CommonFragment fragment;

	private boolean shopMode;

	public unPaymentSearchAdapter(CommonActivity context, CommonFragment cf,
			boolean isShop) {
		this.activity = context;
		fragment = cf;
		uihandler = new Uihandler();
		layoutInflater = (LayoutInflater) context.getLayoutInflater();
		balance = new ArrayList<CustomForm>();

		shopMode = isShop;
	}

	public List<CustomForm> getBalance() {
		return balance;
	}

	public void setBalance(List<CustomForm> balance) {
		this.balance = balance;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (balance == null) {
			return 0;
		}
		return balance.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_balance_unpayment, null);
		}
		bindView(convertView, arg0);
		return convertView;
	}

	public void bindView(View v, final int position) {
		final CustomForm bl = balance.get(position);
		View layoutView = v.findViewById(R.id.Layout);
		layoutView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("debug", "click");
				Log.e("debug", "click");
				Intent intent = new Intent(activity,
						UnPaymentDetailActivity.class);
				Bundle b = new Bundle();
				b.putSerializable("from", bl);
				intent.putExtras(b);
				activity.startActivity(intent);
			}
		});

		Button imgBtn_Del = (Button) v.findViewById(R.id.btn_Delet);

		imgBtn_Del.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(activity);
				builder.setMessage("确认删除");
				builder.setTitle("提示");
				currentP = position;
				builder.setPositiveButton("确认",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								ProgressDialogUtil.show(activity, "提示", "正在删除",
										true, true);
								BaseRequest request = activity
										.createRequestWithUserId(CustomFormService.FORM_DELETE);
								request.add("balanceno", "" + bl.getBalanceno());
								request.add("balanqishu", bl.getBalanqishu());
								new FormAsyncTask(null, fragment)
										.execute(request);

							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});
				builder.show();
			}
		});

		TextView tvBalanceno = (TextView) v.findViewById(R.id.Tv_Balanceno);
		TextView tvUserno = (TextView) v.findViewById(R.id.Tv_Userno);
		TextView tvUserName = (TextView) v.findViewById(R.id.Tv_UserName);
		TextView tvShopno = (TextView) v.findViewById(R.id.Tv_Shopno);
		TextView tvAllmoney = (TextView) v.findViewById(R.id.Tv_Allmoney);
		TextView tvAllPv = (TextView) v.findViewById(R.id.Tv_AllPv);

		if (bl.getBalanceno() != null) {
			tvBalanceno.setText(bl.getBalanceno());
		}
		if (bl.getBalanceno() != null) {
			tvUserno.setText(bl.getBalanceid());
		}
		if (bl.getUsername() != null) {
			tvUserName.setText(bl.getUsername());
		}
		if (bl.getShopno() != null) {
			tvShopno.setText(bl.getShopno());
		}
		if (bl.getAllmoney() != null) {
			tvAllmoney.setText("￥" + bl.getAllmoney());
		}
		if (bl.getAllpv() != null) {
			tvAllPv.setText(bl.getAllpv() + "pv");
		}
		TextView tvPay = (TextView) v.findViewById(R.id.Tv_Pay);
		Button btnPay = (Button) v.findViewById(R.id.BtnPay);
		if (shopMode == true) {
			tvPay.setVisibility(View.GONE);
			btnPay.setVisibility(View.VISIBLE);
			btnPay.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.e("debug", "click");
					Intent intent = new Intent(activity, PaymentActivity.class);
					if (activity.getUser().getLogintype() == 0) {
						intent.setAction(UnPaymentDetailActivity.ACTION_MEMBER_UNPAY);

					} else {
						intent.setAction(UnPaymentDetailActivity.ACTION_SHOP_UNPAY);

					}
					Bundle bundle = new Bundle();
					bundle.putSerializable("form", bl);
					intent.putExtras(bundle);
					activity.startActivity(intent);
				}
			});
		} else {
			tvPay.setVisibility(View.VISIBLE);
			btnPay.setVisibility(View.GONE);
		}

	}

	class Uihandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {

		}
	}

	public void remove() {
		CustomForm cf = balance.get(currentP);
		balance.remove(cf);

	}
}
