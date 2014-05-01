package com.activity.company.honor;

import java.util.List;

import com.lekoko.sansheng.R;
import com.sansheng.model.LocalInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HonourAdapter extends BaseAdapter {

	private List<LocalInfo> localInfos;

	private LayoutInflater layoutInflater;

	private Context context = null;

	public HonourAdapter(Context context) {
		this.context = context;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		return null;
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
		convertView = (ViewGroup) layoutInflater.inflate(
				R.layout.layout_company_honor_year, null);
		ViewHolder vHolder = new ViewHolder();
		vHolder.tvTitle = (TextView) convertView
				.findViewById(R.id.Tv_HonorYear);
		vHolder.ivOne = (ImageView) convertView.findViewById(R.id.iv_one);
		if (position == 0) {
			vHolder.ivOne.setVisibility(View.INVISIBLE);
		}
		convertView.setTag(vHolder);
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		bindView(localInfo, viewHolder);

		String[] conten = localInfo.getContenList();

		for (int i = 0; i < conten.length; i++) {
			View tv = layoutInflater.inflate(R.layout.layout_hornor_item, null);
			((ViewGroup) convertView).addView(tv);
			((TextView) tv.findViewById(R.id.Tv_Honor)).setText(conten[i]);
		}
		return convertView;
	}

	private void bindView(LocalInfo localInfo, ViewHolder viewHolder) {
		if (localInfo.getTitle() != null) {
			viewHolder.tvTitle.setText(localInfo.getTitle());
		}

	}

	public List<LocalInfo> getLocalInfos() {
		return localInfos;
	}

	private class ViewHolder {
		public TextView tvTitle;
		public TextView tvContent;
		public ImageView ivOne;
	}

	public void setLocalInfos(List<LocalInfo> localInfos) {
		this.localInfos = localInfos;
	}

	// public List<LocalInfo> getLocalInfosTitle() {
	// return localInfosTitle;
	// }
	//
	// public void setLocalInfosTitle(List<LocalInfo> LocalInfosTitle) {
	// this.localInfosTitle = LocalInfosTitle;
	// }

}
