package com.activity.setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.activity.CommonActivity;
import com.activity.index.IndexActivity;
import com.activity.index.LoginActivity;
import com.activity.setting.about.AboutActivity;
import com.activity.setting.bindsetting.BindSettingActivity;
import com.activity.setting.feedback.FeedBackActivity;
import com.http.BaseRequest;
import com.http.SystemService;
import com.http.ViewCommonResponse;
import com.http.task.SystemAsyncTask;
import com.lekoko.sansheng.R;
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.SettingItem;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-8 下午4:09:34 declare:
 */
public class SettingActivity extends CommonActivity implements OnClickListener {

	private SettingItem itemClearCache;
	private SettingItem itemPush;
	private SettingItem itemBind;
	private SettingItem itemFeedBack;
	private SettingItem itemVersionUpdate;
	private SettingItem itemAbout;
	private Button btnLogOut;
	private Activity activity;
	public static final String DESCRIPTOR = "com.umeng.share";
	final UMSocialService mController = UMServiceFactory.getUMSocialService(
			DESCRIPTOR, RequestType.SOCIAL);

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_setting);
		initWidget();
	}

	public void initWidget() {
		activity = this;
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getResources().getString(R.string.system_setting));
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);
		itemClearCache = (SettingItem) findViewById(R.id.Item_Clear);
		itemPush = (SettingItem) findViewById(R.id.Item_Push);
		itemBind = (SettingItem) findViewById(R.id.Item_Bind_Setting);
		itemFeedBack = (SettingItem) findViewById(R.id.Item_Feed_Back);
		itemVersionUpdate = (SettingItem) findViewById(R.id.Item_Version_Update);
		itemAbout = (SettingItem) findViewById(R.id.Item_About);
		btnLogOut = (Button) findViewById(R.id.Btn_Logout);
		btnLogOut.setOnClickListener(this);
		itemClearCache.setClick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clearCache();

			}
		});
		itemPush.setClick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		itemBind.setClick(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				mController.openShare(activity, false);
//				mController.getConfig().closeSinaSSo();
//				mController.showLoginDialog(activity, null);
				Intent intent = new Intent(activity, BindSettingActivity.class);
				startActivity(intent);
				overridePendingTransition(-1, -1);
			}
		});
		itemFeedBack.setClick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, FeedBackActivity.class);
				startActivity(intent);
				// overridePendingTransition(-1,-1);
			}
		});
		itemVersionUpdate.setClick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateVersion();
			}
		});
		itemAbout.setClick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, AboutActivity.class);
				startActivity(intent);
				// overridePendingTransition(-1,
				// -1);
			}
		});
		share();
	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		switch (action) {
		case SystemService.SYS_VERSION:
			ProgressDialogUtil.close();
			if (viewCommonResponse.getMsgCode() == 0) {
				String url = (String) viewCommonResponse.getData();
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(url));
				startActivity(intent);
			}

			break;

		}
	}

	public void share() {
		// 设置分享内容
		mController
				.setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能，http://www.umeng.com/social");
		// 设置分享图片, 参数2为图片的地址
		mController.setShareMedia(new UMImage(this,
				"http://www.umeng.com/images/pic/banner_module_social.png"));
		// 设置分享图片，参数2为本地图片的资源引用
		// mController.setShareMedia(new UMImage(getActivity(),
		// R.drawable.icon));
		// 设置分享图片，参数2为本地图片的路径(绝对路径)
		// mController.setShareMedia(new UMImage(getActivity(),
		// BitmapFactory.decodeFile("/mnt/sdcard/icon.png")));
	}

	@Override
	public void onClick(View v) {
		Log.e("debug", "onCLick");
		int id = v.getId();
		Intent intent;
		switch (id) {
		case R.id.Item_Clear:
			break;

		case R.id.Item_Push:

			break;
		case R.id.Item_Bind_Setting:

			intent = new Intent(this, BindSettingActivity.class);
			startActivity(intent);
			break;

		case R.id.Item_Feed_Back:
			break;
		case R.id.Item_Version_Update:

			BaseRequest requert = createRequest(SystemService.SYS_VERSION);
			requert.add("versionstr", "1");
			new SystemAsyncTask(this, null).execute(requert);
			ProgressDialogUtil.show(activity, "提示", "正在加载数据", true, true);

			break;
		case R.id.Item_About:
			intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
			finish();

			break;
		case R.id.Btn_Back:
			intent = new Intent(this, IndexActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.Btn_Logout:
			logout();
			break;
		}

	}

	public void logout() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("退出当前帐号");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				ProgressDialogUtil.close();
				cleanUser();
				Intent intent = new Intent(activity, LoginActivity.class);
				startActivity(intent);
				finish();
				saveShopCarCount(0);
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.show();

	}

	public void updateVersion() {
		Toast.makeText(this, "更新完成", Toast.LENGTH_SHORT).show();
		BaseRequest requert = createRequest(SystemService.SYS_VERSION);
		requert.add("versionstr", "1");
		new SystemAsyncTask(this, null).execute(requert);
		ProgressDialogUtil.show(activity, "提示", "正在加载数据", true, true);

	}

	public void clearCache() {
		Toast.makeText(this, "缓存清除完成", Toast.LENGTH_SHORT).show();

	}

	public void cancelPush() {

	}

}
