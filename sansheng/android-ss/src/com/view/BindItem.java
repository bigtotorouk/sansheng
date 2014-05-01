package com.view;

import com.lekoko.sansheng.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-9 下午3:35:43 declare:
 */
public class BindItem extends RelativeLayout {

	private ImageView imgIcon;
	private TextView tvName;
	View view;
	TypedArray a;

	public BindItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = layoutInflater.inflate(R.layout.layout_setting_bind, null);
		LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		addView(view, lp);
		a = context.obtainStyledAttributes(attrs, R.styleable.Bind);

		initWidget();
	}

	public void initWidget() {

		imgIcon = (ImageView) findViewById(R.id.Img_Icon);
		tvName = (TextView) findViewById(R.id.Tv_Name);
		int iconRes = a.getResourceId(R.styleable.Bind_icon_src, -1);
		int nameRes = a.getResourceId(R.styleable.Bind_item_text, -1);
		int itemBgRes = a.getResourceId(R.styleable.Bind_itemgb, -1);
		if (itemBgRes != -1) {
			setBackgroundResource(itemBgRes);
		}

		if (iconRes != -1) {
			imgIcon.setBackgroundResource(iconRes);
		}
		if (nameRes != -1) {
			tvName.setText(getResources().getString(nameRes));
		}

	}

}
