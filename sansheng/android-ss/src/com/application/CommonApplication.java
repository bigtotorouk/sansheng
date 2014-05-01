package com.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.sansheng.db.OrmDateBaseHelper;

public class CommonApplication extends Application {
	private OrmDateBaseHelper ormDateBaseHelper;
	public static final String ACTION_PUSH = "push";

	public static final String BUNDLE_TYPE = "type";
	public static final String BUNDEL_ID = "id";

	public final static String ACTION_NEWS = "news";

	public final static String ACTION_PRODUCT = "product";
	public final static String ACTION_FORM = "order";
	public final static String ACTION_APP = "app";

	private Context context;

	public static Activity activity;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		if (false && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectAll().penaltyDialog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectAll().penaltyDeath().build());
		}

		super.onCreate();
		context = this;
		Log.e("debug", "commonApplication  is  oncreate");
		ormDateBaseHelper = new OrmDateBaseHelper(this, "cloud_contact", null,
				1);
		initImageLoader(getApplicationContext());

	}

	public OrmDateBaseHelper getOrmDateBaseHelper() {
		return ormDateBaseHelper;
	}

	public void setOrmDateBaseHelper(OrmDateBaseHelper ormDateBaseHelper) {
		this.ormDateBaseHelper = ormDateBaseHelper;
	}

	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// Not necessary in common
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

}
