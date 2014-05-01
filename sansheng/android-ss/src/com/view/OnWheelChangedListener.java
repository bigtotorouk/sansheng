package com.view;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-23 下午5:18:39 declare:
 */
public interface OnWheelChangedListener {
	/**
	 * Callback method to be invoked when current item changed
	 * 
	 * @param wheel
	 *            the wheel view whose state has changed
	 * @param oldValue
	 *            the old value of current item
	 * @param newValue
	 *            the new value of current item
	 */
	void onChanged(WheelView wheel, int oldValue, int newValue);
}