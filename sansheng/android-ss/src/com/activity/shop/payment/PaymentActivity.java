package com.activity.shop.payment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.shop.ShopActivity;
import com.activity.shop.address.ReapActivity;
import com.http.BaseRequest;
import com.http.CustomFormService;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.http.task.CustomeAsynctask;
import com.http.task.FormAsyncTask;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Address;
import com.sansheng.model.CustomForm;
import com.sansheng.model.FormDetail;
import com.sansheng.model.Order;
import com.sansheng.model.TransOrder;
import com.sansheng.model.User;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.ShopTypeItem;
import com.view.SumaryView;
import com.view.shopListView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-7 下午3:31:04 declare:
 */
public class PaymentActivity extends CommonActivity implements OnClickListener {

	public static final String INTENT_PRICE = "price";
	public static final String INTENT_PV = "pv";
	public final static String ACTION_NEW = "new";
	public final static String ACTION_SHOP_DAI = "shop";
	public final static String ACTION_SHOP_SELF = "shop_self";
	private ShopTypeItem itemTong;
	private ShopTypeItem itemPos;
	private ShopTypeItem itemThird;
	private String price;
	private String pv;
	private int type = 0;
	private String orderCode;

	SumaryView sumaryView;
	AlertDialog.Builder builder;
	private shopListView shopList;
	private TransOrder order;
	private CommonActivity activity;

	private TextView tvMember;
	private TextView tvReceiver;
	private TextView tvPhone;
	private TextView tvAddres;

	private TextView tvCode;

	public static String ACTION_BALANCE = "balance";

	// 支付方式 0 订单支付 1 店长确认支付
	private int payWay = 0;
	CustomForm from;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		activity = this;
		setContentView(R.layout.activity_payment);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("支付方式");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		sumaryView = (SumaryView) findViewById(R.id.SS2);
		shopList = (shopListView) findViewById(R.id.Shop_List);
		shopList.setNeeedShowPrice(false);
		if (ReapActivity.order != null) {
			shopList.bindData(ReapActivity.order.getProductlist());
		}
		tvMember = (TextView) findViewById(R.id.Tv_Member);
		tvReceiver = (TextView) findViewById(R.id.Tv_Receiver);
		tvPhone = (TextView) findViewById(R.id.Tv_Phone);
		tvAddres = (TextView) findViewById(R.id.Tv_Address);
		tvCode = (TextView) findViewById(R.id.Tv_Order_Code);
		// sumaryView.tvSummaryPrice.setText(price);
		// sumaryView.tvSumamryPV.setText(pv);
		TextView TvSumPrice = (TextView) findViewById(R.id.Tv_Sumamry_Number);
		TextView TvSumPv = (TextView) findViewById(R.id.Tv_Sumamry_Pv);
		TvSumPrice.setText("￥" + getSumPrice());
		TvSumPv.setText(getSumPv() + "pv");

		itemTong = (ShopTypeItem) findViewById(R.id.Item_TONG);
		itemPos = (ShopTypeItem) findViewById(R.id.Item_POS);
		itemThird = (ShopTypeItem) findViewById(R.id.Item_Third);
		itemPos.selected();

		itemTong.setOnClickListener(this);
		itemPos.setOnClickListener(this);
		itemThird.setOnClickListener(this);

		sumaryView.btnSumary.setOnClickListener(this);

