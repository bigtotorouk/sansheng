package com.activity.shop.home;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.shop.brand.BrandActivity;
import com.activity.shop.detail.ShopDetailActivity;
import com.lekoko.sansheng.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.sansheng.model.Brand;
import com.sansheng.model.Product;
import com.util.AnimateFirstDisplayListener;
import com.view.AutoAdjustHeightImageView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 下午2:54:20 declare:
 * 
 */
public class BrandAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private List<Brand> brands;
	private CommonActivity activity;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private DisplayImageOptions options;
	ImageLoader imageLoader;

	public BrandAdapter(CommonActivity activity) {
		this.activity = activity;
		imageLoader = activity.imageLoader;
		options = activity.options;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		if (brands == null) {
			return 0;
		} else {
			return brands.size();
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
			convertView = (View) inflater.inflate(
					R.layout.layout_shop_series_item, null);
		}
		bindView(convertView, position);
		return convertView;
	}

	public void bindView(View view, int position) {

		try {

			final Brand brand = brands.get(position);

			TextView tvTop = (TextView) view.findViewById(R.id.Tv_Top_Brand);

			if (brand.getBrandname() != null) {
				tvTop.setText(brand.getBrandname());
			}
			AutoAdjustHeightImageView imgOne = (AutoAdjustHeightImageView) view
					.findViewById(R.id.Img_Row_1);
			final ImageView imgTwo = (ImageView) view
					.findViewById(R.id.Img_Row_2);
			final ImageView imgThree = (ImageView) view
					.findViewById(R.id.Img_Row_3);
			final View layoutTwo = (View) view.findViewById(R.id.Layout_Two);
			final View layoutThree = (View) view.findViewById(R.id.Layotu_T);
			TextView tvBrandOne = (TextView) view
					.findViewById(R.id.TV_Brand_One);
			TextView tvBrandTwo = (TextView) view
					.findViewById(R.id.TV_Brand_Two);

			if (brand.getBrandimg() != null) {
				try {
					imageLoader.displayImage(brand.getBrandimg(), imgOne,
							options, animateFirstListener);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			final List<Product> products = brand.getProducets();
			if (products != null) {
				if (products.size() >= 1) {
					Product p1 = products.get(0);
					if (p1.getImg() != null) {
						layoutTwo.setVisibility(View.VISIBLE);
						imageLoader.displayImage(p1.getImg(), imgTwo, options,
								animateFirstListener);
					}
					if (p1.getTitle() != null) {
						tvBrandOne.setText(p1.getTitle());
					}
				} else {
					layoutTwo.setVisibility(View.GONE);
				}
				if (products.size() >= 2) {
					Product p2 = products.get(1);
					if (p2.getImg() != null) {
						layoutThree.setVisibility(View.VISIBLE);
						imageLoader.displayImage(p2.getImg(), imgThree,
								options, animateFirstListener);
						tvBrandTwo.setText(p2.getTitle());
					}
				} else {
					layoutThree.setVisibility(View.GONE);
				}

			} else {
				layoutTwo.setVisibility(View.INVISIBLE);
				layoutThree.setVisibility(View.INVISIBLE);
			}
			imgOne.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(activity, BrandActivity.class);
					Bundle b = new Bundle();
					b.putSerializable("brand", brand);
					i.putExtras(b);
					activity.startActivity(i);

				}
			});
			imgTwo.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (products.size() >= 1) {
						Product p = products.get(0);
						Intent i = new Intent(activity,
								ShopDetailActivity.class);
						Bundle b = new Bundle();
						Brand brand = new Brand();
						brand.setId(p.getId());
						b.putSerializable(ShopDetailActivity.INTNET_PRODUCT,
								brand);
						i.putExtras(b);
						Log.e("debug", "" + brand);
						activity.startActivity(i);
					}
				}
			});
			imgThree.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (products.size() >= 2) {
						Product p = products.get(1);
						Intent i = new Intent(activity,
								ShopDetailActivity.class);
						Bundle b = new Bundle();
						Brand brand = new Brand();
						brand.setId(p.getId());
						b.putSerializable(ShopDetailActivity.INTNET_PRODUCT,
								brand);
						i.putExtras(b);
						Log.e("debug", "" + brand);
						activity.startActivity(i);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

}
