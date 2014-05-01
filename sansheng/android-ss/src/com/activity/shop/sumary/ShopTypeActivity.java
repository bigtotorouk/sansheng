package com.activity.shop.sumary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.activity.CommonActivity;
import com.activity.shop.ShopActivity;
import com.activity.shop.address.ReapActivity;
import com.http.BaseRequest;
import com.http.SystemService;
import com.http.ViewCommonResponse;
import com.http.task.SystemAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.TransOrder;
import com.sansheng.model.User;
import com.util.AESOperator;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.ShopTypeItem;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-7 下午3:31:04 declare:
 */
public class ShopTypeActivity extends CommonActivity implements OnClickListener {

	public static final String INTENT_PRICE = "price";
	public static final String INTENT_PV = "pv";

	private ShopTypeItem saleItem;
	private ShopTypeItem pvItem;
	private int type = 0;
	TransOrder order;
	EditText etUser;
	Button btnOrder;
	String userId;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_shoptype);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("在线购物");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);
		etUser = (EditText) findViewById(R.id.Et_UserId);
		etUser.setVisibility(View.INVISIBLE);
		btnOrder = (Button) findViewById(R.id.Btn_Order);
		btnOrder.setOnClickListener(this);
		saleItem = (ShopTypeItem) findViewById(R.id.Item_Sale);
		pvItem = (ShopTypeItem) findViewById(R.id.Item_Pv);
		pvItem.unselected();

		saleItem.setOnClickListener(this);
		pvItem.setOnClickListener(this);

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

		case SystemService.SYS_CHECK:
			if (viewCommonResponse.getMsgCode() == 0) {
				saveOrderUserId(userId);
				String userName = (String) viewCommonResponse.getData();
				saveOrderUserName(userName);
				Intent i = new Intent(this, ShopActivity.class);
				startActivity(i);
				finish();
			} else {
				showToast(viewCommonResponse.getMessage());
			}

			// ProgressDialogUtil.show(this, "提示", "", true, true);

			break;

		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;
		case R.id.Item_Sale:
			etUser.setVisibility(View.INVISIBLE);
			saleItem.selected();
			pvItem.unselected();
			type = 0;
			break;
		case R.id.Item_Pv:
			etUser.setVisibility(View.VISIBLE);
			saleItem.unselected();
			pvItem.selected();
			type = 1;
			break;
		case R.id.Btn_Sumary:
			Intent intent = new Intent(this, ReapActivity.class);
			intent.putExtra("order", order);
			startActivity(intent);
			break;
		case R.id.Btn_Order:

			if (type == 1) {
				if (etUser.getText().toString().equals("")) {
					showToast("不能为空");
				} else {
					saveLoginType("0");
					String content = etUser.getText().toString();
					userId = AESOperator.getInstance().encrypt(content);
					BaseRequest requert = createRequest(SystemService.SYS_CHECK);
					requert.add("userid", userId);
					new SystemAsyncTask(this, null).execute(requert);
					ProgressDialogUtil.show(this, "提示", "正在加载数据", true, true);

				}
			} else if (type == 0) {
				saveLoginType("1");
				saveOrderUserId(getAesUserName());
				User u = getUser();
				saveOrderUserName(u.getUsername());
				Intent i = new Intent(this, ShopActivity.class);
				startActivity(i);
				finish();

			}
			break;
		}

	}
}
