package com.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lekoko.sansheng.R;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-8 下午4:19:02 declare:
 */
public class SettingItem extends RelativeLayout {
	View view;
	private TextView tvHead;
	private TextView tvTail;
	private CheckBox ckSetting;
	private ImageView imgArrow;
	private OnClickListener onClickListener;

	public SettingItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = (View) layoutInflater
				.inflate(R.layout.layout_setting_item, null);
		initWidget();
		addView(view);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.Setting);
		int headStr = a.getResourceId(R.styleable.Setting_head_str, -1);
		int tialStr = a.getResourceId(R.styleable.Setting_tail_str, -1);
		boolean canCheck = a.getBoolean(R.styleable.Setting_can_check, false);
		boolean show_arrow = a
				.getBoolean(R.styleable.Setting_show_arrow, false);
		int itembgRes = a.getResourceId(R.styleable.Setting_item_bg, -1);
		if (itembgRes != -1) {
			view.setBackgroundResource(itembgRes);
		}
		if (canCheck == true) {
			ckSetting.setVisibility(VISIBLE);
		}
		if (show_arrow == true) {
			imgArrow.setVisibility(VISIBLE);
		}
		if (headStr != -1) {
			tvHead.setText(getResources().getString(headStr));
		}
		if (tialStr != -1) {
			tvTail.setVisibility(View.VISIBLE);
			tvTail.setText(getResources().getString(tialStr));
		}

		// view.setOnTouchListener(this);
		// view.setOnTouchListener(this);

		SettingItem settingItem = this;

	}

	public void initWidget() {
		imgArrow = (ImageView) view.findViewById(R.id.Img_arrow);
		tvHead = (TextView) view.findViewById(R.id.Tv_Head);
		tvTail = (TextView) view.findViewById(R.id.Tv_Tail);
		ckSetting = (CheckBox) view.findViewById(R.id.Ck_Setting);
	}

	public void setHeadStr(String str) {
		tvHead.setText(str);
	}

	public void setTAilStr(String str) {
		tvTail.setText(str);
	}

	public CheckBox getCkSetting() {
		return ckSetting;
	}

	public void setCkSetting(CheckBox ckSetting) {
		this.ckSetting = ckSetting;
	}

	public void setCkClickListner(OnClickListener clickListener) {
		this.ckSetting.setOnClickListener(clickListener);
	}

	// @Override
	// public boolean onTouch(View v, MotionEvent event) {
	// Log.e("debug", "onTouch");
	// int action = event.getAction();
	// switch (action) {
	// case MotionEvent.ACTION_DOWN:
	// Log.e("debug", "down");
	// // view.setBackgroundColor(Color.parseColor("#cccccc"));
	// view.setBackgroundResource(R.drawable.list_bg_setting_center_selected);
	//
	// return false;
	//
	// case MotionEvent.ACTION_UP:
	// Log.e("debug", "up");
	// // view.setBackgroundColor(Color.parseColor("#f6f6f6"));
	// view.setBackgroundResource(R.drawable.list_bg_setting_center_unselected);
	//
	// return false;
	// }
	//
	// return true;
	//
	// }

	public OnClickListener getOnClickListener() {
		return onClickListener;
	}

	public void setClick(OnClickListener click) {
		view.setOnClickListener(click);
	}

}
