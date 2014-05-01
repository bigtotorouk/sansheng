package com.activity.shop.car;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.CommonActivity;
import com.activity.shop.address.ReapActivity;
import com.activity.shop.detail.ShopDetailActivity;
import com.activity.shop.sumary.SumaryActivity;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Brand;
import com.sansheng.model.FuxiaoPool;
import com.sansheng.model.MuilPorduct;
import com.sansheng.model.Product;
import com.sansheng.model.TransOrder;
import com.util.ProgressDialogUtil;
import com.view.BtnTab;
import com.view.HeadBar;
import com.view.TabController;
import com.view.HeadBar.BtnType;
import com.view.SumaryView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-1 下午10:12:02 declare:
 */
public class ShopCarActivity extends CommonActivity implements OnClickListener {

	private ListView lvShopCar;

	private ShopCarAdapter shopCarFuxiaoAdapter;
	private ShopCarAdapter shopCarLingShouAdapter;
	/** 0 nommal 1edit mode **/
	private int mode = 0;
	HeadBar headBar;
	SumaryView sumaryView;
	private CommonActivity activity;

	public static boolean needReersh = true;
	private TextView TvSumPrice;
	private TextView TvSumPv;
	List<Product> fuproductsproducts;
	List<Product> lingshou;
	private int currentMode = 0;
	private TabController tabController;
	private View layout_alert;
	private Button btnBackShop;
	private TextView tvCarAlert;
	ShopService shopService;
	BtnTab tabHome, tabRoom;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_shop_car);
		headBar = (HeadBar) findViewById(R.id.Head_Bar);
		activity = this;
		headBar.setTitle("购物车");
		headBar.setRightType(BtnType.image);
		headBar.setRightImg(R.drawable.ico_edit_unpress);

		headBar.setWidgetClickListener(this);

		tvCarAlert = (TextView) findViewById(R.id.Tv_Car_alert);
		btnBackShop = (Button) findViewById(R.id.Btn_Back_Shop);
		btnBackShop.setOnClickListener(this);
		layout_alert = (View) findViewById(R.id.Layout_alert);

		TvSumPrice = (TextView) findViewById(R.id.Tv_Sumamry_Number);
		TvSumPv = (TextView) findViewById(R.id.Tv_Sumamry_Pv);
		lvShopCar = (ListView) findViewById(R.id.Lv_Shop);

		LayoutInflater layoutInflater = getLayoutInflater();

		sumaryView = (SumaryView) findViewById(R.id.SS);
		sumaryView.tvSummaryPrice = (TextView) sumaryView
				.findViewById(R.id.Tv_Sumamry_Number);
		sumaryView.tvSumamryPV = (TextView) sumaryView
				.findViewById(R.id.Tv_Sumamry_Pv);
		sumaryView.btnSumary = (Button) sumaryView
				.findViewById(R.id.Btn_Sumary);
		shopCarFuxiaoAdapter = new ShopCarAdapter(this);
		shopCarLingShouAdapter = new ShopCarAdapter(this);
		shopCarLingShouAdapter.setCurrentMode(2);
		lvShopCar.setAdapter(shopCarLingShouAdapter);
		// shopCarAdapter.setProducts(getTempData());
		lvShopCar.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Product product = shopCarFuxiaoAdapter.getProducts().get(
				// position);
				// Intent intent = new Intent(activity,
				// ShopDetailActivity.class);
				// Bundle bundle = new Bundle();
				// Brand brand = new Brand();
				// brand.setId(product.getPid());
				// bundle.putSerializable(ShopDetailActivity.INTNET_PRODUCT,
				// brand);
				// intent.putExtras(bundle);
				// activity.startActivity(intent);
			}
		});

		sumaryView.btnSumary.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (currentMode == 0)
					if (shopCarLingShouAdapter.getProducts() == null
							|| shopCarLingShouAdapter.getProducts().size() == 0) {
						Toast.makeText(activity, "购物车为空 无法结算",
								Toast.LENGTH_LONG).show();
						layout_alert.setVisibility(View.VISIBLE);
						tvCarAlert.setText("您的购物车还没有复消商品");
						return;
					} else {
						layout_alert.setVisibility(View.GONE);
					}
				if (currentMode == 1) {
					if (shopCarFuxiaoAdapter.getProducts() == null
							|| shopCarFuxiaoAdapter.getProducts().size() == 0) {
						Toast.makeText(activity, "购物车为空 无法结算",
								Toast.LENGTH_LONG).show();

						return;
					}
				}

				Intent intent = new Intent(activity, ReapActivity.class);
				intent.putExtra(SumaryActivity.INTENT_PRICE,
						sumaryView.tvSummaryPrice.getText().toString());
				intent.putExtra(SumaryActivity.INTENT_PV,
						sumaryView.tvSumamryPV.getText().toString());
				TransOrder order = new TransOrder();
				if (currentMode == 0) {
					order.setProductlist(lingshou);
					order.setOrdertype("2");
				} else if (currentMode == 1) {
					order.setProductlist(fuproductsproducts);
					order.setOrdertype("4");
				}
				// order.setProductlist(shopCarFuxiaoAdapter.getProducts());
				order.setTotalpv(getSumPv());
				order.setTotalamt(getSumPrice());
				order.setUsername(getUserName());
				order.setUbianhao(getUserId());
				order.setSysflag("1");
				intent.putExtra("order", order);
				ReapActivity.needRefersh = true;
				activity.startActivity(intent);

				sumaryView.btnSumary.setClickable(false);
			}
		});
		tabHome = (BtnTab) findViewById(R.id.Btn_Fu);
		tabRoom = (BtnTab) findViewById(R.id.Btn_Lin);
		tabController = new TabController();
		tabController.addTab(tabRoom);
		tabController.addTab(tabHome);
		tabHome.setOnClickListener(this);
		tabRoom.setOnClickListener(this);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (needReersh == true) {
			iniFuxiaoData();
		}
		sumaryView.btnSumary.setClickable(true);
	}

	public List<Product> getTempData() {
		List<Product> products = new ArrayList<Product>();
		for (int i = 0; i < 30; i++) {
			Product product = new Product();
			product.setName("舒伯赖氨基酸洁面皂");
			product.setNumber("1");
			product.setPrice("120");
			product.setPv("89");
			products.add(product);
		}
		return products;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Img_Right:
			if (mode == 0) {
				headBar.setRightImg(R.drawable.navi_del);
				if (currentMode == 0) {
					shopCarLingShouAdapter.setEidtMode();
				} else {
					shopCarFuxiaoAdapter.setEidtMode();
				}

				mode = 1;
			} else {
				headBar.setRightImg(R.drawable.ico_edit_unpress);

				if (currentMode == 0) {
					shopCarLingShouAdapter.setNomlMode();
				} else {
					shopCarFuxiaoAdapter.setNomlMode();
				}
				mode = 0;
				AlertDialog.Builder builder = new Builder(activity);
				builder.setMessage("确认删除该商品？");
				builder.setTitle("提示");
				builder.setPositiveButton("确认",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								delete(shopCarLingShouAdapter.getStrSelected());
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
			break;

		case R.id.Btn_Back:
			finish();

			break;

		case R.id.Btn_Fu:
			backModel();
			tabController.selected(1);
			lvShopCar.setAdapter(shopCarFuxiaoAdapter);
			currentMode = 1;
			shopCarLingShouAdapter.setCurrentMode(4);
			sum(fuproductsproducts);
			checkShop();
			if (fuproductsproducts != null) {
				sumaryView.btnSumary.setText("结算(" + fuproductsproducts.size()
						+ ")");
			} else {
				sumaryView.btnSumary.setText("结算 ");
			}
			break;
		case R.id.Btn_Lin:
			backModel();
			tabController.selected(0);
			lvShopCar.setAdapter(shopCarLingShouAdapter);
			currentMode = 0;
			shopCarLingShouAdapter.setCurrentMode(2);
			sum(lingshou);
			checkShop();
			if (lingshou != null) {
				sumaryView.btnSumary.setText("结算  (" + lingshou.size() + ")");
			} else {
				sumaryView.btnSumary.setText("结算 ");
			}
			break;
		case R.id.Btn_Back_Shop:
			finish();
			break;
		}
	}

	private void delete(final String str) {
		ProgressDialogUtil.show(activity, "提示", "正在删除", true, true);
		new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				Map<String, String> p = new HashMap<String, String>();
				p.put("userid", activity.getOrderUserId());
				p.put("cartid", str);
				p.put("clear", "0");
				p.put("ordertype", shopCarLingShouAdapter.getCurrentMode() + "");
				ViewCommonResponse resp = shopService.deleteShop(p);
			}
		}.start();
		ProgressDialogUtil.close();
	}

	private void backModel() {
		mode = 0;
		shopCarLingShouAdapter.setNomlMode();
		shopCarFuxiaoAdapter.setNomlMode();
		headBar.setRightImg(R.drawable.ico_edit_unpress);
	}

	public void checkShop() {
		if (currentMode == 0) {
			if (shopCarLingShouAdapter.getProducts() == null
					|| shopCarLingShouAdapter.getProducts().size() == 0) {

				layout_alert.setVisibility(View.VISIBLE);
				tvCarAlert.setText("您的购物车还没有零售商品");
				return;
			} else {
				layout_alert.setVisibility(View.GONE);
			}

		}

		if (currentMode == 1) {
			if (shopCarFuxiaoAdapter.getProducts() == null
					|| shopCarFuxiaoAdapter.getProducts().size() == 0) {

				layout_alert.setVisibility(View.VISIBLE);
				tvCarAlert.setText("您的购物车还没有复消商品");
				return;
			} else {
				layout_alert.setVisibility(View.GONE);
			}
		}

	}

	public void iniFuxiaoData() {
		ProgressDialogUtil.show(this, "提示", "正在加载数据", true, true);
		BaseRequest request = createRequest(ShopService.SHOP_CAR_FUXIAO_LIST);
		request.add("userid", getOrderUserId());
		request.add("ordertype", "" + 4);
		new ShopAsyncTask(activity).execute(request);

		ProgressDialogUtil.show(this, "提示", "正在加载数据", true, true);

		BaseRequest requestLingshiou = createRequest(ShopService.SHOP_CAR_LINGSHOU_LIST);
		requestLingshiou.add("userid", getOrderUserId());
		requestLingshiou.add("ordertype", "" + 2);
		new ShopAsyncTask(activity).execute(requestLingshiou);
	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		switch (action) {
		case ShopService.SHOP_CAR_FUXIAO_LIST:
			ProgressDialogUtil.close();
			fuproductsproducts = (List<Product>) viewCommonResponse.getData();
			shopCarFuxiaoAdapter.setProducts(fuproductsproducts);
			sum(fuproductsproducts);
			checkShop();
			break;

		case ShopService.SHOP_CAR_LINGSHOU_LIST:
			ProgressDialogUtil.close();
			lingshou = (List<Product>) viewCommonResponse.getData();
			shopCarLingShouAdapter.setProducts(lingshou);
			sum(lingshou);
			checkShop();
			break;
		case ShopService.SHOP_CAR_LIST_EDIT:
			List<Product> ps = (List<Product>) viewCommonResponse.getData();
			List<Product> dataProduct = shopCarFuxiaoAdapter.getProducts();
			for (int i = 0; i < dataProduct.size(); i++) {
				Product dp = dataProduct.get(i);
				Product product = shopCarFuxiaoAdapter.findById(dp.getId());
				product.setMun(dp.getMun());
			}

			shopCarFuxiaoAdapter.notifyDataSetChanged();
			break;

		}
		if (lingshou != null) {
			if (lingshou.size() != 0)
				tabRoom.setText("零售  (" + lingshou.size() + ")");
			if (currentMode == 0) // 零售
				sumaryView.btnSumary.setText("结算  (" + lingshou.size() + ")");
		} else {
			sumaryView.btnSumary.setText("结算 ");
		}
		if (fuproductsproducts != null) {
			if (fuproductsproducts.size() != 0)
				tabHome.setText("复消  (" + fuproductsproducts.size() + ")");
		}
	}

	public void sum(List<Product> products) {

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
		}
		DecimalFormat fnum = new DecimalFormat("##0.00");
		TvSumPrice.setText("￥" + fnum.format(sumPrice));
		saveSumPrice(fnum.format(sumPrice));
		saveSumPv("" + fnum.format(sumPv));
		TvSumPv.setText("" + fnum.format(sumPv));
	}

}
