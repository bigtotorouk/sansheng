package com.push;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.activity.index.IndexActivity;
import com.activity.index.LoginActivity;
import com.activity.shop.detail.ShopDetailActivity;
import com.baidu.android.pushservice.PushConstants;

/**
 * Push消息处理receiver
 */
public class PushMessageReceiver extends BroadcastReceiver {
	/** TAG to Log */
	public static final String TAG = PushMessageReceiver.class.getSimpleName();

	AlertDialog.Builder builder;

	/**
	 * @param context
	 *            Context
	 * @param intent
	 *            接收的intent
	 */
	@Override
	public void onReceive(final Context context, Intent intent) {

		Log.d(TAG, ">>> Receive intent: \r\n" + intent);

		if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
			// 获取消息内容
			String message = intent.getExtras().getString(
					PushConstants.EXTRA_PUSH_MESSAGE_STRING);

			// 消息的用户自定义内容读取方式
			Log.i(TAG, "onMessage: " + message);

			// 自定义内容的json串
			Log.d(TAG,
					"EXTRA_EXTRA = "
							+ intent.getStringExtra(PushConstants.EXTRA_EXTRA));

			// 用户在此自定义处理消息,以下代码为demo界面展示用
			Intent responseIntent = null;
			responseIntent = new Intent(Utils.ACTION_MESSAGE);
			responseIntent.putExtra(Utils.EXTRA_MESSAGE, message);
			responseIntent.setClass(context, IndexActivity.class);
			responseIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// context.startActivity(responseIntent);

		} else if (intent.getAction().equals(PushConstants.ACTION_RECEIVE)) {
			// 处理绑定等方法的返回数据
			// PushManager.startWork()的返回值通过PushConstants.METHOD_BIND得到

			// 获取方法Øß
			final String method = intent
					.getStringExtra(PushConstants.EXTRA_METHOD);
			// 方法返回错误码。若绑定返回错误（非0），则应用将不能正常接收消息。
			// 绑定失败的原因有多种，如网络原因，或access token过期。
			// 请不要在出错时进行简单的startWork调用，这有可能导致死循环。
			// 可以通过限制重试次数，或者在其他时机重新调用来解决。
			int errorCode = intent.getIntExtra(PushConstants.EXTRA_ERROR_CODE,
					PushConstants.ERROR_SUCCESS);
			String content = "";
			if (intent.getByteArrayExtra(PushConstants.EXTRA_CONTENT) != null) {
				// 返回内容
				content = new String(
						intent.getByteArrayExtra(PushConstants.EXTRA_CONTENT));
			}

			// 用户在此自定义处理消息,以下代码为demo界面展示用
			Log.d(TAG, "onMessage: method : " + method);
			Log.d(TAG, "onMessage: result : " + errorCode);
			Log.d(TAG, "onMessage: content : " + content);
			// Toast.makeText(
			// context,
			// "method : " + method + "\n result: " + errorCode
			// + "\n content = " + content, Toast.LENGTH_SHORT)
			// .show();
			Intent responseIntent = null;
			responseIntent = new Intent(Utils.ACTION_RESPONSE);
			responseIntent.putExtra(Utils.RESPONSE_METHOD, method);
			responseIntent.putExtra(Utils.RESPONSE_ERRCODE, errorCode);
			responseIntent.putExtra(Utils.RESPONSE_CONTENT, content);
			responseIntent.setClass(context, LoginActivity.class);
			responseIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			handleIntent(responseIntent, context);
			// context.startActivity(responseIntent);
			// 可选。通知用户点击事件处理
		} else if (intent.getAction().equals(
				PushConstants.ACTION_RECEIVER_NOTIFICATION_CLICK)) {
			Log.d(TAG, "intent=" + intent.toUri(0));
			// 自定义内容的json串
			Log.d(TAG,
					"EXTRA_EXTRA = "
							+ intent.getStringExtra(PushConstants.EXTRA_EXTRA));

			String title = intent
					.getStringExtra(PushConstants.EXTRA_NOTIFICATION_TITLE);
			String content = intent
					.getStringExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT);

