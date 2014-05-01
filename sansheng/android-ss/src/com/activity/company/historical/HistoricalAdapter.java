package com.activity.company.historical;

import java.util.List;

import com.lekoko.sansheng.R;
import com.sansheng.model.LocalInfo;

import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HistoricalAdapter extends BaseAdapter {

	private List<LocalInfo> localInfos;

	private LayoutInflater layoutInflater;

	private Context context = null;

	public List<LocalInfo> getLocalInfos() {
		return localInfos;
	}

	public void setLocalInfos(List<LocalInfo> localInfos) {
		this.localInfos = localInfos;
	}

	public HistoricalAdapter(Context context) {
		this.context = context;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	private class ViewHolder {
		public TextView tvYear;
		public TextView tvMoth;
		public TextView tvContent;
	}

	private void bindView(LocalInfo localInfo, ViewHolder viewHolder) {
		if (localInfo.getData() != null) {
			// SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM");
			String str = localInfo.getData().toString();
			// dateformat.parse(str);
			String[] strTime = str.split("-");
			viewHolder.tvYear.setText(strTime[0]);
			viewHolder.tvMoth.setText(strTime[1]);
			TextPaint tp = viewHolder.tvMoth.getPaint();
			tp.setFakeBoldText(true);
		}
		if (localInfo.getContent() != null) {
			viewHolder.tvContent.setText(localInfo.getContent());
		}
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
		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_company_historical, null);
			ViewHolder vHolder = new ViewHolder();
			vHolder.tvYear = (TextView) convertView.findViewById(R.id.Tv_year);
			vHolder.tvMoth = (TextView) convertView.findViewById(R.id.Tv_moth);

			vHolder.tvContent = (TextView) convertView
					.findViewById(R.id.Tv_content);
			convertView.setTag(vHolder);
		}
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		bindView(localInfo, viewHolder);

		return convertView;
	}

}
