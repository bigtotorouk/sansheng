package com.view;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author deng
 */
public class TabController {

	private List<BtnTab> tabs;
	private int current;
	private TabListenner tabListenner;

	public TabController() {
		tabs = new ArrayList<BtnTab>();

	}

	public BtnTab getItem(int i) {
			return tabs.get(i);
	}

	public void selected(int index) {
		tabs.get(current).unsleetced();
		tabs.get(index).selected();
		current = index;
	}

	public void addTab(BtnTab tab) {
		final int tabindex = tabs.size();
		tab.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e("debug", "click" + tabindex);
				selected(tabindex);
				tabListenner.onTabClick(tabindex);
			}
		});

		tabs.add(tab);
	}

	public interface TabListenner {
		public void onTabClick(final int index);
	}

	public TabListenner getTabListenner() {
		return tabListenner;
	}

	public void setTabListenner(TabListenner tabListenner) {
		this.tabListenner = tabListenner;
	}

}
