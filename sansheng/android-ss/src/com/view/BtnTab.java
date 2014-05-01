package com.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;

import com.lekoko.sansheng.R;

public class BtnTab extends Button {

	private int colorSelected = R.drawable.tab_selected_bg;
	private int colorunSelected = R.drawable.tab_unselect_bg;
	private String text_select_color;
	private String text_unselect_color;

	public BtnTab(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		unsleetced();
		setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f);
	}

	public BtnTab(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
				com.lekoko.sansheng.R.styleable.Tab);
		colorSelected = a.getResourceId(
				com.lekoko.sansheng.R.styleable.Tab_select_color, -1);
		colorunSelected = a.getResourceId(
				com.lekoko.sansheng.R.styleable.Tab_unselect_color, -1);
		boolean selected = a.getBoolean(
				com.lekoko.sansheng.R.styleable.Tab_selected, false);
		text_select_color = a
				.getString(com.lekoko.sansheng.R.styleable.Tab_select_text_color);
		text_unselect_color = a
				.getString(com.lekoko.sansheng.R.styleable.Tab_unselect_text_color);
		if (selected == true) {
			selected();
		} else {
			unsleetced();
		}

	}

	public void selected() {
		setBackgroundResource(colorSelected);
		if (text_select_color != null) {
			setTextColor(Color.parseColor(text_select_color));
		} else {
			setTextColor(Color.parseColor("#222222"));
		}

	}

	public void unsleetced() {
		setBackgroundResource(colorunSelected);
		if (text_unselect_color != null) {
			setTextColor(Color.parseColor(text_unselect_color));
		} else {
			setTextColor(Color.parseColor("#222222"));
		}
	}

	public void setBtnTitle(String title) {
		setText(title);
	}
}
