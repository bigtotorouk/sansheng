package com.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lekoko.sansheng.R;

public class OprationDilog extends Dialog {

	View view;

	private TextView tvContent;
	private TextView tvTitle;
	private Button btnOk;
	private Button btnNo;

	private Context context;
	private OprationDilog dialog;

	public OprationDilog(Context context) {
		super(context, R.style.NOTitleDialog);
		setContentView(R.layout.layout_opration_dialog);
		this.context = context;
		dialog = this;
		initWidget();

	}

	public void initWidget() {
		tvContent = (TextView) findViewById(R.id.Tv_Content);
		tvTitle = (TextView) findViewById(R.id.Tv_Title);
		tvTitle.setText(context.getResources().getString(R.string.tips));
		btnOk = (Button) findViewById(R.id.Btn_OK);
		btnNo = (Button) findViewById(R.id.Btn_No);

		btnNo.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

	}

	public void setContent(String content) {
		tvContent.setText(content);
	}

	public void setTitle(String title) {
		tvTitle.setText(title);
	}

	public void onOkCallBack(android.view.View.OnClickListener onClickListener) {
		btnOk.setOnClickListener(onClickListener);
	}

}
