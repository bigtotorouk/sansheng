package com.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface.OnCancelListener;

public class ProgressDialogUtil {
	private static ProgressDialog dialog;

	private ProgressDialogUtil() {

	}

	public static void show(Activity activity, String title, String content,
			boolean flag1, boolean flag2, OnCancelListener listener) {
		ProgressDialogUtil.close();
		dialog = ProgressDialog.show(activity, title, content, flag1, flag2,
				listener);
//		dialog.setCancelable(false);

	}

	public static void show(Activity activity, String title, String content,
			boolean flag1, boolean flag2) {
		ProgressDialogUtil.close();
		dialog = ProgressDialog.show(activity, title, content, flag1, flag2);
//		dialog.setCancelable(false);
	}

	public static void close() {
		if (dialog != null)
			dialog.dismiss();
	}

}
