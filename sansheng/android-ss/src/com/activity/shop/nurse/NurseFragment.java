package com.activity.shop.nurse;

import java.util.ArrayList;
import java.util.List;

import com.activity.CommonActivity;
import com.activity.shop.home.AdverstAdapter;
import com.activity.shop.home.BrandAdapter;
import com.lekoko.sansheng.R;
import com.sansheng.model.Adverst;
import com.sansheng.model.Brand;
import com.view.IndicatorView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 上午10:24:22 declare:
 */
public class NurseFragment extends Fragment {
	private View view;
	private ViewPager viewPager;
	private IndicatorView indicatorView;
	private ListView lvLife;
	private LayoutInflater layoutInflater;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = (View) inflater.inflate(R.layout.fragment_shopping_nurse, null);
		layoutInflater = inflater;
		initWidget();
		return view;
	}

	public void initWidget() {
		CommonActivity commonActivity = (CommonActivity) getActivity();
		lvLife = (ListView) view.findViewById(R.id.Lv_Nurse);
		List<Adverst> ads = new ArrayList<Adverst>();
		for (int i = 0; i < 5; i++) {
			Adverst adverst = new Adverst();
			ads.add(adverst);
		}
		View headView = layoutInflater.inflate(R.layout.layout_shopping_head,
				null);

		indicatorView = (IndicatorView) headView.findViewById(R.id.Indicator);
		indicatorView.setCount(5);
		viewPager = (ViewPager) headView.findViewById(R.id.ViewPaper_Banner);
		AdverstAdapter newsBannerAdapter = new AdverstAdapter(commonActivity,
				ads);
//		newsBannerAdapter.activity = getActivity();
		viewPager.setAdapter(newsBannerAdapter);

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				indicatorView.setCurrent(position);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		lvLife.addHeaderView(headView);
		List<Brand> brands = new ArrayList<Brand>();
		for (int i = 0; i < 10; i++) {
			Brand b = new Brand();
			brands.add(b);
		}
		CommonActivity comActivity = (CommonActivity) getActivity();

		BrandAdapter brandAdapter = new BrandAdapter(comActivity);
		brandAdapter.setBrands(brands);
		lvLife.setAdapter(brandAdapter);

	}
}
