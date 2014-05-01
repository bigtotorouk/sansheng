package com.activity.setting.bindsetting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.activity.CommonActivity;
import com.activity.setting.SettingActivity;
import com.activity.setting.bindsetting.tecent.TecentLoginActivity;
import com.lekoko.sansheng.R;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;
import com.tencent.weibo.api.UserAPI;
import com.tencent.weibo.constants.OAuthConstants;
import com.tencent.weibo.oauthv1.OAuthV1;
import com.util.OauthKeeper;
import com.view.BindItem;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-9 下午12:50:43 declare:
 */
public class BindSettingActivity extends CommonActivity implements
		OnClickListener {

	BindItem itemTecent;
	BindItem itemSina;
	// BindItem itemWX;
	private IWXAPI api;

	private static final String APP_ID = "wxcf6c0794fc6a793c";

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub

		super.onCreate(arg0);
		setContentView(R.layout.activity_setting_bind);
		api = WXAPIFactory.createWXAPI(this, APP_ID, true);
		initWidget();

	}

	public void initWidget() {
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setRightType(BtnType.empty);
		headBar.setTitle(getStr(R.string.bind_setting));
		headBar.setWidgetClickListener(this);
		itemTecent = (BindItem) findViewById(R.id.Item_Tecent);
		itemSina = (BindItem) findViewById(R.id.Item_Sina);
		// itemWX = (BindItem) findViewById(R.id.Item_Wx);
		// itemWX.setOnClickListener(this);
		itemSina.setOnClickListener(this);
		itemTecent.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent;
		switch (id) {
		case R.id.Item_Tecent:
			Log.e("debug", "onclick  tecent");
			intent = new Intent(this, TecentLoginActivity.class);
			startActivity(intent);
			break;

		case R.id.Item_Sina:
			Log.e("debug", "onClick  sina");
			// intent = new Intent(this, SinaLogingActivity.class);
			// startActivity(intent);
			break;

		// case R.id.Item_Wx:
		// registerToWX();
		// break;
		case R.id.Btn_Back:
			// testInfo();
			back();
		}
	}

	public void registerToWX() {

		boolean r = api.registerApp(APP_ID);
		Log.e("debug", "result:" + r);
		String text = "haha";
		// 初始化一个WXTextObject对象
		WXTextObject textObj = new WXTextObject();
		textObj.text = text;

		// 用WXTextObject对象初始化一个WXMediaMessage对象
		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = textObj;
		// 发送文本类型的消息时，title字段不起作用
		// msg.title = "Will be ignored";
		msg.description = text;

		// 构造一个Req
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("text"); // transaction字段用于唯一标识一个请求
		req.message = msg;
		req.scene = SendMessageToWX.Req.WXSceneTimeline; // 调用api接口发送数据到微信
		api.sendReq(req);
	}

	public void testInfo() {
		OAuthV1 oAuth = OauthKeeper.readOAuth(this);
		UserAPI userAPI = new UserAPI(OAuthConstants.OAUTH_VERSION_1);
		try {
			String response = userAPI.info(oAuth, "json");// 获取用户信息
			Log.e("debug", "response:" + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		userAPI.shutdownConnection();
	}

	private String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis())
				: type + System.currentTimeMillis();
	}

	private void back() {
		Intent intent = new Intent(this, SettingActivity.class);
		startActivity(intent);
		overridePendingTransition(-1, -1);
		finish();
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
		back();
	}

}
