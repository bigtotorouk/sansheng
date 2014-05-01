package com.view;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.lekoko.sansheng.R;

public class mySearchView extends RelativeLayout implements OnClickListener {

	private LayoutInflater layoutInflater;
	private View view;
	private EditText etSearch;
	private TextWatcher textWatcher;
	private Button btnSearch;

	public mySearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		init();
	}

	public void init() {
		view = layoutInflater.inflate(R.layout.layout_search_view, null);
		addView(view);
		etSearch = (EditText) view.findViewById(R.id.Et_Search);
		btnSearch = (Button) view.findViewById(R.id.Btn_Search);
	}

	public String getContent() {
		return etSearch.getText().toString();
	}

	public View getSearchBtn() {
		return btnSearch;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.e("debug", "onclick");
	}
}
