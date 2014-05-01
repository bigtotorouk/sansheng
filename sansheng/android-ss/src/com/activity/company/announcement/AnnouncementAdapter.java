package com.activity.company.announcement;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lekoko.sansheng.R;
import com.sansheng.model.LocalInfo;

public class AnnouncementAdapter extends BaseAdapter {

	private List<LocalInfo> localInfos;

	private LayoutInflater layoutInflater;

	public AnnouncementAdapter(Context context) {
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
		public TextView tvDate;
		public TextView tvStatus;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LocalInfo localInfo = localInfos.get(position);

		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_company_announcement, null);
			ViewHolder vHolder = new ViewHolder();
			vHolder.tvTitle = (TextView) convertView
					.findViewById(R.id.Tv_Title);
			vHolder.tvDate = (TextView) convertView.findViewById(R.id.Tv_Date);
			vHolder.tvStatus = (TextView) convertView
					.findViewById(R.id.tv_status);
			// vHolder.tvContent = (TextView) convertView
			// .findViewById(R.id.Tv_Content);
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
			viewHolder.tvDate.setText(localInfo.getData());
		}
		if (localInfo.getStatus() != null) {
			String strStatus = localInfo.getStatus();
			if (strStatus.equals("0")) {
				viewHolder.tvStatus.setText("紧急");
				viewHolder.tvStatus.setVisibility(View.VISIBLE);
			} else {
				viewHolder.tvStatus.setVisibility(View.GONE);
			}
		}
		// if (localInfo.getContent() != null) {
		// viewHolder.tvContent.setText(localInfo.getContent());
		// }

	}

	public List<LocalInfo> getLocalInfos() {
		return localInfos;
	}

	public void setLocalInfos(List<LocalInfo> localInfos) {
		this.localInfos = localInfos;
	}

}
