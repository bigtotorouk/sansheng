package com.activity.shop.address;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.activity.CommonActivity;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Address;
import com.util.ProgressDialogUtil;
import com.view.CityDialog;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-8 上午11:18:03 declare:
 */
public class EditAddressActivity extends CommonActivity implements
		OnClickListener {

	private static String INTENT_ADDRESS = "address";

	private EditText etName;
	private EditText etPhone;
	private EditText etMail;
	private EditText etRegion;
	private EditText etAddress;
	private Address address;
	private CityDialog cityDialog;
	private CommonActivity activity;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		activity = this;
		setContentView(R.layout.activity_address_edit);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("编辑收获地址");
		headBar.setRightType(BtnType.image);
		headBar.setRightImg(R.drawable.btn_gou_unpress);
		headBar.setWidgetClickListener(this);
		etName = (EditText) findViewById(R.id.Et_Name);
		etPhone = (EditText) findViewById(R.id.Et_Phone);
		etMail = (EditText) findViewById(R.id.Et_Mail);
		etRegion = (EditText) findViewById(R.id.Et_Area);
		etAddress = (EditText) findViewById(R.id.Et_Address);
		etRegion.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cityDialog = new CityDialog(activity);
				cityDialog.etRegion = etRegion;
				cityDialog.show();
			}
		});
		etName.clearFocus();
		etPhone.clearFocus();
		etMail.clearFocus();
		etRegion.clearFocus();
		etAddress.clearFocus();

		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(etName.getWindowToken(), 0);

		Intent i = getIntent();
		if (i != null) {
			Bundle bundle = i.getExtras();
			if (bundle != null) {
				address = (Address) bundle.get("address");
				headBar.setTitle("编辑收货地址");
				bindData(address);
			}

		} else {
			address = null;
			headBar.setTitle("添加收货地址");
		}
	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		switch (action) {
		case ShopService.ADDRESS_ADD:

			Intent mIntent = new Intent();
			mIntent.putExtra("refersh", true);
			// 设置结果，并进行传送
			this.setResult(1, mIntent);
			ProgressDialogUtil.close();
			ReapActivity.needRefersh = true;
			finish();
			break;

		}
	}

	public void bindData(Address address) {
		if (address.getName() != null) {
			etName.setText(address.getName());
		}
		if (address.getCall() != null) {
			etPhone.setText(address.getCall());
		}
		if (address.getCode() != null) {
			etMail.setText(address.getCode());
		}
		if (address.getInfrom() != null) {
			etRegion.setText(address.getInfrom());
		}
		if (address.getAdds() != null) {
			etAddress.setText(address.getAdds());
		}

	}

	public void editAddress(Address addressmodel) {
		BaseRequest baseRequest;
		if (addressmodel == null) {
			ProgressDialogUtil.show(this, "提示", "正在添加地址", true, true);
			baseRequest = createRequestWithUserId(ShopService.ADDRESS_ADD);
		} else {
//			ProgressDialogUtil.show(this, "提示", "正在修改地址", true, true);
			baseRequest = createRequestWithUserId(ShopService.ADDRESS_SUMBIT);
			baseRequest.add("addsid", addressmodel.getId() + "");
		}
		baseRequest.add("name", etName.getText().toString());
		baseRequest.add("call", etPhone.getText().toString());
		baseRequest.add("code", etMail.getText().toString());
		baseRequest.add("infrom", etRegion.getText().toString());
		baseRequest.add("adds", etAddress.getText().toString());

		new ShopAsyncTask(this).execute(baseRequest);
	}

	public boolean valideData() {
		String name = etName.getText().toString();
		String phone = etPhone.getText().toString();
		String mail = etMail.getText().toString();
		String region = etRegion.getText().toString();
		String address = etAddress.getText().toString();

		if (name.equals("")) {
			alert("名称不能位空");
			return false;
		}
		if (phone.equals("")) {
			alert("电话不能位空");
			return false;
		}
		if (mail.equals("")) {
			alert("邮箱不能位空");
			return false;
		}
		if (region.equals("")) {
			alert("区域不能位空");
			return false;
		}
		if (address.equals("")) {
			alert("地址不能位空");
			return false;
		}

		if (phone.matches("\\d*") == false) {
			alert("电话必须位数字");
			return false;
		}
		if (mail.length() > 6) {
			alert("邮箱密码过长");
			return false;
		}
		if (mail.matches("\\d*") == false) {
			alert("邮编必须位数字");
			return false;
		}

		if (name.length() > 10) {
			alert("名字过长");
			return false;
		}
		if (phone.length() > 15) {
			alert("电话过长");
			return false;
		}
		if (phone.length() < 8) {
			alert("电话过短");
			return false;
		}

		return true;

	}

	public void alert(String conten) {
		Toast.makeText(this, conten, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			closeKeyBoard();
			finish();
			break;

		case R.id.Img_Right:
			if (valideData() == true) {
				editAddress(address);
			}
			break;
		}

	}
}
