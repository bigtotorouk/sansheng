package com.activity.index;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.activity.CommonActivity;
import com.baidu.android.pushservice.CustomPushNotificationBuilder;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.http.LoginApi;
import com.lekoko.sansheng.R;
import com.push.Utils;
import com.sansheng.model.User;
import com.util.MacUtil;
import com.util.ProgressDialogUtil;
import com.view.BtnTab;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-17 下午9:24:00 declare:
 */
public class LoginActivity extends CommonActivity implements OnClickListener {
	RelativeLayout fl;
	MemberLoginFragemnt memberFragment;
	FragmentManager fm;
	android.support.v4.app.FragmentTransaction ft;

	BtnTab tabMember;
	BtnTab tabShop;

	public static String clientId = "1089070497052730551";

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		neeecheckLogin = false;
		super.onCreate(arg0);
		setContentView(R.layout.activity_login);
		fl = (RelativeLayout) findViewById(R.id.FL_Content);
		tabMember = (BtnTab) findViewById(R.id.Btn_Member);
		tabShop = (BtnTab) findViewById(R.id.Btn_Shop);
		tabMember.setOnClickListener(this);
		tabShop.setOnClickListener(this);
		setMemberMode();
		memberFragment = new MemberLoginFragemnt();
		fm = getSupportFragmentManager();
		ft = fm.beginTransaction();
		ft.add(R.id.FL_Content, memberFragment);
		ft.commit();

		ShopLoginFragment shopFragment = new ShopLoginFragment();
		fm = getSupportFragmentManager();
		ft = fm.beginTransaction();
		ft.add(R.id.FL_Content2, shopFragment);
		ft.commit();
		// PushManager.startWork(getApplicationContext(),
		// PushConstants.LOGIN_TYPE_API_KEY,
		// Utils.getMetaValue(this, "api_key"));
		// startPush();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		PushManager.activityStarted(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		showChannelIds();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// 如果要统计Push引起的用户使用应用情况，请实现本方法，且加上这一个语句
		setIntent(intent);

		// initPush(intent);
	}

	private void showChannelIds() {
		String appId = null;
		String channelId = null;

		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		appId = sp.getString("appid", "");
		channelId = sp.getString("channel_id", "");
		clientId = sp.getString("user_id", "");
		saveToken(clientId);
		ProgressDialogUtil.close();
		Log.e("debug", "appi:" + appId + "  channelId:" + channelId);

	}

	private void startService() {
		PushManager.startWork(getApplicationContext(),
				PushConstants.LOGIN_TYPE_API_KEY,
				Utils.getMetaValue(this, "api_key"));

		Resources resource = this.getResources();
		String pkgName = this.getPackageName();

		// 设置自定义的通知样式，如果想使用系统默认的可以不加这段代码
		CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
				resource.getIdentifier("notification_custom_builder", "layout",
						pkgName), resource.getIdentifier("notification_icon",
						"id", pkgName), resource.getIdentifier(
						"notification_title", "id", pkgName),
				resource.getIdentifier("notification_text", "id", pkgName));
		cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
		cBuilder.setNotificationDefaults(Notification.DEFAULT_SOUND
				| Notification.DEFAULT_VIBRATE);
		cBuilder.setStatusbarIcon(this.getApplicationInfo().icon);
		cBuilder.setLayoutDrawable(resource.getIdentifier(
				"simple_notification_icon", "drawable", pkgName));
		PushManager.setNotificationBuilder(this, 1, cBuilder);
	}

	public void testLogin() {
		User user = new User();
		user.setUsername("nba001");
		user.setPassword("yftfln");

		String mac = MacUtil.getMac(this);
		Log.e("debug", "mac " + mac);
		LoginApi.Login(user, "1.0|" + mac + "|android");
	}

	public void setMemberMode() {
		findViewById(R.id.FL_Content).setVisibility(View.VISIBLE);
		findViewById(R.id.FL_Content2).setVisibility(View.INVISIBLE);

	}

	public void setShopMode() {
		findViewById(R.id.FL_Content).setVisibility(View.INVISIBLE);
		findViewById(R.id.FL_Content2).setVisibility(View.VISIBLE);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Member:
			Log.e("debug", "member");
			setCurrneTab(1);
			break;
		case R.id.Btn_Shop:
			Log.e("debug", "shopping");
			setCurrneTab(2);
			break;
		case R.id.Btn_Login:
			break;
		}
	}

	public void setCurrneTab(int index) {
		tabMember.unsleetced();
		tabShop.unsleetced();
		if (index == 1) {
			tabMember.selected();
			tabShop.unsleetced();
			setMemberMode();
		} else {
			tabMember.unsleetced();
			tabShop.selected();
			setShopMode();
		}
	}
}
