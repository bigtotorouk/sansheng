package com.activity.shop.address;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Address;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-8 上午11:18:22 declare:
 */
public class AddressActivity extends CommonActivity implements OnClickListener {
	private ListView lvAddress;

	private AddAddressAdapter adapter;
	private CommonActivity commonActivity;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_add_address);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("收货地址");
		headBar.setRightType(BtnType.image);
		headBar.setRightImg(R.drawable.address_edit);
		headBar.setWidgetClickListener(this);
		lvAddress = (ListView) findViewById(R.id.Lv_Address);
		lvAddress.setDivider(null);
		adapter = new AddAddressAdapter(this);
		// adapter.setAddresses(getData1());
		lvAddress.setAdapter(adapter);

		commonActivity = this;
		// lvAddress.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		//
		// }
		// });
		initData();
	}

	public void initData() {
		ProgressDialogUtil.show(commonActivity, "提示", "正在加载数据", true, true);
		BaseRequest baseRequest = createRequestWithUserId(ShopService.ADDRESS_LIST);
		baseRequest.add("userid", getUserId());
		baseRequest.add("", "0");
		new ShopAsyncTask(this).execute(baseRequest);

	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		switch (action) {
		case ShopService.ADDRESS_DELETE:
			ProgressDialogUtil.close();
			adapter.getAddresses().remove(adapter.curAddress);
			adapter.notifyDataSetChanged();
			break;
		case ShopService.ADDRESS_DEFAULT:
//			ReapActivity.needRefersh = true;
			ProgressDialogUtil.close();
			adapter.setdefault();
			break;

		case ShopService.ADDRESS_LIST:
			ProgressDialogUtil.close();
			List<Address> addresses = (List<Address>) viewCommonResponse
					.getData();
			adapter.setAddresses(addresses);
			adapter.notifyDataSetChanged();
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (data != null) {
			Boolean refersh = data.getBooleanExtra("refersh", false);
			// 根据上面发送过去的请求吗来区别
			switch (requestCode) {
			case 1:
				if (refersh == true) {
					initData();
				}
				break;

			}
		}
	}

	public List<Address> getData1() {
		List<Address> addresses = new ArrayList<Address>();
		for (int i = 0; i < 3; i++) {
			Address address = new Address();
			address.setType(0);
			addresses.add(address);
		}
		return addresses;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();

			break;

		case R.id.Img_Right:
			Intent intent = new Intent(this, EditAddressActivity.class);
			startActivityForResult(intent, 1);
			break;
		}

	}

}
