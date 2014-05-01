package com.activity.setting.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-9 下午12:51:37 declare:
 */
public class AboutActivity extends CommonActivity implements OnClickListener {
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_setting_about);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setRightType(BtnType.empty);
		headBar.setTitle(getStr(R.string.about_sansheng_title));
		headBar.setWidgetClickListener(this);
		Intent i = getIntent();
		if (i.getStringExtra("url") != null) {
			String url = i.getStringExtra("url");
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(url));
			startActivity(intent);

		}

	 
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;

		default:
			break;
		}

	}

}
