package com.activity.balance;

import java.text.DecimalFormat;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.balance.comment.CommentActivity;
import com.activity.balance.payment.PaymentFragment;
import com.activity.balance.pre.PrepaymentFragment;
import com.activity.balance.unpayment.UnpaymentFragment;
import com.activity.balance.unpayment.unPaymentAdapter;
import com.activity.shop.ShopActivity;
import com.http.BaseRequest;
import com.http.CustomFormService;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.http.task.FormAsyncTask;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.CustomForm;
import com.sansheng.model.FormDetail;
import com.sansheng.model.Product;
import com.sansheng.model.TransOrder;
import com.sansheng.model.User;
import com.util.AESOperator;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.ShopTypeItem;
import com.view.SumaryView;
import com.view.shopListView;
import com.view.shopQingListView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-7 下午3:31:04 declare:
 */
public class UnPaymentDetailActivity extends CommonActivity implements
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
	private TextView TvUserCode;
	private TextView tvCode;

	public static String ACTION_BALANCE = "balance";
	private Button btnComment;
	TextView tvLogiscs;
	TextView tvLogiscsNum;

	FormDetail fomDetail;
	CustomForm form;

	public static String ACTION_MEMBER_UNPAY = "member_unpay";
	public static String ACTION_MEMBER_PAY = "member_pay";

	public static String ACTION_SHOP_UNPAY = "shop_unpay";
	public static String ACTION_SHOP_PREPAY = "shop_prepay";
	public static String ACTION_SHOP_PAY = "shop_pay";

	private String intentAction;

	private View pickAndComentLayout;
	private View waitLayout;
	private View LogisLayout;
	private SumaryView sumaryView;
	private View payWayLayout;
	private Button btnMyComment;

	private shopQingListView qingListView;
	private Button btnPick;

	private ShopTypeItem itemRoom;
	private ShopTypeItem itemTong;
	private ShopTypeItem itemPos;
	private ShopTypeItem itemThird;
	HeadBar headBar;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		activity = this;
		setContentView(R.layout.activity_unpayment_balance);

		headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("报单详情");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		shopList = (shopListView) findViewById(R.id.Shop_List);
		// shopList.bindData(ReapActivity.order.getProductlist());
		tvMember = (TextView) findViewById(R.id.Tv_Member);
		tvReceiver = (TextView) findViewById(R.id.Tv_Receiver);
		tvAddres = (TextView) findViewById(R.id.Tv_Address);
		tvCode = (TextView) findViewById(R.id.Tv_Order_Code);
		TvUserCode = (TextView) findViewById(R.id.Tv_UserCode);
		// sumaryView.tvSummaryPrice.setText(price);
		// sumaryView.tvSumamryPV.setText(pv);
		tvLogiscs = (TextView) findViewById(R.id.Tv_Logistics);
		tvLogiscsNum = (TextView) findViewById(R.id.Tv_Logistice_Num);
		findViewById(R.id.Btn_Comment).setOnClickListener(this);

		pickAndComentLayout = (View) findViewById(R.id.Layout_Opr);
		waitLayout = (View) findViewById(R.id.Layout_Wait);
		LogisLayout = (View) findViewById(R.id.Layout_Logis);
		btnComment = (Button) findViewById(R.id.Btn_Comment);
		sumaryView = (SumaryView) findViewById(R.id.Sumary_Bottom);
		payWayLayout = (View) findViewById(R.id.Layout_Pay_Way);
		btnMyComment = (Button) findViewById(R.id.Btn_My_Comment);

		qingListView = (shopQingListView) findViewById(R.id.Shop_Qing_List);
		btnPick = (Button) findViewById(R.id.Btn_Pick);
		btnPick.setOnClickListener(this);
		initData();
		initItem();
	}

	public void initItem() {
		itemRoom = (ShopTypeItem) findViewById(R.id.Item_Room);
		itemTong = (ShopTypeItem) findViewById(R.id.Item_TONG);
		itemPos = (ShopTypeItem) findViewById(R.id.Item_POS);
		itemThird = (ShopTypeItem) findViewById(R.id.Item_Third);
		itemRoom.unselected();
		itemTong.selected();
		itemPos.unselected();
		itemThird.unselected();
	}

	public void initData() {
		Intent intent = getIntent();
		if (intent == null)
			return;
		intentAction = intent.getAction();
		ProgressDialogUtil.show(this, "提示", "正在加载数据", true, true);

		if (getIntent().getExtras() != null) {
			form = (CustomForm) getIntent().getExtras().get("from");
			BaseRequest requert = activity
					.createRequest(CustomFormService.FORM_DETAIL);// action名称
			if (form.getUserid() != null) {
				String userid = AESOperator.getInstance().encrypt(
						form.getUserid());
				form.setUserid(userid);
			}
			requert.add("userid", form.getUserid());
			requert.add("querytype", "0");
			if (intentAction.equals(ACTION_SHOP_PAY)) {
				requert.add("showid", "1");
			}
			requert.add("orderid", form.getBalanceid());
			new FormAsyncTask(this, null).execute(requert);
		}

		if (form.getShopno() != null) {
			order = new TransOrder();
			order.setPaytype("1");
			order.setStoreid(form.getShopno());
			orderCode = form.getBalanceno();
		}
		if (intentAction.equals(ACTION_MEMBER_PAY)) {
			headBar.setTitle("已支付报单详情");
			pickAndComentLayout.setVisibility(View.GONE);
			waitLayout.setVisibility(View.GONE);
			LogisLayout.setVisibility(View.VISIBLE);
			btnComment.setVisibility(View.GONE);
			sumaryView.setVisibility(View.GONE);
			payWayLayout.setVisibility(View.GONE);
			btnMyComment.setVisibility(View.VISIBLE);
			qingListView.setVisibility(View.GONE);
			bindMemberPick();
		} else if (intentAction.equals(ACTION_MEMBER_UNPAY)) {
			headBar.setTitle("未支付报单详情");
			pickAndComentLayout.setVisibility(View.GONE);
			waitLayout.setVisibility(View.VISIBLE);
			LogisLayout.setVisibility(View.GONE);
			btnComment.setVisibility(View.GONE);
			payWayLayout.setVisibility(View.GONE);
			btnMyComment.setVisibility(View.GONE);
			sumaryView.setVisibility(View.GONE);
			qingListView.setVisibility(View.GONE);
		} else if (intentAction.equals(ACTION_SHOP_PAY)) {
			headBar.setTitle("已支付报单详情");
			pickAndComentLayout.setVisibility(View.VISIBLE);
			waitLayout.setVisibility(View.GONE);
			LogisLayout.setVisibility(View.VISIBLE);
			btnComment.setVisibility(View.VISIBLE);
			payWayLayout.setVisibility(View.GONE);
			btnMyComment.setVisibility(View.GONE);
			btnComment.setOnClickListener(this);
			qingListView.setVisibility(View.VISIBLE);
			sumaryView.setVisibility(View.GONE);
			if (form.getIsshow() != null) {
				if (form.getIsshow().equals("0")) {
					btnPick.setVisibility(View.GONE);
				} else {
					btnPick.setVisibility(View.VISIBLE);

				}
			}
		} else if (intentAction.equals(ACTION_SHOP_PREPAY)) {
			headBar.setTitle("代支付报单详情");
			pickAndComentLayout.setVisibility(View.GONE);
			waitLayout.setVisibility(View.GONE);
			LogisLayout.setVisibility(View.GONE);
			btnComment.setVisibility(View.GONE);
			payWayLayout.setVisibility(View.VISIBLE);
			btnMyComment.setVisibility(View.GONE);
			sumaryView.setVisibility(View.VISIBLE);
			qingListView.setVisibility(View.GONE);
		} else if (intentAction.equals(ACTION_SHOP_UNPAY)) {
			headBar.setTitle("未支付报单详情");
			sumaryView.setVisibility(View.VISIBLE);
			pickAndComentLayout.setVisibility(View.GONE);
			waitLayout.setVisibility(View.GONE);
			LogisLayout.setVisibility(View.GONE);
			btnComment.setVisibility(View.GONE);
			payWayLayout.setVisibility(View.VISIBLE);
			btnMyComment.setVisibility(View.GONE);
			qingListView.setVisibility(View.GONE);

		}

		sumaryView.btnSumary.setOnClickListener(this);
	}

	public void bindMemberPick() {
		btnMyComment.setOnClickListener(this);
	}

	private void shopPay() {
		ProgressDialogUtil.show(this, "提示", "正在支付", true, true);
		BaseRequest baseRequest = createRequest(CustomFormService.FORM_PAY);
		User u = getUser();
		baseRequest.add("userid", getAesUserName());
		baseRequest.add("storeid", form.getShopno());
		baseRequest.add("runno", form.getBalanceno());
		baseRequest.add("paytype", "1");
		new FormAsyncTask(this, null).execute(baseRequest);
	}

	private void memberPay() {
		User u = getUser();
		ProgressDialogUtil.show(this, "提示", "正在支付", true, true);
		BaseRequest baseRequest = createRequest(ShopService.ORDER_PAY);
		baseRequest.add("userid", form.getUserid());
		baseRequest.add("storeid", form.getShopno());
		baseRequest.add("runno", form.getBalanceno());
		baseRequest.add("zhifutype", (u.getLogintype() + 1) + "");
		baseRequest.add("paytype", "1");
		new ShopAsyncTask(this).execute(baseRequest);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent;
		Bundle bundle;
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;

		case R.id.Btn_Sumary:
			// Intent intent = new Intent(this, ReapActivity.class);
			// startActivity(intent);

			builder = new Builder(this);
			builder.setMessage("确认支付？");
			builder.setTitle("提示");
			builder.setPositiveButton("确认",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (intentAction.equals(ACTION_SHOP_PREPAY)) {
								shopPay();
							} else if (intentAction.equals(ACTION_MEMBER_PAY)
									|| intentAction.equals(ACTION_SHOP_UNPAY)) {
								memberPay();
							}
						}
					});
			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// activity.finish();

						}
					});
			builder.show();

			break;
		case R.id.Btn_Comment:
			if (fomDetail != null) {
				intent = new Intent(this, CommentActivity.class);
				intent.setAction(CommentActivity.ACTION_COMENT);
				bundle = new Bundle();
				bundle.putSerializable(CommentActivity.BUNDLE_COMMENT,
						fomDetail);
				intent.putExtras(bundle);
				startActivity(intent);
			}
			break;
		case R.id.Btn_Pick:

			builder = new Builder(this);
			builder.setMessage("确认提货?");
			builder.setTitle("提示");
			builder.setPositiveButton("确认",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {

							if (fomDetail != null) {
								ProgressDialogUtil.show(activity, "提示",
										"数据加载中", true, true);

								BaseRequest baseRequest = activity
										.createRequestWithUserId(CustomFormService.FORM_Sure);// action名称
								baseRequest.add("balanceid",
										fomDetail.getOrderid());
								baseRequest.add("balanceno",
										fomDetail.getOrdercode());
								new FormAsyncTask(activity, null)
										.execute(baseRequest);
							}
						}
					});
			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// activity.finish();

						}
					});
			builder.show();

			break;
		case R.id.Btn_My_Comment:
			intent = new Intent(this, CommentActivity.class);
			intent.setAction(CommentActivity.ACTION_COMENT);
			bundle = new Bundle();
			bundle.putSerializable(CommentActivity.BUNDLE_COMMENT, fomDetail);
			intent.putExtras(bundle);
			startActivity(intent);
			break;

		}

	}

	public void sumBottomView(List<Product> products) {

		float sumPrice = 0;
		float sumPv = 0;
		if (products != null) {
			for (int i = 0; i < products.size(); i++) {
				Product product = products.get(i);
				float price = Float.parseFloat(product.getPrice());
				float pv = Float.parseFloat(product.getPv());
				sumPrice += price * product.getMun();
				sumPv += pv * product.getMun();

			}
		} else {
			return;
		}

		TextView TvSumPrice = (TextView) findViewById(R.id.Tv_Sumamry_Number);
		TextView TvSumPv = (TextView) findViewById(R.id.Tv_Sumamry_Pv);

		DecimalFormat fnum = new DecimalFormat("##0.00");
		TvSumPrice.setText("￥" + fnum.format(sumPrice));
		TvSumPv.setText("" + fnum.format(sumPv) + "pv");
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

			if (fomDetail != null) {
				sumBottomView(fomDetail.getOderprlist());
			} else {
				return;
			}
			bindFrom(fomDetail);
			if (intentAction.equals(ACTION_SHOP_PAY)) {
				qingListView.bindData(fomDetail.getShipmentslist());

			}

			break;
		case CustomFormService.FORM_Sure:
			// if(viewCommonResponse.getMsgCode()==0){
			//
			// }
			BalanceActivity.finish = 0;
			PaymentFragment.needUpdate = true;
			showToast(viewCommonResponse.getMessage());
			UnpaymentFragment.needUpdate = true;
			PaymentFragment.needUpdate = true;
			PrepaymentFragment.needUpdate = true;
			break;
		case CustomFormService.FORM_PAY:
			showToast(viewCommonResponse.getMessage());
			BalanceActivity.finish = 0;
			UnpaymentFragment.needUpdate = true;
			PaymentFragment.needUpdate = true;
			PrepaymentFragment.needUpdate = true;
			break;
		case ShopService.ORDER_PAY:
			BalanceActivity.finish = 0;
			showToast(viewCommonResponse.getMessage());
			PrepaymentFragment.needUpdate = true;
			UnpaymentFragment.needUpdate = true;
			PaymentFragment.needUpdate = true;
			break;
		}
	}

	public void bindFrom(FormDetail form) {

		if (form == null)
			return;
		tvCode.setText("报单编号：" + form.getOrdercode());
		if (form.getLogistics() != null) {
			tvLogiscs.setText(form.getLogistics());
		}
		if (form.getLogiscode() != null) {
			tvLogiscsNum.setText(form.getLogiscode());
		}

		if (form.getUsername() != null) {
			tvMember.setText("报单人:" + form.getUsername() + "  "
					+ form.getReceiptusercall());
		}
		if (form.getUserid() != null) {
			TvUserCode.setText("会员卡号:" + form.getUserid());
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
