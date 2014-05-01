package com.activity.company.community;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lekoko.sansheng.R;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.Lovenewsadsopen;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-17 上午11:47:22 declare:
 */
public class BannnerAdapter extends PagerAdapter {

	private List<Lovenewsadsopen> community;
	private LayoutInflater layoutInflater;
	private View currentView;
	public static Context activity;

	public BannnerAdapter(Context context, List<Lovenewsadsopen> ns) {
		community = ns;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public List<Lovenewsadsopen> getCommunity() {
		return community;
	}

	public void setCommunity(List<Lovenewsadsopen> community) {
		this.community = community;
	}

	@Override
	public int getCount() {
		if (community == null) {
			return 0;
		} else {
			return community.size();
		}
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		currentView = (View) object;
		return view.equals(object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		View view = (View) layoutInflater.inflate(
				R.layout.layout_company_community_banner, null);
		((ViewPager) container).addView(view, 0);
		return view;

	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);

	}

}
