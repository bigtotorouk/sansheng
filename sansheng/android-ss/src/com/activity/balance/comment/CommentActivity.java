package com.activity.balance.comment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

import com.actionbarsherlock.app.ActionBar;
import com.activity.CommonActivity;
import com.google.gson.Gson;
import com.http.BaseRequest;
import com.http.CustomFormService;
import com.http.ViewCommonResponse;
import com.http.task.FormAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Balance;
import com.sansheng.model.FormDetail;
import com.sansheng.model.Product;
import com.sansheng.model.User;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

public class CommentActivity extends CommonActivity implements OnClickListener {
	public static List<Balance> bList;
	ListView lvComment;
	CommentAdapter commentAdapter;
	public static final String ACTION_COMENT = "comment";
	public static final String BUNDLE_COMMENT = "comment";
	int rat;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_comment);
		HeadBar headBar = (HeadBar) findViewById(R.id.HeadBar);

		headBar.setTitle("发表评价");
		headBar.setRightType(BtnType.empty);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		headBar.setWidgetClickListener(this);
		commentAdapter = new CommentAdapter(this);

		Intent intent = getIntent();
		if (intent != null) {
			intent.getAction().equals(ACTION_COMENT);
			Bundle bundle = intent.getExtras();

			FormDetail form = (FormDetail) bundle.get(BUNDLE_COMMENT);

			if (form != null && form.getOderprlist() != null) {
				List<Product> products = form.getOderprlist();
				commentAdapter.setBalance(products);
			}
			lvComment = (ListView) findViewById(R.id.Lv_Comment);

			View btnSubmit = getLayoutInflater().inflate(
					R.layout.layout_comment_submit, null);
			lvComment.addFooterView(btnSubmit);
			lvComment.setAdapter(commentAdapter);

			btnSubmit.findViewById(R.id.Btn_Sumbit).setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							Log.e("debug", "" + commentAdapter.getBalance());
							comment();
						}
					});
  
		}

		RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar_L);
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				rat = (int) rating;
			}
		});

	}

	public void comment() {

		BaseRequest requert = this
				.createRequestWithUserId(CustomFormService.FORM_COMMENT);// action名称

		User user = getUser();
		requert.add("userlevel", user.getUserlevel());
		requert.add("logisticsok", "" + rat);
		Gson g = new Gson();
		String comment = g.toJson(commentAdapter.getBalance());
		requert.add("commentinfo", comment);
		new FormAsyncTask(this, null).execute(requert);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		Intent i = null;
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;

		}
	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		ProgressDialogUtil.close();
		switch (action) {
		case CustomFormService.FORM_COMMENT:
			FormDetail form = (FormDetail) viewCommonResponse.getData();
			if (viewCommonResponse.getMsgCode() == 0) {
				showToast("" + viewCommonResponse.getMessage());
				finish();
			} else {
				showToast("" + viewCommonResponse.getMessage());
			}
			break;

		}
	}

}
