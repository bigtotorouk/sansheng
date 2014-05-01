package com.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.lekoko.sansheng.R;
import com.view.HeadBar.BtnType;

/** 
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-7 下午3:33:49 declare:
 */
public class SumaryView extends RelativeLayout {
	public TextView tvSummaryPrice;
	public TextView tvSumamryPV;
	public Button btnSumary;

	public SumaryView(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = (View) inflater.inflate(R.layout.layout_summary, null);
		btnSumary = (Button) view.findViewById(R.id.Btn_Sumary);
		tvSummaryPrice = (TextView) view.findViewById(R.id.Tv_Sumamry_Number);
		tvSumamryPV = (TextView) view.findViewById(R.id.Tv_Sumamry_Pv);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.Summary);
		int rightText = a.getResourceId(R.styleable.Summary_right_text, -1);
		if (rightText != -1) {
			btnSumary.setText(getResources().getString(rightText));
		}
		LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		addView(view, lp);
	}

}
