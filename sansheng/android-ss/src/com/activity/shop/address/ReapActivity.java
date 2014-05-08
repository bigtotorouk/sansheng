package com.activity.shop.address;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.shop.car.ShopCarActivity;
import com.activity.shop.payment.PaymentActivity;
import com.google.gson.Gson;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Address;
import com.sansheng.model.Room;
import com.sansheng.model.TransOrder;
import com.sansheng.model.User;
import com.util.DateKeeper;
import com.util.ProgressDialogUtil;
import com.view.BtnTab;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.SumaryView;
import com.view.TabController;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-7 下午10:07:10 declare:
 */
public class ReapActivity extends CommonActivity implements OnClickListener {

	private TabController tabController;

	private ListView lvAddress;
	AddressAdapter addressAdapter;
	private int currentMode = 0;
	private CommonActivity commonActivity;
	List<Address> adds;
	RoomAdapter roomAdapter;

	public static boolean needRefersh = true;

	public static TransOrder order;
	Address defaultAdds = null;
	Room room;

	private static String payType = "1";
	SumaryView sumaryView;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_address);

		commonActivity = this;
		BtnTab tabHome = (BtnTab) findViewById(R.id.Btn_Home);
		BtnTab tabRoom = (BtnTab) findViewById(R.id.Btn_Room);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("收货地址");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		TextView TvSumPrice = (TextView) findViewById(R.id.Tv_Sumamry_Number);
		TextView TvSumPv = (TextView) findViewById(R.id.Tv_Sumamry_Pv);
		TvSumPrice.setText("￥" + getSumPrice());
		TvSumPv.setText(getSumPv());

		tabController = new TabController();
		tabController.addTab(tabHome);
		tabController.addTab(tabRoom);

		tabHome.setOnClickListener(this);
		tabRoom.setOnClickListener(this);

		lvAddress = (ListView) findViewById(R.id.Lv_Address);
		lvAddress.setDivider(null);
		addressAdapter = new AddressAdapter(this);
		addressAdapter.setAddresses(getData1());
		lvAddress.setAdapter(addressAdapter);
		lvAddress.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Address address = addressAdapter.getAddresses().get(position);
				if (address.getType() == 0) {
					Intent intent = new Intent(commonActivity,
							AddressActivity.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(commonActivity,
							RoomAddressActivity.class);
					startActivity(intent);
				}
			}
		});

		sumaryView = (SumaryView) findViewById(R.id.Sumary);
		sumaryView.btnSumary.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (currentMode == 1) {

					if (room == null || room.getShopid() == null
							|| room.getShopid().equals("")) {
						Log.e("debug", "please  click");
						showToast("请先添加工作室");
						return;
					} else {
						order.setHometel("");
						order.setFhtype("0");
						order.setMobiletel(room.getShopcall());
						order.setAddress(room.getShopadds());
						order.setReceiver(room.getShopname());
						submitOrder();
						sumaryView.btnSumary.setClickable(false);
					}

				} else {
					ProgressDialogUtil.show(commonActivity, "提示", "正在提交订单",
							true, true);

					order.setHometel("");
					order.setFhtype("1");
					order.setMobiletel(defaultAdds.getCall());
					order.setAddress(defaultAdds.getAdds());
					order.setReceiver(defaultAdds.getName());
					submitOrder();
				}
				// Intent intent = new Intent(commonActivity,
				// PaymentActivity.class);
				// intent.putExtra("order", order);
				// commonActivity.startActivity(intent);
			}
		});

		roomAdapter = new RoomAdapter(this);
		initShopRoom();
		initIntent();
		if (getLoginType().equals("1")) {
			findViewById(R.id.Layout_Head).setVisibility(View.GONE);
			currentMode = 1;
			setRoom();
		} else {
			findViewById(R.id.Layout_Head).setVisibility(View.VISIBLE);
			setHome();
		}

		
		findViewById(R.id.tab1).setOnClickListener(this);
		findViewById(R.id.tab2).setOnClickListener(this);
		findViewById(R.id.tab1).setSelected(true);
	}

	private void initShopRoom() {
		room = (Room) DateKeeper.getData(this, "Room");
		ArrayList<Address> rs = new ArrayList<Address>();
		Address addressRoom = new Address();
		if (room != null) {
			addressRoom.setAdds(room.getShopadds());
			addressRoom.setCall(room.getShopcall());
			addressRoom.setName(room.getShopname());
		}
		try {
			addressRoom.setId(Integer.parseInt(room.getShopid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		addressRoom.setType(2);
		rs.add(addressRoom);

		roomAdapter.setAddresses(rs);
		roomAdapter.notifyDataSetChanged();
	}

	public void inRoom() {
	}

	public void initIntent() {
		order = (TransOrder) getIntent().getExtras().get("order");

		TextView TvSumPrice = (TextView) findViewById(R.id.Tv_Sumamry_Number);
		TextView TvSumPv = (TextView) findViewById(R.id.Tv_Sumamry_Pv);
		TvSumPrice.setText("￥" + getSumPrice());
		TvSumPv.setText(getSumPv());

	}

	public void submitOrder() {
		BaseRequest request = createRequest(ShopService.ORDER_SUBMIT);

		User user = getUser();
		// request.add("userid", getOrderUserId());
		request.add("storeid", order.getStoreid());
		request.add("bianhao", order.getBianhao());
		request.add("ubianhao", getOrderUserId());
		request.add("username", getOrderUserName());
		request.add("fhtype", order.getFhtype());
		request.add("hometel", "");
		request.add("mobiletel", order.getMobiletel());
		request.add("address", order.getAddress());
		request.add("receiver", order.getReceiver());
		request.add("totalamt", getSumPrice());
		request.add("totalpv", getSumPv());
		request.add("sysflag", order.getSysflag());
		request.add("ordertype", order.getOrdertype());
		request.add("paytype", order.getPaytype());
		System.out.print("order" + order);
		Gson gson = new Gson();
		String json = gson.toJson(order.getProductlist());
		request.add("productlist", json);
		System.out.println(order);
		new ShopAsyncTask(this).execute(request);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (needRefersh == true) {
			needRefersh = false;
		}
		initData();
		sumaryView.btnSumary.setClickable(true);
	}

	private void initData() {
		ProgressDialogUtil.show(commonActivity, "提示", "正在加载数据", true, true);

		BaseRequest baseRequest = createRequestWithUserId(ShopService.ADDRESS_LIST);
		baseRequest.add("userid", getUserId());
		baseRequest.add("", "0");
		new ShopAsyncTask(this).execute(baseRequest);
	}

	public List<Address> getData1() {
		return adds;
	}

	public List<Address> getData2() {
		List<Address> addresses = new ArrayList<Address>();
		for (int i = 0; i < 1; i++) {
			Address address = new Address();
			address.setType(1);
			addresses.add(address);
		}
		return addresses;
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
		case ShopService.ADDRESS_LIST:

			initShopRoom();
			adds = (List<Address>) viewCommonResponse.getData();
			room = (Room) DateKeeper.getData(this, "Room");
			if (adds == null) {
				initAddress();
				return;
			}
			int i = 0;
			int count = adds.size() - 1;

			for (i = 0; i < count; i++) {
				Address address = adds.get(i);
				System.out.println(address);
				if (address.getDefaults().equals("1")) {
					defaultAdds = adds.get(i);
				}
			}
			if (room == null) {
				initRoom();
				return;
			}

			Address addressRoom = new Address();
			addressRoom.setAdds(room.getShopadds());
			addressRoom.setCall(room.getShopcall());
			addressRoom.setName(room.getShopname());
			addressRoom.setId(addressRoom.getId());
			addressRoom.setType(2);
			if (defaultAdds == null) {
				defaultAdds = adds.get(0);
			}
			adds = new ArrayList<Address>();
			adds.add(defaultAdds);
			adds.add(addressRoom);

			addressAdapter.setAddresses(adds);
			addressAdapter.notifyDataSetChanged();
			order.setFhtype("0");
			order.setBianhao(room.getShopuserid());
			order.setStoreid(room.getShopid());
			order.setAddress(defaultAdds.getAdds());
			order.setReceiver(defaultAdds.getName());
			order.setMobiletel(defaultAdds.getCall());
			order.setHometel(defaultAdds.getCall());
			order.setPaytype("1");

			break;
		case ShopService.ORDER_SUBMIT:
			ProgressDialogUtil.close();
			ShopCarActivity.needReersh = true;
			String orderCode = (String) viewCommonResponse.getData();
			Intent intent = new Intent(commonActivity, PaymentActivity.class);
			intent.setAction(PaymentActivity.ACTION_SHOP_DAI);
			Address homeAddres = addressAdapter.getHomeAddres();
			Address roomAddress = roomAdapter.addresses.get(0);
			if (payType.equals("1")) {
				order.setHomeAddres(homeAddres);
			} else {
				order.setHomeAddres(roomAddress);
			}

			intent.putExtra("orderCode", orderCode);
			order.setPaytype(payType);
			intent.putExtra("order", order);
			commonActivity.startActivity(intent);
			break;

		}
	}

	public void initAddress() {

		if (commonActivity.getLoginType() != null
				&& commonActivity.getLoginType().equals("2")) {
			return;
		}
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("未设置居家收获地址，请添加");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Intent intent = new Intent(commonActivity,
						EditAddressActivity.class);
				startActivity(intent);
			}
		});
		// builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
		// {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// commonActivity.finish();
		// }
		// });
		AlertDialog alertDialog = builder.show();
		alertDialog.setCancelable(false);
	}

	public void initRoom() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("未设置收获工作室，请添加");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Intent intent = new Intent(commonActivity,
						RoomAddressActivity.class);
				startActivity(intent);
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// commonActivity.finish();

				List<Address> as = new ArrayList<Address>();
				as.add(defaultAdds);
				addressAdapter.setAddresses(as);
				addressAdapter.notifyDataSetChanged();
				finish();
			}
		});
		AlertDialog alertDialog = builder.show();
		alertDialog.setCancelable(false);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		sumaryView.btnSumary.setClickable(false);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();

			break;

		case R.id.Btn_Home:
			setHome();
			currentMode = 0;
			payType = "1";
			break;
		case R.id.Btn_Room:
			setRoom();
			currentMode = 1;
			payType = "0";
			// if (currentMode == 0) {
			//
			// if (room != null) {
			// Address addressRoom = new Address();
			// addressRoom.setAdds(room.getShopadds());
			// addressRoom.setCall(room.getShopcall());
			// addressRoom.setName(room.getShopname());
			// addressRoom.setId(addressRoom.getId());
			// addressRoom.setType(2);
			// List<Address> myadds = new ArrayList<Address>();
			// myadds.add(addressRoom);
			// addressAdapter.setAddresses(myadds);
			// addressAdapter.notifyDataSetChanged();
			// currentMode = 1;
			// }
			// }
			break;
		case R.id.tab1:
			findViewById(R.id.tab1).setSelected(true);
			findViewById(R.id.tab2).setSelected(false);
			setHome();
			currentMode = 0;
			payType = "1";
			break;
		case R.id.tab2:
			findViewById(R.id.tab1).setSelected(false);
			findViewById(R.id.tab2).setSelected(true);
			setRoom();
			currentMode = 1;
			payType = "0";
			break;

		}
	}

	private void setHome() {
		tabController.selected(0);
		currentMode = 0;
		lvAddress.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Address address = addressAdapter.getAddresses().get(position);
				if (address.getType() == 0) {
					Intent intent = new Intent(commonActivity,
							AddressActivity.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(commonActivity,
							RoomAddressActivity.class);
					startActivity(intent);
				}
			}
		});
		lvAddress.setAdapter(addressAdapter);
	}

	private void setRoom() {
		currentMode = 1;
		lvAddress.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(commonActivity,
						RoomAddressActivity.class);
				startActivity(intent);
			}
		});
		tabController.selected(1);
		payType = "0";
		lvAddress.setAdapter(roomAdapter);
		roomAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		needRefersh = false;
	}

	public void updateHome() {

	}

}
