package com.activity.shop.life;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;

import com.activity.CommonActivity;
import com.activity.company.InfoDetailActivity;
import com.activity.shop.detail.ShopDetailActivity;
import com.activity.shop.home.AdverstAdapter;
import com.activity.shop.home.BrandAdapter;
import com.http.BrandAndAdverst;
import com.lekoko.sansheng.R;
import com.push.Opration;
import com.sansheng.model.Adverst;
import com.sansheng.model.Brand;
import com.view.ChildViewPager;
import com.view.ChildViewPager.OnSingleTouchListener;
import com.view.IndicatorView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 上午10:24:12 declare:
 */
public class LifeFragment extends Fragment {

	private View view;
	private ChildViewPager viewPager;
	private IndicatorView indicatorView;
	private ListView lvLife;
	private LayoutInflater layoutInflater;

	BrandAdapter brandAdapter;
	AdverstAdapter adsAdapter;
	private int id;
	CommonActivity commonActivity;
	View headView;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = (View) inflater.inflate(R.layout.fragment_shopping_life, null);
		layoutInflater = inflater;

		initWidget();
		return view;
	}

	public void initWidget() {
		commonActivity = (CommonActivity) getActivity();
		lvLife = (ListView) view.findViewById(R.id.Lv_Life);
		lvLife.setDivider(null);
		List<Adverst> ads = new ArrayList<Adverst>();
		for (int i = 0; i < 5; i++) {
			Adverst adverst = new Adverst();
			ads.add(adverst);
		}
		headView = layoutInflater.inflate(R.layout.layout_shopping_head, null);

		indicatorView = (IndicatorView) headView.findViewById(R.id.Indicator);
		indicatorView.setCount(0);
		viewPager = (ChildViewPager) headView
				.findViewById(R.id.ViewPaper_Banner);

		adsAdapter = new AdverstAdapter(commonActivity, ads);
		viewPager.setAdapter(adsAdapter);

		viewPager.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e("debug", "viewpager  onclick");
			}
		});

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

		commonActivity = (CommonActivity) getActivity();

		brandAdapter = new BrandAdapter(commonActivity);
		brandAdapter.setBrands(brands);
		lvLife.setAdapter(brandAdapter);

	}

	public void update(BrandAndAdverst ba) {
		List<Adverst> adversts = ba.getAdversts();
		if (adsAdapter == null || viewPager == null) {
			return;
		}
		adsAdapter.viewPager = viewPager;
		if (adversts != null) {
			indicatorView.setCount(adversts.size());
			adsAdapter.setNews(adversts);
			adsAdapter.notifyDataSetChanged();
		}
		// adsAdapter.update();
		List<Brand> brands = ba.getBrands();
		if (brands != null) {
			brandAdapter.setBrands(brands);
			brandAdapter.notifyDataSetChanged();
		}

		android.widget.AbsListView.LayoutParams lp = (android.widget.AbsListView.LayoutParams) headView
				.getLayoutParams();
		Display display = commonActivity.getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		float scale = ((float) 640) / ((float) width);
		int h = height = (int) (350 / scale) - 1;
		lp.height = h;
		lp.width = width;
		headView.setLayoutParams(lp);

		viewPager.setOnSingleTouchListener(new OnSingleTouchListener() {

			@Override
			public void onSingleTouch() {
				Log.e("debug", "my click");
				int current = viewPager.getCurrentItem();
				Adverst ad = adsAdapter.getAds().get(current);
				Intent i = new Intent(commonActivity, InfoDetailActivity.class);
				// if (ad.getTitle() != null) {
				// i.putExtra(InfoDetailActivity.TITLE, ad.getTitle());
				// }
				// if (ad.getInfo() != null) {
				// i.putExtra(InfoDetailActivity.URL, ad.getInfo());
				// }
				// Bundle bundle = new Bundle();
				// bundle.putString("title", "新闻详情");
				// bundle.putString("key", ad.getInfo());
				// bundle.putString("url", ad.getInfo());
				// i.putExtras(bundle);
				// commonActivity.startActivity(i);

				if (ad.getInfo() == null || ad.getInfo().equals("")) {
					return;
				}

				Opration opration = new Opration();
				try {
					opration.parse(ad.getInfo());
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (opration.getOpra().equals("product")) {
					Intent intent = new Intent(commonActivity,
							ShopDetailActivity.class);
					intent.setAction("push");
					Bundle bundle = new Bundle();
					bundle.putString(ShopDetailActivity.META_TYPE,
							opration.getNumber());
					intent.putExtras(bundle);
					commonActivity.startActivity(intent);

				} else if (opration.getOpra().equals("news")) {
					Intent intent = new Intent(commonActivity,
							InfoDetailActivity.class);
					intent.setAction("push");
					Bundle bundle = new Bundle();
					bundle.putString("title", "新闻详情");
					bundle.putString("key", opration.getNumber());
					bundle.putString("url", opration.getNumber());
					intent.putExtras(bundle);
					commonActivity.startActivity(intent);
				}

			}

		});
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LifeFragment [view=" + view + ", viewPager=" + viewPager
				+ ", indicatorView=" + indicatorView + ", lvLife=" + lvLife
				+ ", layoutInflater=" + layoutInflater + ", brandAdapter="
				+ brandAdapter + ", adsAdapter=" + adsAdapter + ", id=" + id
				+ ", commonActivity=" + commonActivity + "]";
	}

}