			Bundle bundle = intent.getExtras();

			// String extraStr =
			// intent.getStringExtra(PushConstants.EXTRA_EXTRA);

			String extraStr = bundle.getString("caozuokey");
			if (extraStr == null) {
				return;
			}
			Opration opration = new Opration();

			opration.parse(extraStr);

			if (opration.getNumber() != null && opration.getOpra() != null) {
				IndexActivity.id = opration.getNumber();
			} else {
				return;
			}
			Intent aIntent = null;

			bundle = new Bundle();
			IndexActivity.action = opration.getOpra();
			IndexActivity.id = opration.getNumber();
			if (opration.getOpra().equals("order")) {
				aIntent = new Intent(context, IndexActivity.class);
				bundle.putString("id", opration.getNumber());
				aIntent.putExtras(bundle);
				aIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT,
						content);
				aIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				aIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

			} else if (opration.getOpra().equals("product")) {
				aIntent = new Intent(context, IndexActivity.class);

				bundle.putString("id", opration.getNumber());
				aIntent.putExtras(bundle);

				aIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT,
						content);
				aIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				aIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
			}

			else if (opration.getOpra().equals("news")) {
				aIntent = new Intent(context, IndexActivity.class);
				bundle.putString("id", opration.getNumber());
				aIntent.putExtras(bundle);
				aIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT,
						content);
				aIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				aIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				aIntent.setAction(ShopDetailActivity.ACTION_NEW);
			} else if (opration.getOpra().equals("app")) {
				aIntent = new Intent(context, IndexActivity.class);
				bundle.putString("id", opration.getNumber());
				aIntent.putExtras(bundle);

				aIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT,
						content);
				aIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				aIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				aIntent.setAction(ShopDetailActivity.ACTION_NEW);
			}

			Log.e("debug", "extra" + extraStr);

			Log.e("debug", "push content" + extraStr);
			Bundle b = new Bundle();
			b.putString("type", "push");
			context.startActivity(aIntent);

		}
	}

	private void handleIntent(Intent intent, Context c) {
		String action = intent.getAction();

		if (Utils.ACTION_RESPONSE.equals(action)) {

			String method = intent.getStringExtra(Utils.RESPONSE_METHOD);

			if (PushConstants.METHOD_BIND.equals(method)) {
				String toastStr = "";
				int errorCode = intent.getIntExtra(Utils.RESPONSE_ERRCODE, 0);
				if (errorCode == 0) {
					String content = intent
							.getStringExtra(Utils.RESPONSE_CONTENT);
					String appid = "";
					String channelid = "";
					String userid = "";
					try {
						JSONObject jsonContent = new JSONObject(content);
						JSONObject params = jsonContent
								.getJSONObject("response_params");
						appid = params.getString("appid");
						channelid = params.getString("channel_id");
						userid = params.getString("user_id");
					} catch (JSONException e) {
						Log.e(Utils.TAG, "Parse bind json infos error: " + e);
					}

					SharedPreferences sp = PreferenceManager
							.getDefaultSharedPreferences(c);
					Editor editor = sp.edit();
					editor.putString("appid", appid);
					editor.putString("channel_id", channelid);
					editor.putString("user_id", userid);
					editor.commit();

					System.out.println("***********:" + appid + "," + channelid
							+ "," + userid);

					toastStr = "Bind Success";
				} else {
					toastStr = "Bind Fail, Error Code: " + errorCode;
					if (errorCode == 30607) {
						Log.d("Bind Fail", "update channel token-----!");
					}
				}

				// Toast.makeText(this, toastStr, Toast.LENGTH_LONG).show();
			}
		} else {
			Log.i(Utils.TAG, "Activity normally start!");
		}

		Intent myIntent = new Intent();// 创建Intent对象
		myIntent.setAction("BROAD_BIND");
		c.sendBroadcast(myIntent);// 发送广播

	}
}
