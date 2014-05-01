package com.activity.shop.detail;

import java.util.List;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.sansheng.model.Adverst;
import com.util.AnimateFirstDisplayListener;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-30 下午9:26:31 declare:
 */
public class GallgerAdapter extends BaseAdapter {

	private LayoutInflater layoutInflater;
	private View currentView;

	public CommonActivity activity;

	private String[] urls;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private DisplayImageOptions options;
	ImageLoader imageLoader;

	public GallgerAdapter(CommonActivity context) {
		activity = context;
		imageLoader = context.imageLoader;
		options = context.options; 
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {

		if (urls == null) {
			return 0;
		} else {
			return urls.length;
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
			Log.e("debug", "getView");
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_shop_gallery_item, null);
			 
		}
		ImageView  imgProduct=(ImageView) convertView.findViewById(R.id.Img_Product);
		if(urls[position]!=null){
			imageLoader.displayImage(urls[position], imgProduct, options,
					animateFirstListener);
		}
		return convertView;
	}

	public String[] getUrls() {
		return urls;
	}

	public void setUrls(String[] urls) {
		this.urls = urls;
	}

}
