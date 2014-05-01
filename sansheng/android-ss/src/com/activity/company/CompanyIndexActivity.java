package com.activity.company;

import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore.Action;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.activity.CommonActivity;
import com.activity.company.announcement.AnnouncementActivity;
import com.activity.company.brands.BrandsActivity;
import com.activity.company.community.CommunityActivity;
import com.activity.company.cultural.CulturalActivity;
import com.activity.company.historical.HistoricalActivity;
import com.activity.company.honor.Honour_Activity;
import com.activity.company.introduce.IntroduceAcitity;
import com.activity.company.news.NewsActivity;
import com.activity.company.produce.ProduceActivity;
import com.activity.company.quality.QualityActivity;
import com.activity.company.sale.SaleActivity;
import com.activity.index.IndexActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.LocalInfo;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.ItemButton;

/**
 * 
 * 公司主界面
 * 
 * @author retryu
 * 
 */
public class CompanyIndexActivity extends CommonActivity implements
		OnClickListener {
	// 顶部HeadBar
	HeadBar headBar;

	View l_one;

	private Activity activity;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_company_index);
		View btnAnnouncement = (View) findViewById(R.id.Btn_Announcement);
		btnAnnouncement.setOnClickListener(this);

		headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.company_info));
		headBar.setWidgetClickListener(this);
		headBar.setRightType(BtnType.empty);
		initWidget();
	}

	public void initWidget() {

		View btnNews = (View) findViewById(R.id.Btn_News);
		View btnSlaes = (View) findViewById(R.id.Btn_Sales);

		ItemButton btnIntroduce = (ItemButton) findViewById(R.id.Btn_Indroduction);
		ItemButton btnHistory = (ItemButton) findViewById(R.id.Btn_Histoty);
		ItemButton btnHornor = (ItemButton) findViewById(R.id.Btn_Company_Honor);

		ItemButton btnCulture = (ItemButton) findViewById(R.id.Btn_Company_Culture);
		ItemButton btnBrand = (ItemButton) findViewById(R.id.Btn_Brands);
		// View btnChairman = (View) findViewById(R.id.Btn_Chairman);

		ItemButton btnIndustry = (ItemButton) findViewById(R.id.Btn_Company_Community);
		ItemButton btnWorld = (ItemButton) findViewById(R.id.Btn_Company_Quality);
		ItemButton btnProduce = (ItemButton) findViewById(R.id.Btn_Company_Produce);
		btnNews.setOnClickListener(this);
		btnSlaes.setOnClickListener(this);

		l_one = findViewById(R.id.Layout_Btn_one);
		activity = this;
		btnIntroduce.btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("debug", "url");
				Intent i;
				Bundle bundle = new Bundle();
				i = new Intent(activity, InfoDetailActivity.class);
				i.putExtra(InfoDetailActivity.TITLE, "公司简介");
				i.putExtra(
						InfoDetailActivity.URL,
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=9");
				i.setAction("push");
				bundle.putString("title", "公司简介" + "");
				bundle.putString(
						"key",
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=9");
				bundle.putString(
						"url",
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=9");
				i.putExtras(bundle);

				activity.startActivity(i);
			}
		});

		btnProduce.btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("debug", "url");
				Intent i;
				Bundle bundle = new Bundle();
				i = new Intent(activity, InfoDetailActivity.class);
				i.putExtra(InfoDetailActivity.TITLE, "产品概况");
				i.putExtra(
						InfoDetailActivity.URL,
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=10");
				i.setAction("push");
				bundle.putString("title", "产品概况");
				bundle.putString(
						"key",
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=10");
				bundle.putString(
						"url",
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=10");
				i.putExtras(bundle);
				activity.startActivity(i);
			}
		});
		btnWorld.btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("debug", "url");
				Intent i;
				Bundle bundle = new Bundle();
				i = new Intent(activity, InfoDetailActivity.class);
				i.putExtra(InfoDetailActivity.TITLE, "优质产品");
				i.putExtra(
						InfoDetailActivity.URL,
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=11");
				i.setAction("push");
				bundle.putString("title", "优质产品" + "");
				bundle.putString(
						"key",
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=11");
				bundle.putString(
						"url",
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=11");
				i.putExtras(bundle);

				activity.startActivity(i);
			}
		});

		btnCulture.btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("debug", "url");
				Intent i;
				Bundle bundle = new Bundle();
				i = new Intent(activity, InfoDetailActivity.class);
				i.putExtra(InfoDetailActivity.TITLE, "企业文化");
				i.putExtra(
						InfoDetailActivity.URL,
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=12");
				i.setAction("push");
				bundle.putString("title", "企业文化");
				bundle.putString(
						"key",
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=12");
				bundle.putString(
						"url",
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=12");
				i.putExtras(bundle);

				activity.startActivity(i);
			}
		});

		btnBrand.btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("debug", "url");
				Intent i;
				Bundle bundle = new Bundle();
				i = new Intent(activity, InfoDetailActivity.class);
				i.putExtra(InfoDetailActivity.TITLE, "品牌概况");
				i.putExtra(
						InfoDetailActivity.URL,
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=13");
				i.setAction("push");
				bundle.putString("title", "品牌概况");
				bundle.putString(
						"key",
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=13");
				bundle.putString(
						"url",
						"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=13");
				i.putExtras(bundle);

				activity.startActivity(i);
			}
		});

		btnIndustry.btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(activity, CommunityActivity.class);
				startActivity(i);
			}
		});

		btnHistory.btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(activity, HistoricalActivity.class);
				startActivity(i);
			}
		});

		btnHornor.btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(activity, Honour_Activity.class);
				startActivity(i);
			}
		});

	}

	// // 公司主界面商各个按钮的的响应事件
	// @Override
	public void onClick(View v) {
		int id = v.getId();
		Intent i;
		Bundle bundle = new Bundle();
		switch (id) {

		case R.id.Btn_Announcement:
			i = new Intent(this, AnnouncementActivity.class);
			startActivity(i);

			break;
		case R.id.Btn_News:
			i = new Intent(this, NewsActivity.class);
			startActivity(i);
			break;

		case R.id.Btn_Sales:
			i = new Intent(this, SaleActivity.class);
			startActivity(i);
			break;
		case R.id.Btn_Histoty:
			i = new Intent(this, HistoricalActivity.class);
			startActivity(i);
			break;

		case R.id.Btn_Company_Honor:
			i = new Intent(this, Honour_Activity.class);
			startActivity(i);
			break;
		case R.id.Btn_Company_Community:
			i = new Intent(this, CommunityActivity.class);
			startActivity(i);
			break;
		case R.id.Btn_Company_Produce:
			// i = new Intent(this, ProduceActivity.class);
			// startActivity(i);
			Log.e("debug", "url");
			i = new Intent(activity, InfoDetailActivity.class);
			i.putExtra(InfoDetailActivity.TITLE, "产品概况");
			i.putExtra(
					InfoDetailActivity.URL,
					"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=10");
			i.setAction("push");
			bundle.putString("title", "产品概况" + "");
			bundle.putString(
					"key",
					"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=10");
			bundle.putString(
					"url",
					"http://cloud.yofoto.cn/index.php?gr=wapsite&mr=shwuczb&ar=showpageshtml&comid=10");
			i.putExtras(bundle);

			activity.startActivity(i);
			break;
		case R.id.Btn_Back:
			// i = new Intent(this, IndexActivity.class);
			// startActivity(i);
			finish();
			break;
		}
	}
}
