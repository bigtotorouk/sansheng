package com.activity.setting.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.CommonActivity;
import com.activity.setting.SettingActivity;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.SystemService;
import com.http.ViewCommonResponse;
import com.http.task.SystemAsyncTask;
import com.lekoko.sansheng.R;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.ProgressDialogUtil;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-9 下午12:50:07 declare:
 */
public class FeedBackActivity extends CommonActivity implements OnClickListener {
	EditText etFeedBack;

	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_setting_feed_back);
		initWidget();
	}

	public void initWidget() {
		// HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		// headBar.setTitle(getStr(R.string.feed_back));
		// headBar.setRightType(BtnType.image);
		// headBar.getImgRight().setBackgroundResource(
		// R.drawable.btn_head_bar_send);
		// headBar.setBtnRightText(getStr(R.string.send));
		// headBar.setWidgetClickListener(this);
		TextView tvTitle = (TextView) findViewById(R.id.Tv_Title);
		tvTitle.setText("意见反馈");
		ImageButton btnBack = (ImageButton) findViewById(R.id.Btn_Back);
		btnBack.setOnClickListener(this);
		Button btnSend = (Button) findViewById(R.id.Img_Right);
		btnSend.setOnClickListener(this);

		etFeedBack = (EditText) findViewById(R.id.Et_Feed_Bak);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			Intent i = new Intent(this, SettingActivity.class);
			startActivity(i);
			finish();
			break;
		case R.id.Img_Right:
			String feedback = etFeedBack.getText().toString();
			if (feedback.length() == 0 || feedback.length() > 200) {
				showToast("反馈字数在0-200个之间");
				return;
			}

			ProgressDialogUtil.show(this, "", "请稍等...", true, false);
			BaseRequest baseRequest = createRequest(SystemService.SYS_FEED_BACK);
			baseRequest.add("", feedback);
			new SystemAsyncTask(this, null).execute(baseRequest);
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
		switch (action) {
		case ShopService.SHOP_PRBC_LIST:
			ProgressDialogUtil.close();
			Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
			finish();
			break;

		}
	}

}
