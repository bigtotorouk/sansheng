package com.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.lekoko.sansheng.R;

public class SearchView extends RelativeLayout {
	View view;

	private Button btnCancel;
	private Animation showAnimation;
	private Animation hideAnimation;
	private int duration = 1000;
	private RelativeLayout search;
	private EditText etSearch;
	public View btnSearchView;

	public SearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initWidget(context);   
	}

	public SearchView(Context context) {
		super(context);
		initWidget(context);
	}

	public void initWidget(Context context) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.layout_search, null);
		LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		addView(view, lp);

		search = (RelativeLayout) view.findViewById(R.id.Layout_Search);
		btnSearchView = (View) view.findViewById(R.id.Btn_Search);
		btnCancel = (Button) view.findViewById(R.id.Btn_cancel);
		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Log.e("debug", "cancel  click");
				// hide();
			}
		});
		etSearch = (EditText) findViewById(R.id.Et_Search);

		// etSearch.addTextChangedListener(new TextWatcher() {
		// @Override
		// public void onTextChanged(CharSequence s, int start, int before,
		// int count) {
		// int c = etSearch.getText().toString().length();
		// if (c == 0) {
		// hide();
		// } else {
		// show();
		// }
		// }
		//
		// @Override
		// public void beforeTextChanged(CharSequence s, int start, int count,
		// int after) {
		// }
		//
		// @Override
		// public void afterTextChanged(Editable s) {
		// }
		// });
	}

	public void show() {
		Log.e("debug", "show");
		android.view.ViewGroup.LayoutParams lp = search.getLayoutParams();
		lp.width = lp.width - 100;
		search.setLayoutParams(lp);
		btnCancel.setVisibility(View.VISIBLE);

	}

	public void hide() {
		Log.e("debug", "hide");
		android.view.ViewGroup.LayoutParams lp = search.getLayoutParams();
		lp.width = lp.width + 100;
		search.setLayoutParams(lp);
		btnCancel.setVisibility(View.INVISIBLE);
	}

	public EditText getEtSearch() {
		return etSearch;
	}

	public String getContent() {
		return etSearch.getText().toString();
	}

	public void setEtSearch(EditText etSearch) {
		this.etSearch = etSearch;
	}

	public View getBtnSearchView() {
		return btnSearchView;
	}

	public void setBtnSearchView(View btnSearchView) {
		this.btnSearchView = btnSearchView;
	}

}
