package com.activity.company.quality;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.lekoko.sansheng.R.drawable;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.sansheng.model.LocalInfo;
import com.util.AnimateFirstDisplayListener;

public class QualityAdapter extends BaseAdapter {

	private List<LocalInfo> localInfos;

	public List<LocalInfo> getLocalInfos() {
		return localInfos;
	}

	public void setLocalInfos(List<LocalInfo> localInfos) {
		this.localInfos = localInfos;
	}

	private LayoutInflater layoutInflater;
	private DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	ImageLoader imageLoader;

	public QualityAdapter(CommonActivity activity) {
		layoutInflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = activity.imageLoader;
		options = activity.options;
	}

	public class ViewHolder {
		public TextView tvTitle;
		public TextView tvContent;
		public Button btnClick;
		public ImageView img;
	}

	private void bindView(LocalInfo localInfo, ViewHolder viewHolder) {
		if (localInfo.getContent() != null) {
			viewHolder.tvContent.setText(localInfo.getContent());
		}

		String url = "http://cloud.yofoto.cn" + localInfo.getbImg();
		imageLoader.displayImage(url, viewHolder.img, options,
				animateFirstListener);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (localInfos == null) {
			return 0;
		} else {
			return localInfos.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LocalInfo localInfo = localInfos.get(position);

		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_company_quality, null);
			ViewHolder vHolder = new ViewHolder();
			// vHolder.tvTitle = (TextView) convertView
			// .findViewById(R.id.Tv_Cultural_Title);
			vHolder.tvContent = (TextView) convertView
					.findViewById(R.id.Tv_QualityContent);
			vHolder.img = (ImageView) convertView
					.findViewById(R.id.Iv_ContentThree);
			convertView.setTag(vHolder);
		}
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		bindView(localInfo, viewHolder);
		return convertView;
	}
}