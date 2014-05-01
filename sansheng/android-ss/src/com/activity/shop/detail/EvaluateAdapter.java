package com.activity.shop.detail;

import java.util.List;

import model.Evaluate;
import android.app.Activity;
import android.content.Context;
import android.database.DatabaseUtils;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lekoko.sansheng.R;
import com.tencent.weibo.api.PrivateAPI;
import com.util.DateFormatTool;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-31 下午8:46:45 declare:
 */
public class EvaluateAdapter extends BaseAdapter {

	private List<Evaluate> evaluates;
	private LayoutInflater layoutInflater;
	private View currentView;

	public Activity activity;

	public EvaluateAdapter(Context context, List<Evaluate> ns) {
		evaluates = ns;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		if (evaluates == null) {
			return 0;
		} else {
			return evaluates.size();
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
			convertView = layoutInflater.inflate(
					R.layout.layout_shop_evaulation_item, null);
		}
		Evaluate eva = evaluates.get(position);
		bindView(convertView, eva);
		return convertView;
	}

	public void bindView(View view, Evaluate eva) {
		TextView tvLeve = (TextView) view.findViewById(R.id.Tv_Level);
		TextView tvData = (TextView) view.findViewById(R.id.Tv_Data);
		TextView tvcard = (TextView) view.findViewById(R.id.Tv_Card_Num);
		TextView tvContent = (TextView) view.findViewById(R.id.Tv_Content);
		if (eva.getUserlevel() != null) {
			tvLeve.setText("v" + eva.getUserlevel());
		}
		if (eva.getTime() != null) {
			String data = DateFormatTool.dateTime2Date(eva.getTime());
			tvData.setText(data);
		}
		if (eva.getNumber() != null) {
			tvcard.setText(eva.getNumber());
		}
		if (eva.getUserlevel() != null) {
			tvContent.setText(eva.getContent());
		}
	}

	public List<Evaluate> getEvaluates() {
		return evaluates;
	}

	public void setEvaluates(List<Evaluate> evaluates) {
		this.evaluates = evaluates;
	}

}
