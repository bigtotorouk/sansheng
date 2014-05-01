package com.activity.schedule;

import com.http.ViewCommonResponse;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-19 下午9:54:22 declare:
 */
public class CommonFragment extends Fragment implements CallBack {

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

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
		Toast.makeText(this.getActivity(), "数据载入失败", Toast.LENGTH_SHORT).show();
	}

	// 显示数据加载失败土司
	protected void showDataLoadingErrToast(String msg) {
		Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
	}

	// 土司提示
	protected void showToast(String msg) {
		Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
	}

}
