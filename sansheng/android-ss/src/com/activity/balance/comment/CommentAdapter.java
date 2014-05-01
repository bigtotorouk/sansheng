package com.activity.balance.comment;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.Product;

public class CommentAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater;
	private CommonActivity activity;
	private Uihandler uihandler;
	private List<Product> balance;

	String[] coment;
	public int p;

	public CommentAdapter(CommonActivity context) {
		this.activity = context;
		uihandler = new Uihandler();
		layoutInflater = (LayoutInflater) context.getLayoutInflater();
		balance = new ArrayList<Product>();
	}

	public List<Product> getBalance() {
		return balance;
	}

	public void setBalance(List<Product> balance) {
		this.balance = balance;
		coment = new String[balance.size()];
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (balance == null) {
			return 0;
		}
		return balance.size();
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
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_comment_item, null);
		}

		bindView(convertView, arg0);
		return convertView;
	}

	public void bindView(View v, final int position) {

		final Product bl = balance.get(position);
		p = position;
		if (position == (balance.size() - 1)) {
			v.findViewById(R.id.Layout_Division).setVisibility(View.INVISIBLE);
		} else {
			v.findViewById(R.id.Layout_Division).setVisibility(View.VISIBLE);
		}

		TextView tvName = (TextView) v.findViewById(R.id.Tv_name);
		TextView tvProce = (TextView) v.findViewById(R.id.Tv_Price);
		TextView tvPv = (TextView) v.findViewById(R.id.Tv_Pv);
		TextView tvNumber = (TextView) v.findViewById(R.id.Tv_Number);

		if (bl.getName() != null) {
			tvName.setText(bl.getName());
		}
		if (bl.getPrice() != null) {
			tvProce.setText(bl.getPrice());
		}
		if (bl.getPv() != null) {
			tvPv.setText(bl.getPv());
		}
		if (bl.getNumber() != null) {
			tvNumber.setText(bl.getNumber());
		}
		RatingBar ratingBar = (RatingBar) v.findViewById(R.id.ratingBar1);
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				int rat = (int) rating;
				bl.setQualityint(rat);
			}
		});
		final EditText et = (EditText) v.findViewById(R.id.Et_Comment);

		// final TextWatcher tw = new TextWatcher() {
		//
		// @Override
		// public void onTextChanged(CharSequence s, int start, int before,
		// int count) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void beforeTextChanged(CharSequence s, int start, int count,
		// int after) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void afterTextChanged(Editable s) {
		// // TODO Auto-generated method stub
		// Log.e("debug", "after  change  content:" + s.toString()
		// + " position:" + p);
		// // bl.setComment(s.toString());
		// coment[p] = s.toString();
		// }
		// };
		et.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e("debug", "click");
				// TODO Auto-generated method stub
				et.setFocusableInTouchMode(true);
			}
		});

		et.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (hasFocus == false) {

					if (!et.getText().equals("")) {
						Log.e("debug", "after  fouse  content:"
								+ et.getText().toString() + " position:"
								+ position);
						bl.setComment(et.getText().toString());
						coment[position] = et.getText().toString();
					}
				}
			}
		});

		for (int i = 0; i < coment.length; i++) {
			Log.e("debug", "comment:" + coment[i]);
		}
		// Log.e("debug", "position:" + p + "  content" + bl.getComment());

		if (bl.getComment() != null) {
			et.setText(coment[position]);
		} else {
			et.setText("");
		}

	}

	class Uihandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {

		}
	}

	public void remove() {
		Product cf = balance.get(p);
		balance.remove(cf);

	}
}
