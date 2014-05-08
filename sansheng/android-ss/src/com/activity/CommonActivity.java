package com.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.activity.balance.BalanceActivity;
import com.activity.company.InfoDetailActivity;
import com.activity.index.LoginActivity;
import com.activity.setting.about.AboutActivity;
import com.activity.shop.detail.ShopDetailActivity;
import com.application.CommonApplication;
import com.baidu.android.pushservice.CustomPushNotificationBuilder;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.http.BaseRequest;
import com.http.BaseRequestObj;
import com.http.ViewCommonResponse;
import com.lekoko.sansheng.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.push.Utils;
import com.sansheng.db.OrmDateBaseHelper;
import com.sansheng.model.Brand;
import com.sansheng.model.Room;
import com.sansheng.model.User;
import com.util.AESOperator;
import com.util.DateKeeper;

//push  ok
public class CommonActivity extends SherlockFragmentActivity {
	public static int THEME = R.style.Theme_Sherlock_Light;
	TextView tvTitle;
	private CommonApplication comApp;
	private OrmDateBaseHelper ormDateBaseHelper;
	public ImageLoader imageLoader = ImageLoader.getInstance();
	public DisplayImageOptions options;

	SharedPreferences userInfo;

	public static final String SHAREDPREFERENCES_NAME = "base_info";
	public static final String SHOP_ID = "SHOP_ID";
	public static final String SHOP_USER_ID = "SHOP_USER_ID";
	public static final String SUM_PRICE = "SUM_PRICE";
	public static final String SUM_PV = "SUM_PV";
	public static final String SHOP_CAR_COUNT = "SHOPCAR_COUNT";

	public static final String STORE_ID = "STORE_ID";
	public static final String BIAN_HAP = "BIANHAO";

	public static final String CI = "CI";
	public static final String MYUSER = "MY_USER";

	private static final String push_token = "user_id";

	public static final String ACTION_PUSH = "push";

	public static final String BUNDLE_TYPE = "type";
	public static final String BUNDEL_ID = "id";

	public final static String ACTION_NEWS = "news";

	public final static String ACTION_PRODUCT = "product";
	public final static String ACTION_FORM = "order";
	public final static String ACTION_APP = "app";
	public final static String LOGIN_TYPE = "login_type";

	public final static String ORDER_USER = "order";

	public final static String ORDER_USER_name = "order_name";
	public static String id;
	public static String action;
	static CommonPushReceiver commonPushReceiver;

	protected boolean neeecheckLogin = true;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setTheme(THEME);

		CommonApplication application = (CommonApplication) getApplication();
		application.activity = this;
		ActionBar actionBar = getSupportActionBar();
		// tvTitle = (TextView) getSupportActionBar().getCustomView()
		// .findViewById(R.id.Tv_Title);
		comApp = (CommonApplication) getApplication();
		ormDateBaseHelper = comApp.getOrmDateBaseHelper();
		actionBar = getSupportActionBar();
		actionBar.hide();

		options = new DisplayImageOptions.Builder()
		// .showStubImage(R.drawable.empty)
		// .showImageForEmptyUri(R.drawable.empty)
		// .showImageOnFail(R.drawable.empty).cacheInMemory()
				.cacheOnDisc().displayer(new RoundedBitmapDisplayer(0)).build();
		userInfo = getSharedPreferences(SHAREDPREFERENCES_NAME, 0);

		if (neeecheckLogin == true) {
			User user = getUser();
			if (user == null || user.getUserId() == null) {
				// Intent i = new Intent(this, LoginActivity.class);
				// startActivity(i);
				// finish();
			}
		}

		// try {
		// commonPushReceiver = new CommonPushReceiver();
		// // 生成一个IntentFilter对象
		// IntentFilter filter = new IntentFilter();
		// filter.addAction(ACTION_PUSH);
		// registerReceiver(commonPushReceiver, filter);
		// } catch (Exception e) {
		// Log.e("debug", "push  error");
		// }

