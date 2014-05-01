package com.activity.shop.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.CommonActivity;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.lekoko.sansheng.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.sansheng.model.Product;
import com.umeng.socialize.bean.ac;
import com.util.AnimateFirstDisplayListener;
import com.util.ProgressDialogUtil;
import com.view.ShopEditDialog;
import com.view.ShopEditDialog.onDeleteListner;
import com.view.ShopEditDialog.onDissmissListner;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-4 下午5:23:48 declare:
 */
public class ShopCarAdapter extends BaseAdapter {

	private ShopCarActivity activity;
	private List<Product> products;
	private int mode = 0;
	String strSelected;

	public String getStrSelected() {
		return strSelected;
	}

	public void setStrSelected(String strSelected) {
		this.strSelected = strSelected;
	}

	private LayoutInflater layoutInflater;
	public ShopEditDialog shopEditDialog;

	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private DisplayImageOptions options;
	ImageLoader imageLoader;
	ShopService shopService;
	Uihandler uihandler;
	int currentMode;

	public int getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(int currentMode) {
		this.currentMode = currentMode;
	}

	public ShopCarAdapter(ShopCarActivity context) {
		this.activity = context;
		imageLoader = activity.imageLoader;
		options = activity.options;
		shopService = new ShopService();
		uihandler = new Uihandler();
		shopEditDialog = new ShopEditDialog(context);
		layoutInflater = (LayoutInflater) context.getLayoutInflater();
		products = new ArrayList<Product>();
	}

