package com.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 上午10:57:45 declare:
 */    
public class StaticViewPager extends ViewPager {

	public StaticViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public StaticViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public boolean dispatchTouchEvent(MotionEvent ev) {
//		// TODO Auto-generated method stub
//		// boolean res = false;
//		boolean res = super.dispatchTouchEvent(ev);
//		Log.e("debug", "dispatchTouchEvent:" + res);
//		return res;
//	}
//
//	@Override  
//	public boolean onTouchEvent(MotionEvent arg0) {
//		boolean res = false;
//		// boolean res = super.dispatchTouchEvent(ev);
//		Log.e("debug", "onTouchEvent:" + res);
//		return res;
//		// return false;
//	}

}
