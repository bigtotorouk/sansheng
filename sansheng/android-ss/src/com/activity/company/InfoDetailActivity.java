package com.activity.company;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-19 下午2:40:29 declare:
 */
public class InfoDetailActivity extends CommonActivity implements
		OnClickListener {

	WebView webView;
	HeadBar headBar;
	public static final String TITLE = "title";
	public static final String URL = "url";

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_infodetail);
		headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setWidgetClickListener(this);
		headBar.setRightType(BtnType.empty);
		headBar.setTitle("新闻详情");
		webView = (WebView) findViewById(R.id.Wv_Detail);
		WebViewClient wc = new WebViewClient();
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		webView.requestFocus();
		webView.setWebViewClient(wc);
		Intent intent = getIntent();
		getInfo(intent);
	}

	public void getInfo(Intent i) {
		Bundle b = i.getExtras();
		if (i != null) {
			if (b == null) {
				return;
			}
			if (b.containsKey("title")) {
				String title = b.getString("title");
				if (title != null) {
					headBar.setTitle(title);
				}
			}
			if (b.containsKey("url")) {
				String url = b.getString("url");
				if (url != null) {
					webView.loadUrl(url);
				}
			}

		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
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