	@Override
	public int getCount() {
		if (products == null) {
			return 0;
		} else {
			return products.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_shop_car_item, null);
		}
		bindView(convertView, position);
		return convertView;
	}

	public void bindView(View v, final int position) {
		final Product product = products.get(position);
		EditText etmun = (EditText) v.findViewById(R.id.Et_Number);
		ImageButton btnDelete = (ImageButton) v.findViewById(R.id.Btn_Delete);
		TextView tvmun = (TextView) v.findViewById(R.id.Tv_Count);
		TextView tvPrice = (TextView) v.findViewById(R.id.Tv_Price);
		TextView tvPv = (TextView) v.findViewById(R.id.Tv_Pv);
		TextView tvNum = (TextView) v.findViewById(R.id.Tv_Number);
		TextView tvName = (TextView) v.findViewById(R.id.Tv_name);
		ImageView img = (ImageView) v.findViewById(R.id.img_shop);
		CheckBox chk = (CheckBox) v.findViewById(R.id.cb);
		chk.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (getStrSelected().equals(""))
						setStrSelected(products.get(position).getId());
					else
						setStrSelected("," + products.get(position).getId());
				} else {
					if (getStrSelected()
							.indexOf(products.get(position).getId()) != -1) {
						if (getStrSelected().indexOf(
								products.get(position).getId()) == 0)
							getStrSelected().replace(
									products.get(position).getId(), "");
						else
							getStrSelected().replace(
									"," + products.get(position).getId(), "");
					}
				}
			}
		});
		if (product.getPrice() != null) {
			tvPrice.setText("￥" + product.getPrice());
		}
		if (product.getPv() != null) {
			tvPv.setText(product.getPv() + "PV");
		}
		tvmun.setText("X " + product.getMun());
		etmun.setText("" + product.getMun());
		if (product.getNumber() != null) {
			tvNum.setText(product.getNumber());
		}
		if (product.getTitle() != null) {
			tvName.setText(product.getTitle());
		}
		if (product.getSimg() != null) {
			imageLoader.displayImage(product.getSimg(), img, options,
					animateFirstListener);
		}
		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(activity);
				builder.setMessage("确认删除该商品？");
				builder.setTitle("提示");
				builder.setPositiveButton("确认",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								delete(product);
							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				builder.show();

			}

		});

		etmun.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				shopEditDialog.show(product);
				shopEditDialog.getBtnFinish().setOnClickListener(
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								edit(product);

								shopEditDialog.dismiss();
							}

						});
				shopEditDialog.setOnDissmissListner(new onDissmissListner() {

					@Override
					public void OnDissMiss(Product product) {
						String count = product.getNumber();
						Log.e("debug", "position" + position);
						products.get(position).setNumber(count);
						notifyDataSetChanged();
					}

				});

				shopEditDialog.setOnDeleteListner(new onDeleteListner() {

					@Override
					public void OnDissMiss(Product product) {

						products.remove(product);
						notifyDataSetChanged();
					}
				});

			}
		});

		Product p = products.get(position);
		if (mode == 1) {
			etmun.setVisibility(View.VISIBLE);
			btnDelete.setVisibility(View.VISIBLE);
			tvmun.setVisibility(View.INVISIBLE);
			chk.setVisibility(View.VISIBLE);
		} else {
			etmun.setVisibility(View.INVISIBLE);
			btnDelete.setVisibility(View.INVISIBLE);
			tvmun.setVisibility(View.VISIBLE);
			chk.setVisibility(View.GONE);
		}

	}

	private void delete(final Product product) {
		ProgressDialogUtil.show(activity, "提示", "正在删除", true, true);
		new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				Map<String, String> p = new HashMap<String, String>();
				p.put("userid", activity.getOrderUserId());
				p.put("cartid", product.getCartid());
				p.put("clear", "0");
				p.put("ordertype", getCurrentMode() + "");
				ViewCommonResponse resp = shopService.deleteShop(p);
				if (resp.getMsgCode() == 0) {
					Message msg = new Message();
					msg.what = 0;
					msg.obj = product;
					uihandler.sendMessage(msg);
				} else {
					Message msg = new Message();
					msg.what = 3;
					msg.obj = "删除失败 ";
					uihandler.sendMessage(msg);
					ProgressDialogUtil.close();
				}
			}
		}.start();
	}

	private void edit(final Product product) {
		ProgressDialogUtil.show(activity, "提示", "正在提交", true, true);
		new Thread() {
			public void run() {
				Map<String, String> p = new HashMap<String, String>();
				p.put("userid", activity.getOrderUserId());
				p.put("cartid", product.getCartid());
				p.put("edmun", "" + shopEditDialog.getEtNumber().getText());
				ViewCommonResponse resp = shopService.editShop(p);
				if (resp.getMsgCode() == 0) {
					Message msg = new Message();
					msg.what = 2;
					msg.obj = product;
					uihandler.sendMessage(msg);
				} else {
					Message msg = new Message();
					msg.what = 3;
					msg.obj = "编辑失败 ";
					uihandler.sendMessage(msg);
					ProgressDialogUtil.close();
				}

			};
		}.start();
	}

	public void bindView(View view, Product product) {

	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
		notifyDataSetChanged();
	}

	public void setEidtMode() {
		mode = 1;
		notifyDataSetChanged();

	}

	public void setNomlMode() {
		mode = 0;
		notifyDataSetChanged();
	}

	class Uihandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case 0:
				Product p = (Product) msg.obj;
				products.remove(p);
				notifyDataSetChanged();
				ProgressDialogUtil.close();
				activity.sum(products);
				break;
			case 2:
				ProgressDialogUtil.close();
				Product ep = (Product) msg.obj;
				for (int i = 0; i < products.size(); i++) {
					Product product = products.get(i);
					if (ep.getCartid() == product.getCartid()) {
						product.setMun(ep.getMun());
						notifyDataSetChanged();
					}
				}
				activity.sum(products);
				break;
			case 3:
				String content = (String) msg.obj;
				Toast.makeText(activity, "" + content, Toast.LENGTH_SHORT)
						.show();
				break;
			}

		}
	}

	public Product findById(String id) {
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			if (product.getId().equals(id)) {
				return product;
			}
		}
		return null;
	}

}