		initPush();
	}

	public void initPush() {
		Resources resource = this.getResources();
		String pkgName = this.getPackageName();
		// Push: 以apikey的方式登录，一般放在主Activity的onCreate中。
		// 这里把apikey存放于manifest文件中，只是一种存放方式，
		// 您可以用自定义常量等其它方式实现，来替换参数中的Utils.getMetaValue(PushDemoActivity.this,
		// "api_key")
		// 通过share preference实现的绑定标志开关，如果已经成功绑定，就取消这次绑定
		if (PushManager.isConnected(this) == true) {
			Log.e("debug", "push  has  connect");
		} else {
			Log.e("debug", "push  not  connect");
		}
		if (!Utils.hasBind(getApplicationContext())) {
			PushManager.startWork(getApplicationContext(),
					PushConstants.LOGIN_TYPE_API_KEY,
					Utils.getMetaValue(this, "api_key"));
			// Push: 如果想基于地理位置推送，可以打开支持地理位置的推送的开关
			// PushManager.enableLbs(getApplicationContext());
			Log.e("debug", "push  not bind");
		}
		Log.e("debug", "push  has bind");
		// Push: 设置自定义的通知样式，具体API介绍见用户手册，如果想使用系统默认的可以不加这段代码
		// 请在通知推送界面中，高级设置->通知栏样式->自定义样式，选中并且填写值：1，
		// 与下方代码中 PushManager.setNotificationBuilder(this, 1, cBuilder)中的第二个参数对应
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

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Intent intent = getIntent();

		if (action == null) {
			return;
		}

		if (action.equals(ACTION_NEWS)) {
			intent = new Intent(this, InfoDetailActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra(InfoDetailActivity.TITLE, "新闻详情");
			intent.putExtra("key", id);
			intent.putExtra("url", id);
			this.startActivity(intent);

		} else if (action.equals(ACTION_FORM)) {
			intent = new Intent(this, BalanceActivity.class);
			intent.putExtra(BalanceActivity.META_TYPE, id);
			intent.setAction(BalanceActivity.ACTION_PUSH);
			this.startActivity(intent);
		} else if (action.equals(ACTION_PRODUCT)) {
			intent = new Intent(this, ShopDetailActivity.class);
			intent.setAction(null);
			Bundle b = new Bundle();
			Brand brand = new Brand();
			brand.setId(id);
			b.putSerializable(ShopDetailActivity.INTNET_PRODUCT, brand);
			intent.putExtras(b);
			this.startActivity(intent);
		} else if (action.equals(ACTION_APP)) {
			intent = new Intent(this, AboutActivity.class);
			this.startActivity(intent);
		}
		action = "";
	}

	@Override
	public void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		super.startActivity(intent);
		Activity parent = getParent();
		if (parent != null) {
			slideInFromRight(parent);
		} else {
			slideInFromRight(this);
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// unregisterReceiver(commonPushReceiver);
	}

	public BaseRequest createRequest(int action) {
		BaseRequest commonRequest = new BaseRequest();
		Map<String, String> params = new HashMap<String, String>();
		commonRequest.setAction(action);
		commonRequest.setParams(params);
		return commonRequest;
	}

	public BaseRequest createRequestWithUserId(int action) {
		BaseRequest commonRequest = new BaseRequest();
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", getUserId());
		commonRequest.setAction(action);
		commonRequest.setParams(params);
		return commonRequest;
	}

	public BaseRequestObj createRequestWithUserIdObj(int action) {
		BaseRequestObj commonRequest = new BaseRequestObj();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", getUserId());
		commonRequest.setAction(action);
		commonRequest.setParams(params);
		return commonRequest;
	}

	public String getStr(int strId) {
		String string = getResources().getString(strId);
		return string;
	}

	public void setTitle(String title) {
		tvTitle.setText(title);
	}

	public CommonApplication getComApp() {
		return comApp;
	}

	public OrmDateBaseHelper getOrmDateBaseHelper() {
		return ormDateBaseHelper;
	}

	public void refresh(ViewCommonResponse viewCommonResponse) {
		int action = viewCommonResponse.getAction();
		switch (action) {
		// 获取appcode请求结果不在此处理

		}

		int code = viewCommonResponse.getHttpCode();
		System.out.println("HttpCode:" + code);
		switch (code) {
		case 200:
			break;
		case 404:
		case 500:
			showDataLoadingErrToast();
			break;
		case 544:
			showDataLoadingErrToast("加载超时!");
			break;
		case 545:
			Log.i("TAG", "canel request");
			break;
		default:
			showToast("未知错误！");
			break;
		}
	}

	// 显示数据加载失败土司
	protected void showDataLoadingErrToast() {
		Toast.makeText(this, "数据载入失败", Toast.LENGTH_SHORT).show();
	}

	// 显示数据加载失败土司
	protected void showDataLoadingErrToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	public String getUserId() {
		//return "H7Mud3IiaWjWqdL4J4qEJA==";
		return "TYgQm3jJucgti1xy2Hd9zA=="; // 商家
		// return getAesUserName();
	}

	public String getUserName() {
		return "公司";
	}

	public void saveRoom(Room room) {
		Editor editor = userInfo.edit();
		editor.putString(SHOP_ID, room.getShopid());
		editor.putString(SHOP_USER_ID, room.getShopuserid());
		editor.commit();
	}

	public Room getRoom() {
		Room room = new Room();
		String shopId = userInfo.getString(SHOP_ID, "");
		String shopUserId = userInfo.getString(SHOP_ID, "");
		room.setShopid(shopId);
		room.setShopuserid(shopUserId);
		return room;
	}

	public void saveSumPrice(String sumPrice) {
		Editor editor = userInfo.edit();
		editor.putString(SUM_PRICE, sumPrice);
		editor.commit();
	}

	public String saveSumPv() {
		String sumPrice = userInfo.getString(SUM_PRICE, "0");
		return sumPrice;
	}

	public void saveSumPv(String sumPrice) {
		Editor editor = userInfo.edit();
		editor.putString(SUM_PV, sumPrice);
		editor.commit();
	}

	public String getSumPrice() {
		String sumPrice = userInfo.getString(SUM_PRICE, "0");
		return sumPrice;
	}

	public String getSumPv() {
		String sumPrice = userInfo.getString(SUM_PV, "0");
		return sumPrice;
	}

	public void saveBainHao(String bianhao) {
		Editor editor = userInfo.edit();
		editor.putString(BIAN_HAP, bianhao);
		editor.commit();
	}

	public int getShopCarCount() {
		int sumPrice = userInfo.getInt(SHOP_CAR_COUNT, 0);
		return sumPrice;
	}

	public void saveShopCarCount(int bianhao) {
		Editor editor = userInfo.edit();
		editor.putInt(SHOP_CAR_COUNT, bianhao);
		editor.commit();
	}

	public void saveLoginType(String type) {
		Editor editor = userInfo.edit();
		editor.putString(LOGIN_TYPE, type);
		editor.commit();
	}

	public String getLoginType() {
		User user = getUser();
		// String sumPrice = userInfo.getString(LOGIN_TYPE,
		// "" + user.getLogintype());

		return 0 + "";
	}

	public void saveStoreId(String storeId) {
		Editor editor = userInfo.edit();
		editor.putString(STORE_ID, storeId);
		editor.commit();
	}

	public String getStoreId() {
		String sumPrice = userInfo.getString(STORE_ID, "0");
		return sumPrice;
	}

	public void saveOrderUserId(String storeId) {
		Editor editor = userInfo.edit();
		editor.putString(ORDER_USER, storeId);
		editor.commit();
	}

	public String getOrderUserId() {
		// String sumPrice = userInfo.getString(ORDER_USER, "0");

		return getUserId();
	}

	public void saveOrderUserName(String name) {
		Editor editor = userInfo.edit();
		editor.putString(ORDER_USER_name, name);
		editor.commit();
	}

	public String getOrderUserName() {
		String sumPrice = userInfo.getString(ORDER_USER_name, "0");
		return sumPrice;
	}

	public String getBianHao() {
		String sumPrice = userInfo.getString(BIAN_HAP, "0");
		return sumPrice;
	}

	public void cleanUser() {
		User u = new User();

		DateKeeper.saveData(this, MYUSER, u);
	}

	// public void saveUSer(String user) {
	// // Editor editor = userInfo.edit();
	// // editor.putString(USER, user);
	// // editor.commit();
	// }

	public void closeKeyBoard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
		// 得到InputMethodManager的实例
		if (imm.isActive()) {
			// 如果开启
			imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
					InputMethodManager.HIDE_NOT_ALWAYS);
			// 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
		}
	}

	private long lastToast = 0;
	public Toast toast;

	// 土司提示
	public void showToast(String msg) {
		long toastTime = System.currentTimeMillis();

		Log.e("debug", "t1：" + toastTime + "  last" + lastToast);

		if (toastTime - lastToast > 3000) {
			lastToast = toastTime;
			toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
			toast.show();
		}

	}

	public void saveUser(User user) {
		DateKeeper.saveData(this, MYUSER, user);
	}

	public String getAesUserName() {
		User user = (User) DateKeeper.getData(this, MYUSER);
		String AesUser = "";
		try {
			AesUser = AESOperator.getInstance().encrypt(user.getUserId() + "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AesUser;
	}

	// add by bigtotoro,  因为DateKeeper.getData老是为空
	public User getUser() {
		User user = (User) DateKeeper.getData(this, MYUSER);
		/*User user = new User();
		user.setUserId("TYgQm3jJucgti1xy2Hd9zA");
		user.setLogintype(1);*/
		/*User user = new User();
		user.setUserId("TYgQm3jJucgti1xy2Hd9zA");
		user.setLogintype(1);
		user.setShopId(0 + "");*/
		return user;
	}

	public String getTokens() {
		String token = userInfo.getString(push_token, "");
		return token;
	}

	public void saveToken(String token) {
		Editor editor = userInfo.edit();
		editor.putString(push_token, token);
		editor.commit();
	}

	public static class CommonPushReceiver extends BroadcastReceiver {

		public static boolean isInited = false;

		public CommonPushReceiver() {
			isInited = true;
		}

		// 接收到广播会被自动调用
		@Override
		public void onReceive(Context context, Intent intent) {
			// 从Intent中获取action
			Log.e("debufg", " commonPushReceiver  is  ok");
			if (intent.getAction().endsWith(ACTION_PUSH)) {
				// Intent i = new Intent(context, LoginActivity.class);
				// i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				// context.startActivity(i);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				Bundle bundle = intent.getExtras();
				if (bundle != null) {
					String action = bundle.getString(BUNDLE_TYPE);
					String id = bundle.getString(BUNDEL_ID);
					if (action.equals(ACTION_NEWS)) {
						intent = new Intent(context, InfoDetailActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.putExtra(InfoDetailActivity.TITLE, "新闻详情");
						intent.putExtra("key", id);
						intent.putExtra("url", id);
						context.startActivity(intent);

					} else if (action.equals(ACTION_FORM)) {
						intent = new Intent(context, BalanceActivity.class);
						intent.putExtra(BalanceActivity.META_TYPE, id);
						intent.setAction(BalanceActivity.ACTION_PUSH);
						context.startActivity(intent);
					} else if (action.equals(ACTION_PRODUCT)) {
						intent = new Intent(context, ShopDetailActivity.class);
						intent.setAction(null);
						Bundle b = new Bundle();
						Brand brand = new Brand();
						brand.setId(id);
						b.putSerializable(ShopDetailActivity.INTNET_PRODUCT,
								brand);
						intent.putExtras(b);
						context.startActivity(intent);
					} else if (action.equals(ACTION_APP)) {
						intent = new Intent(context, AboutActivity.class);
						context.startActivity(intent);
					}
				}
			}
		}
	}

	/**
	 * 由下往上滑动进入
	 */
	public void slideInFromBottom(Activity activit) {
		activit.overridePendingTransition(R.anim.slide_in_from_bottom,
				R.anim.nothing);
	}

	/**
	 * 由上往下滑动进入
	 */
	public void slideInFromTop(Activity activity) {
		activity.overridePendingTransition(R.anim.slide_in_from_top,
				R.anim.nothing);
	}

	/**
	 * 由左往右滑动进入
	 */
	public void slideInFromLeft(Activity activity) {
		activity.overridePendingTransition(R.anim.slide_in_from_left,
				R.anim.nothing);
	}

	/**
	 * 由右往左滑动进入
	 */
	public void slideInFromRight(Activity activity) {
		activity.overridePendingTransition(R.anim.slide_in_from_right, 0);
	}

	/**
	 * 由右往左滑动进入and Fade in
	 */
	public void slideInFromRightAndFadeIn(Activity activity) {
		activity.overridePendingTransition(
				R.anim.slide_in_from_right_and_fade_in, 0);
	}

	/**
	 * 由上往下滑动退出
	 */
	public void slideOutToBottom(Activity activity) {
		activity.overridePendingTransition(R.anim.nothing,
				R.anim.slide_out_to_bottom);
	}

	/**
	 * 由下往上滑动退出
	 */
	public void slideOutToTop(Activity activity) {
		activity.overridePendingTransition(R.anim.nothing,
				R.anim.slide_out_to_top);
	}

	/**
	 * 由下往上滑动进入
	 */
	public void slideInToTop(Activity activity) {
		activity.overridePendingTransition(R.anim.slide_in_from_bottom, 0);
	}

	/**
	 * 由右往左滑动退出
	 */
	public void slideOutToLeft(Activity activity) {
		activity.overridePendingTransition(R.anim.nothing,
				R.anim.slide_out_to_left);
	}

	/**
	 * 由左往右滑动退出
	 */
	public void slideOutToRight(Activity activity) {
		activity.overridePendingTransition(R.anim.nothing,
				R.anim.slide_out_to_right);
	}

	/**
	 * 由右往左滑动退出and fade out
	 */
	public void slideOutToRightAndFadeOut() {
		overridePendingTransition(R.anim.nothing,
				R.anim.slide_out_to_right_and_fade_out);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
		Activity parent = getParent();
		if (parent != null) {
			slideInFromRight(parent);
		} else {
			slideOutToRight(this);
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		Activity parent = getParent();
		if (parent != null) {
			slideInFromRight(parent);
		} else {
			slideOutToRight(this);
		}
	}
}
