package com.activity.shop.home;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.activity.CommonActivity;
import com.activity.company.InfoDetailActivity;
import com.lekoko.sansheng.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.sansheng.model.Adverst;
import com.util.AnimateFirstDisplayListener;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-17 上午11:47:22 declare:
 */
public class AdverstAdapter extends PagerAdapter {

	private List<Adverst> ads;
	private LayoutInflater layoutInflater;
	private View currentView;
	public CommonActivity activity;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private DisplayImageOptions options;
	ImageLoader imageLoader;
	public ViewPager viewPager;

	public AdverstAdapter(CommonActivity activity, List<Adverst> ns) {
		ads = ns;
		this.activity = activity;
		layoutInflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		options = activity.options;
		imageLoader = activity.imageLoader;
	}

	public List<Adverst> getNews() {
		return ads;
	}

	public void setNews(List<Adverst> news) {
		this.ads = news;
	}

	@Override
	public int getCount() {
		if (ads == null) {
			return 0;
		} else {
			return ads.size();
		}
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		currentView = (View) object;
		return view.equals(object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		final int p = position;
		final Adverst ad = ads.get(position);

		View view = (View) layoutInflater.inflate(
				R.layout.layout_shop_ad_banner, null);
		 		((ViewPager) container).addView(view, 0);
		 		
		 	
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e("debug", "view  onclick");
			}
		});

		ImageView imgAd = (ImageView) view.findViewById(R.id.img_ad);
		if (ad.getImg() != null) {
			imageLoader.displayImage(ad.getImg(), imgAd, options,
					animateFirstListener);
		}
		return view;

	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);

	}

	// public void update() {
	// for (int i = 0; i < ads.size(); i++) {
	// Adverst adverst = ads.get(i);
	// View view = viewPager.findViewWithTag(adverst.getTitle());
	// ImageView imgAd = (ImageView) view.findViewById(R.id.img_ad);
	//
	// imageLoader.displayImage(adverst.getImg(), imgAd, options,
	// animateFirstListener);
	// view.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	//
	// }
	// });
	// }
	// }

	// @Override
	// public int getItemPosition(Object object) {
	// return POSITION_NONE;
	// }
	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	public List<Adverst> getAds() {
		return ads;
	}

	public void setAds(List<Adverst> ads) {
		this.ads = ads;
	}
	
}
