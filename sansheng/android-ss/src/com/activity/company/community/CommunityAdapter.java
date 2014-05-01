package com.activity.company.community;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.sansheng.model.LocalInfo;
import com.util.AnimateFirstDisplayListener;

public class CommunityAdapter extends BaseAdapter {

	private List<LocalInfo> localInfos;

	private LayoutInflater layoutInflater;
	ImageLoader imageLoader;
	private DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	public CommunityAdapter(CommonActivity activity) {
		layoutInflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = activity.imageLoader;
		options = activity.options;
	}

	@Override
	public int getCount() {
		if (localInfos == null) {
			return 0;
		} else {
			return localInfos.size();
		}
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	private class ViewHolder {
		public TextView tvTitle;
		public ImageView imgShrinkImg;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LocalInfo localInfo = localInfos.get(position);

		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_company_community, null);
			ViewHolder vHolder = new ViewHolder();
			vHolder.tvTitle = (TextView) convertView
					.findViewById(R.id.Tv_CommunityTitle);
			vHolder.imgShrinkImg = (ImageView) convertView
					.findViewById(R.id.Iv_ShrinkImg);
			convertView.setTag(vHolder);
		}
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		bindView(localInfo, viewHolder);

		return convertView;
	}

	private void bindView(LocalInfo localInfo, ViewHolder viewHolder) {
		if (localInfo.getTitle() != null) {
			viewHolder.tvTitle.setText(localInfo.getTitle());
		}
		String url = localInfo.getNews_simg();
		imageLoader.displayImage(url, viewHolder.imgShrinkImg, options,
				animateFirstListener);
	}

	public List<LocalInfo> getLocalInfos() {
		return localInfos;
	}

	public void setLocalInfos(List<LocalInfo> localInfos) {
		this.localInfos = localInfos;
	}

}