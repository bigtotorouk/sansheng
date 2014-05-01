package com.view;

import com.lekoko.sansheng.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-27 下午7:09:06 declare:
 */
public class BottomButton extends RelativeLayout {

	private View view;

	private ImageView imgIcon;
	private TextView tvBtn;
	private TextView tvCount;
	private RelativeLayout layoutCcounter;

	public BottomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.layout_bottom_button, null);
		addView(view);
		imgIcon = (ImageView) view.findViewById(R.id.Img_Icon);
		tvBtn = (TextView) view.findViewById(R.id.Tv_Icon_Title);
		layoutCcounter = (RelativeLayout) findViewById(R.id.Layout_Counter);
		tvCount = (TextView) findViewById(R.id.Tv_Counter);
 
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.IconButton);
		int btnIcon = a.getResourceId(R.styleable.IconButton_btn_icon, -1);
		int tv = a.getResourceId(R.styleable.IconButton_btn_text, -1);

		if (btnIcon != -1) {
			imgIcon.setBackgroundResource(btnIcon);
		}
		if (tv != -1) {
			String str = getResources().getString(tv);
			tvBtn.setText(str);
		}
//		setCount(12);

	}

	public BottomButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void setCount(int counter) {
		if (counter == 0) {
			layoutCcounter.setVisibility(INVISIBLE);
		} else {
			layoutCcounter.setVisibility(VISIBLE);
			tvCount.setText("" + counter);
		}
	}
}
