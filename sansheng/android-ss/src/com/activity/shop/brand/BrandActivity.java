package com.activity.shop.brand;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.shop.car.ShopCarActivity;
import com.activity.shop.detail.ShopDetailActivity;
import com.activity.shop.search.SearchActivity;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Brand;
import com.sansheng.model.Product;
import com.view.HeadBar;
import com.view.IconButton;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-1 下午8:24:11 declare:
 */
public class BrandActivity extends CommonActivity implements OnClickListener {

	ListView lvBrand;
	BrandAdapter brandAdapter;
	private CommonActivity activity;
	private IconButton btnSearch;
	private IconButton btnShopCar;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_brand_list);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);

		headBar.setTitle("御坊堂");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		btnSearch = (IconButton) findViewById(R.id.Btn_Search);
		btnShopCar = (IconButton) findViewById(R.id.Btn_Shopp_Car);

		lvBrand = (ListView) findViewById(R.id.Lv_Brand_My);
		LayoutInflater layoutInflater = getLayoutInflater();
		View bottom = (View) layoutInflater
				.inflate(R.layout.layout_empty, null);
		lvBrand.addFooterView(bottom);

		activity = this;
		lvBrand.setDivider(null);
		// lvBrand.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		// Intent i = new Intent(activity, ShopDetailActivity.class);
		// activity.startActivity(i);
		// }
		// });

		brandAdapter = new BrandAdapter(this);
		lvBrand.setAdapter(brandAdapter);

		Brand b = getBrand();
		headBar.setTitle(b.getBrandName());
		BaseRequest baseRequest = createRequest(ShopService.SHOP_BRAND);
		baseRequest.add("bid", "" + b.getBrandid());
		new ShopAsyncTask(activity).execute(baseRequest);

	}

	public Brand getBrand() {
		Intent i = getIntent();
		if (i != null) {
			Bundle b = i.getExtras();
			if (b != null) {
				Brand brand = (Brand) b.get("brand");
				return brand;
			}
		}
		return null;
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();

		for (int i = 0; i < 10; i++) {
			Product product = new Product();
			products.add(product);
		}
		return products;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();

			break;
		case R.id.Btn_Search:
			Intent intentSearch = new Intent(this, SearchActivity.class);
			startActivity(intentSearch);
			break;
		case R.id.Btn_Shopp_Car:
			Intent intentShopCar = new Intent(this, ShopCarActivity.class);
			startActivity(intentShopCar);
		default:
			break;
		}

	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		switch (action) {
		case ShopService.SHOP_BRAND:
			List<Brand> brands = (List<Brand>) viewCommonResponse.getData();
			brandAdapter.setProducts(brands);
			brandAdapter.notifyDataSetChanged();
			break;

		}
	}

}