		itemTong.selected();
		itemPos.unselected();
		itemThird.unselected();
		initData();
	}

	public void initData() {

		Intent intent = getIntent();
		if (intent == null || intent.getAction() == null) {
			return;
		}

		if (intent.getAction().equals(ACTION_NEW)) {
			Bundle bundle = intent.getExtras();
			from = (CustomForm) bundle.get("form");
			BaseRequest requert = activity
					.createRequestWithUserId(CustomFormService.FORM_DETAIL);// action名称
			order = new TransOrder();
			order.setStoreid(from.getShopno());
			orderCode = from.getBalanceid();
			requert.add("orderid", from.getBalanceid());
			new FormAsyncTask(this, null).execute(requert);
			payWay = 1;
		} else if (intent.getAction().equals(ACTION_SHOP_DAI)) {
			order = (TransOrder) getIntent().getExtras().get("order");
			orderCode = (String) getIntent().getExtras().getString("orderCode");

			tvCode.setText("报单编号：" + orderCode);// BaseRequest baseRequest =
			if (order.getHomeAddres() != null) {
				bindFrom(order);
			}
		} else if (intent.getAction().equals(ACTION_SHOP_SELF)) {
			Bundle bundle = intent.getExtras();
			from = (CustomForm) bundle.get("form");
			order = new TransOrder();
			order.setShopno(from.getShopno());
			order.setStoreid(from.getShopno());
			orderCode = from.getBalanceno();

			BaseRequest requert = activity
					.createRequestWithUserId(CustomFormService.FORM_DETAIL);// action名称
			requert.add("orderid", from.getBalanceid());
			new FormAsyncTask(this, null).execute(requert);

		}
		// createRequestWithUserId(CustomFormService.FORM_DETAIL);
		// baseRequest.add("userid", getUserId());
		// baseRequest.add("orderid", "1");
		// baseRequest.add("showid", "1");
		// new FormAsyncTask(this, null).execute(baseRequest);
	}

	private void pay() {
		ProgressDialogUtil.show(this, "提示", "正在支付", true, true);
		BaseRequest baseRequest = createRequest(ShopService.ORDER_PAY);
		User user = getUser();

		baseRequest.add("userid", getOrderUserId());
		baseRequest.add("storeid", order.getStoreid());
		baseRequest.add("runno", orderCode);
		baseRequest.add("zhifutype", ""
				+ (Integer.parseInt(getLoginType()) + 1));
		baseRequest.add("paytype", "" + 1);
		new ShopAsyncTask(this).execute(baseRequest);

		// ProgressDialogUtil.show(this, "提示", "正在支付", true, true);
		// BaseRequest baseRequest =
		// createRequestWithUserId(ShopService.ORDER_PAY);
		// User user = getUser();
		//
		// baseRequest.add("storeid", order.getStoreid());
		// baseRequest.add(3"runno", orderCode);
		// baseRequest.add("zhifutype", "" + (user.getLogintype() + 1));
		// baseRequest.add("paytype", "" + 1);
		// new ShopAsyncTask(this).execute(baseRequest);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;
		case R.id.Item_TONG:
			itemTong.selected();
			itemPos.unselected();
			itemThird.unselected();
			type = 0;
			break;  
		case R.id.Item_POS:
//			itemTong.unselected();
//			itemPos.selected();
//			itemThird.unselected();
//			type = 1;
			break;
		case R.id.Item_Third:
			// itemTong.unselected();
			// itemPos.unselected();
			// itemThird.selected();
			// type = 2;
			break;

		case R.id.Btn_Sumary:
			// Intent intent = new Intent(this, ReapActivity.class);
			// startActivity(intent);

			if (payWay == 0) {
				pay();
			} else {
				ShoperPay();
			}
			break;
		}

	}

	public void ShoperPay() {
		BaseRequest baseRequest = createRequestWithUserId(CustomFormService.FORM_PAY);
		baseRequest.add("storeid", from.getShopno());
		baseRequest.add("runno", from.getBalanceid());
		baseRequest.add("paytype", "1");
		new FormAsyncTask(this, null).execute(baseRequest);
	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		ProgressDialogUtil.close();
		switch (action) {
		case ShopService.ORDER_PAY:
			if (viewCommonResponse.getMsgCode() == 0) {
				finishPay(viewCommonResponse.getMessage());
			} else {
				showToast(viewCommonResponse.getMessage());
			}
			break;
		case CustomFormService.FORM_DETAIL:
			FormDetail form = (FormDetail) viewCommonResponse.getData();
			Log.e("debug", "form" + form);
			if (form != null) {
				bindFrom(form);
			}
			break;

		case CustomFormService.FORM_PAY:
			if (viewCommonResponse.getMsgCode() == 0) {
				showToast(viewCommonResponse.getMessage());
				finish();
			} else {
				showToast(viewCommonResponse.getMessage());
			}
			break;

		}
	}

	private void finishPay(String msg) {
		builder = new Builder(this);
		builder.setMessage(msg);
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Intent intent = new Intent(activity, ShopActivity.class);
				startActivity(intent);
				finish();
			}
		});
		// builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
		// {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// // activity.finish();
		//
		// }
		// });
		builder.show();
	}

	public void bindFrom(TransOrder order) {
		// tvCode.setText("报单编号："+form.getOrdercode());
		User user = getUser();   //这里有改动
		if (order.getUsername() != null) {
			tvMember.setText("会  员:" + this.getOrderUserName());
		}
		if (order.getReceiver() != null) {
			tvReceiver.setText("收货人:" + order.getReceiver());
		}
		if (order.getMobiletel() != null) {
			tvPhone.setText("电  话:" + order.getMobiletel());
		}
		if (order.getAddress() != null) {
			tvAddres.setText("收货地址:" + order.getAddress());
		}

	}

	public void bindFrom(FormDetail form) {
		// tvCode.setText("报单编号："+form.getOrdercode());

		User user = getUser();
		if (form.getUsername() != null) {
			tvMember.setText("会  员:" + form.getUsername());
		}
		if (form.getReceiptusername() != null) {
			tvReceiver.setText("收货人:" + form.getReceiptusername());
		}

		if (form.getReceiptuseradds() != null) {
			tvAddres.setText("收获地址:" + form.getReceiptuseradds());
		}
		if (form.getReceiptusercall() != null) {
			tvPhone.setText("电话:" + form.getReceiptusercall());
		}

		shopList.bindData(form.getOderprlist());

	}

}
