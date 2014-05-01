package com.activity.company.news;

import java.util.List;

import android.app.Activity;
import android.content.Context;
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

public class NewsAdapter extends BaseAdapter {

	private List<LocalInfo> localInfos;

	private LayoutInflater layoutInflater;

	private DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	ImageLoader imageLoader;

	public NewsAdapter(CommonActivity activity) {
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
		public TextView tvInfo;
		public ImageView img;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LocalInfo localInfo = localInfos.get(position);

		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_company_news, null);
			ViewHolder vHolder = new ViewHolder();
			vHolder.tvTitle = (TextView) convertView
					.findViewById(R.id.Tv_Title);
			vHolder.tvInfo = (TextView) convertView.findViewById(R.id.Tv_Info);
			vHolder.img = (ImageView) convertView.findViewById(R.id.Img_News);
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

		if (localInfo.getData() != null) {
			String strcontent = localInfo.getContent();
			if (strcontent.length() >= 30) {
				viewHolder.tvInfo.setText(localInfo.getContent().substring(0,
						30));
			} else {
				viewHolder.tvInfo.setText(localInfo.getContent());
			}
		}

		String url = "http://cloud.yofoto.cn" + localInfo.getNews_simg();
		imageLoader.displayImage(localInfo.getNews_simg(), viewHolder.img,
				options, animateFirstListener);
	}

	public List<LocalInfo> getLocalInfos() {
		return localInfos;
	}

	public void setLocalInfos(List<LocalInfo> localInfos) {
		this.localInfos = localInfos;
	}

}
