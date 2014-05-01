package com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lekoko.sansheng.R;
import com.hp.hpl.sparta.Text;

public class HeadBar extends RelativeLayout { 

	private ImageButton btnBack;
	private View view;
	private Button btnRight;
	private ImageButton imgRight;
	private TextView tvTitle;

	public enum BtnType {
		image, btn, empty
	}

	public HeadBar(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		view = layoutInflater.inflate(R.layout.layout_head_bar, null);
		addView(view);
		initWidget();
	}

	public void initWidget() {
		btnBack = (ImageButton) view.findViewById(R.id.Btn_Back);
		btnRight = (Button) view.findViewById(R.id.Btn_Right);
		imgRight = (ImageButton) view.findViewById(R.id.Img_Right);
		tvTitle = (TextView) view.findViewById(R.id.Tv_Title);
	}

	public ImageButton getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(ImageButton btnBack) {
		this.btnBack = btnBack;
	}

	public Button getBtnRight() {
		return btnRight;
	}

	public ImageButton getImgRight() {
		return imgRight;
	}

	public void setTitle(String title) {
		tvTitle.setText(title);
	}

	/**
	 * 设置bar右边操作控件的类型
	 * 
	 * @param type
	 */
	public void setRightType(BtnType type) {
		if (type == BtnType.image) {
			imgRight.setVisibility(View.VISIBLE);
			btnRight.setVisibility(View.GONE);
		} else if (type == BtnType.btn) {
			imgRight.setVisibility(View.GONE);
			btnRight.setVisibility(View.VISIBLE);
		} else if (type == type.empty) {
			imgRight.setVisibility(View.INVISIBLE);
			btnRight.setVisibility(View.INVISIBLE);
		}
	}

	public void setBtnRightText(String btnText) {
		btnRight.setText(btnText);
	}

	public void setRightImg(int id) {
		imgRight.setBackgroundResource(id);
	}

	public void setWidgetClickListener(OnClickListener clickListener) {
		btnBack.setOnClickListener(clickListener);
		imgRight.setOnClickListener(clickListener);
		btnRight.setOnClickListener(clickListener);
	}

}
