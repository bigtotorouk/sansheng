package com.activity.balance;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.balance.comment.CommentActivity;
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
import com.sansheng.model.Product;
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
public class PaymentDetailActivity extends CommonActivity implements
		OnClickListener {

	public static final String INTENT_PRICE = "price";
	public static final String INTENT_PV = "pv";

	private String price;
	private String pv;
	private int type = 0;
	private String orderCode;

	AlertDialog.Builder builder;
	private shopListView shopList;
	private TransOrder order;
	private CommonActivity activity;

	private TextView tvMember;
	private TextView tvReceiver;
	private TextView tvAddres;

	private TextView tvCode;

	public static String ACTION_BALANCE = "balance";
	private Button btnComment;
	TextView tvLogiscs;
	TextView tvLogiscsNum;

	FormDetail fomDetail;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		activity = this;
		setContentView(R.layout.activity_payment_balance);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("报单详情");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		shopList = (shopListView) findViewById(R.id.Shop_List);
		// shopList.bindData(ReapActivity.order.getProductlist());
		tvMember = (TextView) findViewById(R.id.Tv_Member);
		tvReceiver = (TextView) findViewById(R.id.Tv_Receiver);
		tvAddres = (TextView) findViewById(R.id.Tv_Address);
		tvCode = (TextView) findViewById(R.id.Tv_Order_Code);
		// sumaryView.tvSummaryPrice.setText(price);
		// sumaryView.tvSumamryPV.setText(pv);
		tvLogiscs = (TextView) findViewById(R.id.Tv_Logistics);
		tvLogiscsNum = (TextView) findViewById(R.id.Tv_Logistice_Num);
		findViewById(R.id.Btn_Comment).setOnClickListener(this);
		findViewById(R.id.Btn_Pick).setOnClickListener(this);
		initData();
	}

	public void initData() {
		CustomForm form = (CustomForm) getIntent().getExtras().get("from");
		BaseRequest requert = activity
				.createRequestWithUserId(CustomFormService.FORM_DETAIL);// action名称

		requert.add("querytype", "0");
		requert.add("orderid", form.getBalanceid());
		new FormAsyncTask(this, null).execute(requert);
	}

	private void pay() {
		ProgressDialogUtil.show(this, "提示", "正在支付", true, true);
		BaseRequest baseRequest = createRequest(ShopService.ORDER_PAY);
		baseRequest.add("userid", getOrderUserId());
		baseRequest.add("storeid", order.getStoreid());
		baseRequest.add("runno", orderCode);
		baseRequest.add("paytype", order.getPaytype());
		new ShopAsyncTask(this).execute(baseRequest);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;

		case R.id.Btn_Sumary:
			// Intent intent = new Intent(this, ReapActivity.class);
			// startActivity(intent);
			pay();
			break;
		case R.id.Btn_Comment:

			Intent intent = new Intent(this, CommentActivity.class);
			intent.setAction(CommentActivity.ACTION_COMENT);
			Bundle bundle = new Bundle();
			bundle.putSerializable(CommentActivity.BUNDLE_COMMENT, fomDetail);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		case R.id.Btn_Pick:
			break;
		}

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
		case CustomFormService.FORM_DETAIL:
			fomDetail = (FormDetail) viewCommonResponse.getData();
			bindFrom(fomDetail);
			break;

		}
	}

	public void bindFrom(FormDetail form) {
		// tvCode.setText("报单编号："+form.getOrdercode());

		if (form.getLogistics() != null) {
			tvLogiscs.setText(form.getLogistics());
		}
		if (form.getLogiscode() != null) {
			tvLogiscsNum.setText(form.getLogiscode());
		}

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

		shopList.bindData(form.getOderprlist());

	}
}
