package com.activity.shop.brand;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.shop.detail.ShopDetailActivity;
import com.lekoko.sansheng.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.sansheng.model.Brand;
import com.util.AnimateFirstDisplayListener;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-1 下午8:35:53 declare:
 */
public class BrandAdapter extends BaseAdapter {

	private LayoutInflater layoutInflater;
	private View currentView;

	public Activity activity;

	private List<Brand> brands;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	private DisplayImageOptions options;
	ImageLoader imageLoader;
	public ViewPager viewPager;

	public BrandAdapter(CommonActivity context) {
		activity = context;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		options = context.options;
		imageLoader = context.imageLoader;
	}

	@Override
	public int getCount() {
		if (brands == null) {
			return 0;
		} else {
			return brands.size() / 2;
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
					R.layout.layout_brand_list, null);
		}

		bindView(convertView, position);
		return convertView;
	}

	public void bindView(View view, int position) {
		final Brand brandone = brands.get(position * 2);
		int position2 = position * 2 + 1;
		if (position2 >= brands.size()) {
			position2 = 0;
		}
		final Brand brandTwo = brands.get(position * 2 + 1);
		RelativeLayout layoutone = (RelativeLayout) view
				.findViewById(R.id.Layout_One);
		RelativeLayout layouttwo = (RelativeLayout) view
				.findViewById(R.id.Layout_Two);

		TextView tvTitleOne = (TextView) view.findViewById(R.id.Tv_Title_One);
		TextView tvPriveOne = (TextView) view.findViewById(R.id.Tv_Price_One);
		TextView tvPvOne = (TextView) view.findViewById(R.id.Tv_Pv_One);
		ImageView imgOne = (ImageView) view.findViewById(R.id.Img_One);

		TextView tvTitleTwo = (TextView) view.findViewById(R.id.Tv_Title_Two);
		TextView tvPriveTwo = (TextView) view.findViewById(R.id.Tv_Price_Two);
		TextView tvPvTwo = (TextView) view.findViewById(R.id.Tv_Pv_Tow);
		ImageView imgTwo = (ImageView) view.findViewById(R.id.Img_Two);

		if (brandone.getTitle() != null) {
			tvTitleOne.setText(brandone.getTitle());
		}
		if (brandone.getPrice() != null) {
			tvPriveOne.setText("￥" + brandone.getPrice());
		}
		if (brandone.getPv() != null) {

			Float pv = Float.parseFloat(brandone.getPv());
			int intpv = pv.shortValue();
			tvPvOne.setText("/" + intpv + "pv");
		}

		imageLoader.displayImage(brandone.getSimg(), imgOne, options,
				animateFirstListener);
		if ((position * 2 + 1) < brands.size()) {
			layouttwo.setVisibility(View.VISIBLE);
			if (brandTwo.getTitle() != null) {
				tvTitleTwo.setText(brandTwo.getTitle());
			}
			if (brandTwo.getPrice() != null) {
				tvPriveTwo.setText("￥" + brandTwo.getPrice());
			}
			if (brandTwo.getPv() != null) {
				Float pv = Float.parseFloat(brandTwo.getPv());
				int intpv = pv.shortValue();
				tvPvTwo.setText("/" + intpv + "pv");
			}
			imageLoader.displayImage(brandTwo.getSimg(), imgTwo, options,
					animateFirstListener);
		} else {
			layouttwo.setVisibility(View.GONE);
		}

		layoutone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Aulayoutoneto-generated method stub
				Intent i = new Intent(activity, ShopDetailActivity.class);
				Bundle b = new Bundle();
				b.putSerializable("brand", brandone);
				i.putExtras(b);
				activity.startActivity(i);

			}
		});

		layouttwo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(activity, ShopDetailActivity.class);
				Bundle b = new Bundle();
				b.putSerializable("brand", brandTwo);
				i.putExtras(b);
				activity.startActivity(i);

			}
		});
	}

	public List<Brand> getProducts() {
		return brands;
	}

	public void setProducts(List<Brand> products) {
		this.brands = products;
	}

}
