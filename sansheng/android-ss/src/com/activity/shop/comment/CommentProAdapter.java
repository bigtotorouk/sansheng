package com.activity.shop.comment;

import com.lekoko.sansheng.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 商品评价列表适配器
 * @author bing
 */
public class CommentProAdapter extends BaseAdapter {
	private Context mContext;
	public CommentProAdapter(Context context){
		mContext = context;	
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder ;
		if(convertView==null){
			holder = new ViewHolder();
			LayoutInflater inflate = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflate.inflate(R.layout.layout_shop_comment_item, null);
			//holder.name = (TextView)convertView.findViewById(R.id.name);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		return convertView;
	}
	
	static class ViewHolder {
		TextView title;
	}

}
