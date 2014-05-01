package com.view;

import com.lekoko.sansheng.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.drm.DrmStore.Action;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
public class ItemButton extends RelativeLayout {

	private View view;

	private ImageView imgIcon;
	private TextView tvBtn;
	private TextView tvCount;
	private RelativeLayout layoutCcounter;
	public Button btn;

	private View parent;
	private String selectedColor;
	private int selectedBg;
	private int unselectedBg;
	TypedArray a;

	private String text;

	public ItemButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.item_button, null);
		LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		addView(view, lp);
		btn = (Button) view.findViewById(R.id.Btn_Item);
		parent = this;
		a = context.obtainStyledAttributes(attrs, R.styleable.ItemButton);

		selectedColor = a.getString(R.styleable.ItemButton_selected_color);
		selectedBg = a.getResourceId(R.styleable.ItemButton_selected_bg, 0);
		text = a.getString(R.styleable.ItemButton_my_title);
		btn.setText(text);
		unselectedBg = a.getResourceId(R.styleable.ItemButton_unselected_bg, 0);

		parent.setBackgroundColor(Color.parseColor("#ffffff"));
		btn.setBackgroundResource(unselectedBg);

		btn.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
				if (action == MotionEvent.ACTION_DOWN) {
					parent.setBackgroundColor(Color.parseColor(selectedColor));
					btn.setBackgroundResource(selectedBg);
				}
				if (action == MotionEvent.ACTION_UP) {
					parent.setBackgroundColor(Color.parseColor("#ffffff"));
					btn.setBackgroundResource(unselectedBg);

				}
				return false;
			}
		});

	}

	public ItemButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

}
