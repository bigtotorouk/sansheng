package com.view;

import com.lekoko.sansheng.R;
import com.lekoko.sansheng.R.layout;
import com.lekoko.sansheng.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ResultsView extends RelativeLayout {

	private View view;
	Button btn;
	public ResultsView(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = layoutInflater.inflate(R.layout.layout_results_view, null);
		btn=(Button)view.findViewById(R.id.btn_periods);
		TextPaint txt=btn.getPaint();
		txt.setFakeBoldText(true);
		addView(view);
	}
	
	public void setTitle(String strTitle){
		btn.setText(strTitle);
	}
	public void setWidth(int btnwidth){
		btn.setWidth(btnwidth);
	}
}
