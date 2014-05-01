package com.activity.shop.detail;

import java.util.List;

import model.Evaluate;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.activity.CommonActivity;
import com.activity.custome.CustomeDetailActivity;
import com.activity.schedule.TabsAdapter;
import com.activity.shop.car.ShopCarActivity;
import com.activity.shop.search.SearchActivity;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.lekoko.sansheng.R;
import com.sansheng.model.Brand;
import com.sansheng.model.Product;
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.util.ProgressDialogUtil;
import com.view.BtnTab;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.IconButton;
import com.view.TabController;
import com.view.TabController.TabListenner;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-30 下午8:30:49 declare:
 */
public class ShopDetailActivity extends CommonActivity implements
		OnClickListener {
	private TabController tabController;
	public static ViewPager viewPager;
	private IconButton btnSearch;
	private IconButton btnShopCar;
	public static final String INTNET_PRODUCT = "brand";
	TabsAdapter tabsAdapter;
	private static final int MSG_LOAD = 1;
	private static final int MSG_SHOPCAR = 2;
	private CommonActivity activity;
	private UiHandler uiHandler;
	private DetailData detailData;
	private Brand brand;
	public final static String ACTION_NEW = "new";
	public static String ACTION_PUSH = "push";
	public static String META_TYPE = "id";
	public int push_id = 0;
	final UMSocialService mController = UMServiceFactory.getUMSocialService(
			"com.umeng.share", RequestType.SOCIAL);
	DetailData data;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub

		super.onCreate(arg0);
		activity = this;
		uiHandler = new UiHandler();
		setContentView(R.layout.activity_shop_detail);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("在线购物");
		headBar.setRightType(BtnType.image);
		headBar.setRightImg(R.drawable.btn_share);
		headBar.setWidgetClickListener(this);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabController = new TabController();
		BtnTab tabInfo = (BtnTab) findViewById(R.id.Btn_Info);
		BtnTab tabDetail = (BtnTab) findViewById(R.id.Btn_Detail);
		BtnTab tabEvaluation = (BtnTab) findViewById(R.id.Btn_Evaluation);
		btnSearch = (IconButton) findViewById(R.id.Btn_Search);
		btnShopCar = (IconButton) findViewById(R.id.Btn_Shopp_Car);
		btnShopCar.setOnClickListener(this);
		btnSearch.setOnClickListener(this);

		tabController.addTab(tabInfo);
		tabController.addTab(tabDetail);
		tabController.addTab(tabEvaluation);

		viewPager = (ViewPager) findViewById(R.id.ViewPaper_Content);

		tabsAdapter = new TabsAdapter(this, viewPager);
		tabsAdapter.addTab(actionBar.newTab(), ShopInfoFragment.class, null);
		tabsAdapter.addTab(actionBar.newTab(), ShopIntroduceFragment.class,
				null);
		tabsAdapter.addTab(actionBar.newTab(), ShopEvaulationFragment.class,
				null);

		tabController.setTabListenner(new TabListenner() {

			@Override
			public void onTabClick(final int i) {
				Log.e("debug", "index" + i);
				viewPager.setCurrentItem(i);
			}

		});

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int item) {
				viewPager.setCurrentItem(item);
				tabController.selected(item);
				if (detailData != null) {
					if (item == 0) {
						if (detailData.getProduct() != null) {
							ShopInfoFragment shopFragment = (ShopInfoFragment) tabsAdapter
									.instantiateItem(viewPager, 0);
							Product product = detailData.getProduct();
							shopFragment.update(product);
						}
					} else if (item == 1) {
						if (detailData.getContent() != null) {
							ShopIntroduceFragment shopInfoFragment = (ShopIntroduceFragment) tabsAdapter
									.instantiateItem(viewPager, 1);

							shopInfoFragment.update(detailData.getContent());
						}
					} else if (item == 2) {
						if (detailData.getEvaluates() != null) {
							ShopEvaulationFragment shopEvaulationFragment = (ShopEvaulationFragment) tabsAdapter
									.instantiateItem(viewPager, 2);
							shopEvaulationFragment.update(detailData
									.getEvaluates());
						}
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// µ TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		ShopInfoFragment.commonActivity = this;
		// brand = getBrand();
		// load(brand.getId());
		share();
	}

	public void share() {
		// 设置分享内容

		// 设置分享图片, 参数2为图片的地址
		mController.setShareMedia(new UMImage(this,
				"http://www.umeng.com/images/pic/banner_module_social.png"));
		// 设置分享图片，参数2为本地图片的资源引用
		// mController.setShareMedia(new UMImage(getActivity(),
		// R.drawable.icon));
		// 设置分享图片，参数2为本地图片的路径(绝对路径)
		// mController.setShareMedia(new UMImage(getActivity(),
		// BitmapFactory.decodeFile("/mnt/sdcard/icon.png")));
	}

	public void initData() {
		Intent i = getIntent();
		if (i != null) {
			if (i.getAction().equals(ACTION_PUSH)) {
				Bundle b = i.getExtras();
				if (b != null) {
					if (b.containsKey(META_TYPE)) {
						int type = b.getInt(META_TYPE);

					}
				}
			}
		}
	}

	private void load(final String id) {
		ProgressDialogUtil.show(activity, "提示", "正在加载数据", true, true);
		new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub

				super.run();
				ShopService shopService = new ShopService();
				detailData = new DetailData();

				BaseRequest baseRequest = createRequest(ShopService.PRODUCT_INFO);
				baseRequest.add("pid", "" + id);
				ViewCommonResponse resp = shopService
						.getProductSimpleInfo(baseRequest.getParams());
				Product product = (Product) resp.getData();

				resp = shopService.getProductDetail(baseRequest.getParams());
				String content = (String) resp.getData();
				resp = shopService.getProductComment(baseRequest.getParams());

				List<Evaluate> evaluates = (List<Evaluate>) resp.getData();

				detailData.setEvaluates(evaluates);
				detailData.setContent(content);
				detailData.setProduct(product);

				Message msg = new Message();
				msg.what = MSG_LOAD;
				msg.obj = detailData;
				uiHandler.sendMessage(msg);

			}
		}.start();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		initShopCarCount();
		Intent intent = getIntent();
		if (intent == null) {
			return;
		} 
		if (intent.getAction() == null) {
			Bundle b = intent.getExtras();
			if (b != null) {
				brand = (Brand) b.get(INTNET_PRODUCT);
				load(brand.getId());
			}
		} else {
			if (intent.getAction().equals(ACTION_NEW)) {
				Bundle bundle = intent.getExtras();
				if (bundle == null)
					return;
				saveOrderUserId(getAesUserName());
	 			String id = bundle.getString("id");
				load(id);
			} else if (intent.getAction().equals(ACTION_PUSH)) {
				Bundle b = intent.getExtras();
				if (b != null) {
					if (b.containsKey(META_TYPE)) {
						String type = b.getString(META_TYPE);
						load("" + type);
					}
				}
			}
		}
	}

	private void initShopCarCount() {
		btnShopCar = (IconButton) findViewById(R.id.Btn_Shopp_Car);
		btnShopCar.setCount(getShopCarCount());
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;
		case R.id.Btn_Search:
			Intent intent = new Intent(this, SearchActivity.class);
			startActivity(intent);
			break;
		case R.id.Btn_Shopp_Car:
			Intent intentShopCar = new Intent(this, ShopCarActivity.class);
			startActivity(intentShopCar);
			break;

		case R.id.Img_Right:
			mController.setShareContent(data.getProduct().getTitle());

			String[] imgs = data.getProduct().getImgs();
			if (imgs != null) {
				mController.setShareMedia(new UMImage(this, imgs[0]));
			}

			mController.openShare(activity, false);
			break;

		}
	}

	class UiHandler extends Handler {
		public void dispatchMessage(android.os.Message msg) {
			int what = msg.what;
			switch (what) {
			case MSG_LOAD:
				ProgressDialogUtil.close();
				data = (DetailData) msg.obj;
				ShopInfoFragment shopFragment = (ShopInfoFragment) tabsAdapter
						.instantiateItem(viewPager, 0);
				ShopIntroduceFragment shopInfoFragment = (ShopIntroduceFragment) tabsAdapter
						.instantiateItem(viewPager, 1);
				if (data.getProduct() != null) {
					shopFragment.update(data.getProduct());
				}

				if (data.getContent() != null) {
					shopInfoFragment.update(data.getContent());
				}

				ShopEvaulationFragment shopEvaulationFragment = (ShopEvaulationFragment) tabsAdapter
						.instantiateItem(viewPager, 2);
				if (data.getEvaluates() != null) {
					shopEvaulationFragment.update(data.getEvaluates());
				} else {
					Log.e("debug", "null");
				}

				break;

			default:
				break;
			}
		};
	}

	class DetailData {
		private Product product;
		private String content;
		private List<Evaluate> evaluates;

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public List<Evaluate> getEvaluates() {
			return evaluates;
		}

		public void setEvaluates(List<Evaluate> evaluates) {
			this.evaluates = evaluates;
		}

	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		switch (action) {
		case ShopService.PRODUCT_ADD:
			ProgressDialogUtil.close();
			int newscartmun = (Integer) viewCommonResponse.getData();
			Toast.makeText(activity, "加入购物车完成", Toast.LENGTH_SHORT).show();
			btnShopCar.setCount(newscartmun);
			saveShopCarCount(newscartmun);
			break;

		}
	}

}
