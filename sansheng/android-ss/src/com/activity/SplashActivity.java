package com.activity;

import com.activity.balance.BalanceActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-11-3 下午9:29:41 declare:
 */
public class SplashActivity extends Activity {
	public static String id;
	public static String action;
	public final static String ACTION_NEWS = "news";
	public final static String ACTION_FORM = "form";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Intent intent = getIntent();
		if (intent != null) {
			if (action == null) {
				return;
			}
			if (action.equals(ACTION_NEWS)) {

			} else if (action.equals(ACTION_FORM)) {
				intent.setClass(this, BalanceActivity.class);
				intent.putExtra(BalanceActivity.META_TYPE, id);
				intent.setAction(BalanceActivity.ACTION_PUSH);
				startActivity(intent);
			}
		}
		finish();
	}
}
