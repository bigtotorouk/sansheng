package com.activity.shop.detail;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.Adverst;
import com.sansheng.model.Brand;
import com.sansheng.model.LocalInfo;
import com.view.HeadBar;
import com.view.IndicatorView;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 上午10:24:52 declare:
 */
public class ShopIntroduceActivity extends Activity implements OnClickListener {
	private ViewPager viewPager;
	private IndicatorView indicatorView;
	private ListView lvHealth;
	private LayoutInflater layoutInflater;
	WebView webView;
	private TextView tvIntroduce;
	
	String pid = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.fragment_shopping_detail_introduce);
		super.onCreate(savedInstanceState);
		pid = getIntent().getStringExtra("pid");
		webView = (WebView) findViewById(R.id.Wv_Detail);
		WebViewClient wc = new WebViewClient();
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		webView.requestFocus();
		webView.setWebViewClient(wc);
		
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("详细信息");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);
		
	}
	
	
	@Override
	protected void onResume() {
		update("http://cloud.yofoto.cn/index.php?gr=oauthserver&mr=shwuczb&ar=appshowprinfowap&pid="+pid);
		super.onResume();
	}

	public void update(String content) {
		if (content != null&&webView!=null) {
			webView.loadUrl(content);
			// tvIntroduce.setText(htmlStr);
		}
	}


	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.Btn_Back){
			this.finish();
		}
	}

}
