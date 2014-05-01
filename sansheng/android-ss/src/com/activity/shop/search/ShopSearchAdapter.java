package com.activity.shop.search;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.shop.detail.ShopDetailActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.Product;
import com.view.ShopEditDialog;
import com.view.ShopEditDialog.onDissmissListner;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-4 下午5:23:48 declare:
 */
public class ShopSearchAdapter extends BaseAdapter {

	private CommonActivity activity;
	private List<Product> products;
	private int mode = 0;

	private LayoutInflater layoutInflater;
	private ShopEditDialog shopEditDialog;

	public ShopSearchAdapter(CommonActivity context) {
		this.activity = context;
		shopEditDialog = new ShopEditDialog(context);
		layoutInflater = (LayoutInflater) context.getLayoutInflater();
	}

	@Override
	public int getCount() {
		if (products == null) {
			return 0;
		} else {
			return products.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_shop_search_item, null);
		}
		bindView(convertView, position);
		return convertView;
	}

	public void bindView(View v, final int position) {
		final Product product = products.get(position);
		EditText etNumber = (EditText) v.findViewById(R.id.Et_Number);
		ImageButton btnDelete = (ImageButton) v.findViewById(R.id.Btn_Delete);
		v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, ShopDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable(ShopDetailActivity.INTNET_PRODUCT,
						product);
				intent.putExtras(bundle);
				activity.startActivity(intent);
			}
		});
	}

	public void bindView(View view, Product product) {

	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setEidtMode() {
		mode = 1;
		notifyDataSetChanged();

	}

	public void setNomlMode() {
		mode = 0;
		notifyDataSetChanged();
	}

}
