/**
 * 
 */
/**
 * @author dengjianxin09
 *
 */
package com.activity.company.brands;

import java.util.List;

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

public class BrandsAdapter extends BaseAdapter {
	private List<LocalInfo> localInfos;

	private LayoutInflater layoutInflater;
	ImageLoader imageLoader;
	private DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	public BrandsAdapter(CommonActivity context) {
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = context.imageLoader;
		options = context.options;
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
		public TextView tvContent;
		public ImageView ivcontentThree;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LocalInfo localInfo = localInfos.get(position);

		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_company_brands, null);
			ViewHolder vHolder = new ViewHolder();
			vHolder.tvTitle = (TextView) convertView
					.findViewById(R.id.Tv_Brands_Title);
			vHolder.tvContent = (TextView) convertView
					.findViewById(R.id.Tv_Brands_Content);
			vHolder.ivcontentThree = (ImageView) convertView
					.findViewById(R.id.iv_contentThree);
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

		if (localInfo.getContent() != null) {
			viewHolder.tvContent.setText(localInfo.getContent());
		}
		if (localInfo.getbImg() != null) {
			String strImg = localInfo.getbImg();
			if (strImg != "") {
				// viewHolder.imgCultrual.setImageURI(strImg);
				String url = "http://cloud.yofoto.cn" + localInfo.getbImg();
				imageLoader.displayImage(url, viewHolder.ivcontentThree,
						options, animateFirstListener);
				viewHolder.ivcontentThree.setVisibility(View.VISIBLE);
			} else {
				viewHolder.ivcontentThree.setVisibility(View.GONE);
			}
		} else {
			viewHolder.ivcontentThree.setVisibility(View.GONE);
		}

	}

	public List<LocalInfo> getLocalInfos() {
		return localInfos;
	}

	public void setLocalInfos(List<LocalInfo> localInfos) {
		this.localInfos = localInfos;
	}

}