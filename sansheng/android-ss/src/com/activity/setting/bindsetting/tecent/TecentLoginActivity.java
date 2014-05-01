package com.activity.setting.bindsetting.tecent;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.tencent.weibo.api.TAPI;
import com.tencent.weibo.api.UserAPI;
import com.tencent.weibo.constants.OAuthConstants;
import com.tencent.weibo.oauthv1.OAuthV1;
import com.tencent.weibo.oauthv1.OAuthV1Client;
import com.tencent.weibo.utils.QHttpClient;
import com.util.OauthKeeper;
import com.util.SerializeTool;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.LoadingDilog;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-9 下午4:48:09 declare:
 */
public class TecentLoginActivity extends CommonActivity implements
		OnClickListener {
	public final static int RESULT_CODE = 1;
	private static final String TAG = "OAuthV1AuthorizeWebView";
	private OAuthV1 oAuth; // Oauth鉴权所需及所得信息的封装存储单元
	private String oauthCallback = "null";
	// !!!请根据您的实际情况修改!!! 换为您为自己的应用申请到的APP KEY
	private String oauthConsumeKey = "801398174";
	// !!!请根据您的实际情况修改!!! 换为您为自己的应用申请到的APP SECRET
	private String oauthConsumerSecret = "7e1d76a70a4bfa0996029700d364f131";
	String urlStr;
	WebView webView;

	private LoadingDilog loadingDilog;
	private Activity activity;

	private UiHandler uiHandler;

	private final static int MSG_SHOW_DIALOG = 1;
	private final static int MSG_MISS_DIALOG = 2;
	private final static int MSG_WEB_LOAD = 3;
	private final static int MSG_FINISH = 4;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_setting_bind_tecent);

		activity = this;

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setRightType(BtnType.empty);
		headBar.setTitle(getStr(R.string.tecetnt_weibo));
		headBar.setWidgetClickListener(this);
		uiHandler = new UiHandler();
		webView = (WebView) findViewById(R.id.WebView_Tecetnt);
		Intent intent = this.getIntent();
		oAuth = new OAuthV1();
		oAuth.setOauthToken("123");

		urlStr = OAuthConstants.OAUTH_V1_AUTHORIZE_URL + "?oauth_token="
				+ oAuth.getOauthToken();
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		webView.requestFocus();

		System.out.println(urlStr.toString());
		WebViewClient client = new WebViewClient() {
			/**
			 * 回调方法，当页面开始加载时执行
			 */
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				loadingDilog.show();
				Log.i(TAG, "WebView onPageStarted...");
				Log.i(TAG, "URL = " + url);
				if (url.indexOf("checkType=verifycode") != -1) {
					int start = url.indexOf("checkType=verifycode&v=") + 23;
					String verifyCode = url.substring(start, start + 6);
					oAuth.setOauthVerifier(verifyCode);
					Intent intent = new Intent();
					Log.e("debug", "finish  token:" + oAuth);
					intent.putExtra("oauth", oAuth.getOauthToken()
							+ "  secreat:" + oAuth.getOauthTokenSecret());
					setResult(RESULT_CODE, intent);
					view.destroyDrawingCache();
					Log.e("debug 1",
							"\naccess_token:\n" + oAuth.getOauthToken()
									+ "\naccess_token_secret:\n"
									+ oAuth.getOauthTokenSecret());

					try {
						oAuth = OAuthV1Client.accessToken(oAuth);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					Log.e("debug 2",
							"\naccess_token:\n" + oAuth.getOauthToken()
									+ "\naccess_token_secret:\n"
									+ oAuth.getOauthTokenSecret());

					// finish();
					testInfo();
					OauthKeeper.saveOAuth(activity, oAuth);
					finish();
				}
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				loadingDilog.dismiss();
			}

		};
		webView.setWebViewClient(client);
		loadingDilog = new LoadingDilog(activity);
		loadingDilog.show();

		new Thread() {
			public void run() {
				initOauth();
			};
		}.start();
	}

	public void sendMsg() {
		TAPI tAPI = new TAPI(OAuthConstants.OAUTH_VERSION_1);
		try {
			String response = tAPI.add(oAuth, "json", "天气不错", "127.0.0.1");
			Log.e("debug", "response" + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tAPI.shutdownConnection();
	}

	public void testInfo() {

		UserAPI userAPI = new UserAPI(OAuthConstants.OAUTH_VERSION_1);
		try {
			String response = userAPI.info(oAuth, "json");// 获取用户信息
			Log.e("debug", "response:" + response);
		} catch (Exception e) {
			e.printStackTrace();

		}
		userAPI.shutdownConnection();
	}

	private void initOauth() {
		oAuth = new OAuthV1(oauthCallback);
		oAuth.setOauthConsumerKey(oauthConsumeKey);
		oAuth.setOauthConsumerSecret(oauthConsumerSecret);
		// 关闭OAuthV1Client中的默认开启的QHttpClient。
		OAuthV1Client.getQHttpClient().shutdownConnection();
		// 为OAuthV1Client配置自己定义QHttpClient。
		OAuthV1Client.setQHttpClient(new QHttpClient());
		getToken();
	}

	public void getToken() {
		try {
			oAuth = OAuthV1Client.requestToken(oAuth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = "token:" + oAuth.getOauthToken() + "\n  tokensecret:"
				+ oAuth.getOauthConsumerSecret();

		Log.e("debug", "text:" + text);

		urlStr = OAuthConstants.OAUTH_V1_AUTHORIZE_URL + "?oauth_token="
				+ oAuth.getOauthToken();

		Log.e("debug", "url:" + urlStr);
		Message msg = new Message();
		msg.what = MSG_WEB_LOAD;
		msg.obj = urlStr;
		uiHandler.sendMessage(msg);
	}

	public void loadUrl(String urlStr) {
		loadingDilog.dismiss();
		webView.loadUrl(urlStr);
	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_SHOW_DIALOG:
				loadingDilog = new LoadingDilog(activity);
				loadingDilog.show();
				break;

			case MSG_MISS_DIALOG:
				loadingDilog.dismiss();
				break;
			case MSG_WEB_LOAD:
				String url = (String) msg.obj;
				loadUrl(url);

				break;
			}

		}
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
