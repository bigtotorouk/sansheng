package com.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lekoko.sansheng.R;
import com.util.UnitsUtil;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-17 下午12:22:47 declare:
 */
public class BannerIndicator extends RelativeLayout {

	View view;
	private TextView tvTitle;
	private List<ImageView> indicators;
	private int currentIndex;
	private int count;

	public BannerIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = layoutInflater.inflate(R.layout.layout_company_news_indicator,
				null);
		addView(view);
		initWidget();
	}

	public void setCount(int c) {
		currentIndex = 0;
		this.count = c;

		LinearLayout ly = new LinearLayout(this.getContext());
		// android.widget.LinearLayout.LayoutParams lineParams = new
		// android.widget.LinearLayout.LayoutParams(
		// LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		//
		// ly.setLayoutParams(lineParams);
		ly.setOrientation(LinearLayout.HORIZONTAL);
		indicators = new ArrayList<ImageView>();

		for (int i = 0; i < count; i++) {
			ImageView indicator = new ImageView(this.getContext());
			if (i == 0) {
				indicator.setBackgroundResource(R.drawable.indicator_selected);
			} else {
				indicator
						.setBackgroundResource(R.drawable.indicator_unselected);

			}
			android.widget.LinearLayout.LayoutParams lp = new android.widget.LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.setMargins(UnitsUtil.dip2px(getContext(), 3), 0, 0, 0);
			indicator.setLayoutParams(lp);
			ly.addView(indicator);
			indicators.add(indicator);
		}
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		lp.setMargins(0, 0, UnitsUtil.dip2px(getContext(), 15), 0);
		addView(ly, lp);
	}

	public void initWidget() {
		tvTitle = (TextView) findViewById(R.id.Tv_Banner_Title);
	}

	public TextView getTvTitle() {
		return tvTitle;
	}

	public void setTvTitle(String title) {
		this.tvTitle.setText(title);
	}

	public void setCurrent(int newCurrent) {
		ImageView indicator = indicators.get(currentIndex);
		indicator.setBackgroundResource(R.drawable.indicator_unselected);

		indicator = indicators.get(newCurrent);
		indicator.setBackgroundResource(R.drawable.indicator_selected);
		currentIndex = newCurrent;

	}

}
