package com.activity.shop.comment;

import java.util.ArrayList;
import java.util.List;

import com.activity.shop.comment.CommentListProActivity.CommentPro;
import com.lekoko.sansheng.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
/**
 * 商品评价列表适配器
 * @author bing
 */
public class CommentProAdapter extends BaseAdapter {
	private Context mContext;
	private List<CommentPro> comments = new  ArrayList<CommentPro>();
	public CommentProAdapter(Context context){
		mContext = context;	
	}
	
	public void update(List<CommentPro> comments){
		this.comments = comments;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return comments.size();
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
			holder.title = (TextView)convertView.findViewById(R.id.title);
			holder.time = (TextView)convertView.findViewById(R.id.time);
			holder.content = (TextView)convertView.findViewById(R.id.content);
			holder.bar1 = (RatingBar)convertView.findViewById(R.id.ratingBar1);
			holder.bar2 = (RatingBar)convertView.findViewById(R.id.ratingBar2);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		CommentPro comment = comments.get(position);
		holder.title.setText(comment.getNumber());
		holder.time.setText(comment.getTime());
		holder.content.setText(comment.getContent());
		holder.bar1.setRating(Float.valueOf(comment.getLogisticsok()));
		holder.bar2.setRating(Float.valueOf(comment.getQualityint()));
		return convertView;
	}
	
	static class ViewHolder {
		TextView title, time, content;
		RatingBar bar1, bar2;
	}

}
