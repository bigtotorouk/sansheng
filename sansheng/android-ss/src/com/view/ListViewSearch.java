package com.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.activity.custome.CustomeAdapter;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time��2013-6-17 ����04:49:19 file declare:
 */
public class ListViewSearch extends ListView {

	private CustomeAdapter searchAdapter;
	private mySearchView searchView;
	private final static int MSG_NOT_FOUND = 1;
	private final static int MSG_SHOW_RESULT = 2;
	private final static int MSG_ERROR = 3;
	private UiHandler uiHandler;
	private com.view.mySearchView mySearchView;

	public ListViewSearch(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	public ListViewSearch(Context context) {
		super(context);

		init();
	}

	public void init() {
		uiHandler = new UiHandler();
	}

	public void queryUser(String user) {
		final String u = user;
		new Thread() {
			@Override
			public void run() {
				super.run();

			}
		}.start();

	}

	public mySearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(mySearchView searchView) {
		this.searchView = searchView;
	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_NOT_FOUND:

				break;

			case MSG_SHOW_RESULT:

				break;
			}

		}
	}

	public CustomeAdapter getSearchAdapter() {
		return searchAdapter;
	}

	public void setSearchAdapter(CustomeAdapter searchAdapter) {
		this.searchAdapter = searchAdapter;
		this.setAdapter(searchAdapter);
	}

	public com.view.mySearchView getMySearchView() {
		return mySearchView;
	}

	public void setMySearchView(com.view.mySearchView s) {
		this.mySearchView = s;
		 
	}

}
