package com.activity.shop.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Product;
import com.sansheng.model.TransOrder;
import com.util.ProgressDialogUtil;
import com.view.HorizontalListView;
import com.view.IndicatorView;
import com.view.ShopEditDialog;
import com.view.ShopEditDialog.onDissmissListner;
import com.view.ShopTypeDialog;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 上午10:24:52
 * 
 *          declare: 商品基本信息界面
 */
public class ShopInfoFragment extends Fragment implements OnClickListener {
	private View view;
	private static ViewPager viewPager;
	private IndicatorView indicatorView;
	private ListView lvHealth;
	private LayoutInflater layoutInflater;
	private HorizontalListView gallery;
	public static CommonActivity commonActivity;
	private EditText etNum;
	private Button btnAdd;
	private static Product product;
	private ShopEditDialog shopEditDialog;
	ShopInfoFragment shopInfoFragment;
	GallgerAdapter adapter;
	private CommonActivity activity;
	RelativeLayout layout_detail, layout_evaluate;
	TransOrder order;
	ShopTypeDialog shopDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		activity = (CommonActivity) this.getActivity();
		shopInfoFragment = this;
		view = (View) inflater.inflate(R.layout.fragment_shopping_detail_info,
				null);
		layout_detail = (RelativeLayout) view.findViewById(R.id.layout_detail);
		layout_detail.setOnClickListener(this);
		layout_evaluate = (RelativeLayout) view
				.findViewById(R.id.layout_evaluate);
		layout_evaluate.setOnClickListener(this);
		layoutInflater = inflater;
		order = new TransOrder();
		order.setAddType("2");
		initWidget();
		return view;
	}

	public void update(Product product) {
		this.product = product;
		try {
			TextView tvName = (TextView) view.findViewById(R.id.Tv_Title);
			TextView tvPrice = (TextView) view.findViewById(R.id.Tv_Price);
			TextView tvPv = (TextView) view.findViewById(R.id.Tv_Pv);
			TextView tvNumber = (TextView) view.findViewById(R.id.Tv_Number);
			TextView tvFormat = (TextView) view.findViewById(R.id.Tv_Format);
			TextView tvSumary = (TextView) view.findViewById(R.id.WebView_Shop);
			
			// add by bigtotoro
			ImageView imgPro = (ImageView) view.findViewById(R.id.icon);
			TextView txtPro = (TextView) view.findViewById(R.id.info);

			// 更新产品图片
			if (product != null) {
				adapter.setUrls(product.getImgs());
				adapter.notifyDataSetChanged();
			}
			if (product.getSummary() != null) {
				tvSumary.setText(Html.fromHtml(product.getSummary()));
			}
			etNum.setText("1");
			if (product.getTitle() != null) {
				tvName.setText(product.getTitle());
			}
			if (product.getPrice() != null) {
				tvPrice.setText("￥" + product.getPrice());
			}
			if (product.getPv() != null) {
				tvPv.setText(product.getPv() + "pv");
			}
			if (product.getNumber() != null) {
				tvNumber.setText(product.getNumber());
			}
			if (product.getFormat() != null) {
				tvFormat.setText(product.getFormat());
			}
			if(product.getImgs()!=null && product.getImgs().length>0){
				activity.imageLoader.displayImage(product.getImgs()[0], imgPro, activity.options);
			}
			txtPro.setText(Html.fromHtml(product.getSummary()));
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addShopCar() {
		ProgressDialogUtil.show(commonActivity, "提示", "正在添加", true, true);
		BaseRequest request = commonActivity
				.createRequest(ShopService.PRODUCT_ADD);
		request.add("userid", commonActivity.getUserId());
		request.add("pid", product.getPid());
		request.add("number", product.getNumber());
		request.add("mun", etNum.getText().toString());
		request.add("ordertype", order.getAddType());
		Log.e("add  shopCar", request.toString());
		new ShopAsyncTask(commonActivity).execute(request);
	}

	public void setCarMun(int count) {

	}

	public void initWidget() {
		gallery = (HorizontalListView) view.findViewById(R.id.gallery);
		etNum = (EditText) view.findViewById(R.id.Et_Number);
		btnAdd = (Button) view.findViewById(R.id.Btn_Add);
		etNum.setOnClickListener(this);
		btnAdd.setOnClickListener(this);
		adapter = new GallgerAdapter(commonActivity);
		String[] urls = { "1", "1", "3", "1", "1", "3", "1", "1", "3" };
		adapter.setUrls(urls);
		gallery.setAdapter(adapter);
		shopDialog = new ShopTypeDialog(commonActivity, order);
		
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Add:
			shopDialog.show();
			shopDialog.btnFiuxiao.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					order.setAddType("4");
					shopDialog.dismiss();
					addShopCar();
				}
			});

			shopDialog.btnLingshou.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					order.setAddType("2");
					shopDialog.dismiss();
					addShopCar();
				}
			});

			break;

		case R.id.Et_Number:
			shopEditDialog = new ShopEditDialog(getActivity());
			shopEditDialog.show(product);

			shopEditDialog.setOnDissmissListner(new onDissmissListner() {

				@Override
				public void OnDissMiss(Product product) {
					shopInfoFragment.product = product;
					update(product);
				}

			});
			shopEditDialog.getBtnFinish().setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {

							if (etNum.getText().toString().equals("0")) {
								activity.showToast("数量必须大于0");
							}
							etNum.setText(""
									+ shopEditDialog.getProduct().getMun());
							shopEditDialog.dismiss();
							// addShopCar();
						}
					});
			break;
		case R.id.layout_detail: // 详细介绍
			String pid = product.getPid();
			Intent intent = new Intent(activity, ShopIntroduceActivity.class);
			intent.putExtra("pid", pid);
			startActivity(intent);
			break;
		case R.id.layout_evaluate: // 用户评价
			break;
		}
	}

}
