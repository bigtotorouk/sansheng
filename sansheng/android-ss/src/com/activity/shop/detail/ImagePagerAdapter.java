package com.activity.shop.detail;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.activity.CommonActivity;

import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImagePagerAdapter extends PagerAdapter {

	private List<String> mPaths = new ArrayList<String>();

	private CommonActivity activity;

	public ImagePagerAdapter(CommonActivity cx) {
		activity = cx;
	}

	public void change(List<String> paths) {
		mPaths = paths;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mPaths.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		// TODO Auto-generated method stub
		return view == (View) obj;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		ImageView imageView = new ImageView(activity);
		imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		activity.imageLoader.displayImage(mPaths.get(position), imageView,
				activity.options);
		((ViewPager) container).addView(imageView, 0);
		return imageView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}