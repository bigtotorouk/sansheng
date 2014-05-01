package com.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lekoko.sansheng.R;
import com.sansheng.model.Product;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-4 下午7:03:43 declare:
 */
public class PickListView extends LinearLayout {

	private LayoutInflater layoutInflater;
	private List<Product> products;

	public PickListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		initData();
		bindData(products);
	}

	public void initData() {
		products = new ArrayList<Product>();
		for (int i = 0; i < 15; i++) {
			Product product = new Product();
			product.setName("产品目录");
			product.setNumber("NMNSs1");
			product.setMun(12);
			products.add(product);
		}
	}

	public void bindData(List<Product> products) {
		setOrientation(VERTICAL);
		View view = (View) layoutInflater.inflate(
				R.layout.layout_payment_shop_head, null);
		View v;
		addView(view);
		for (int i = 0; i < products.size(); i++) {
			if (i != (products.size() - 1)) {
				v = (View) layoutInflater.inflate(R.layout.layout_payment_item,
						null);
				LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT);
				addView(v, lp);
			} else {
				v = (View) layoutInflater.inflate(
						R.layout.layout_payment_shop_bottom, null);
				addView(v);
			}

			Product product = products.get(i);
			ImageButton btnDelete = (ImageButton) v
					.findViewById(R.id.Btn_Delete);
			TextView tvmun = (TextView) v.findViewById(R.id.Tv_Count);
			TextView tvPrice = (TextView) v.findViewById(R.id.Tv_Price);
			TextView tvPv = (TextView) v.findViewById(R.id.Tv_Pv);
			TextView tvNum = (TextView) v.findViewById(R.id.Tv_Number);
			TextView tvName = (TextView) v.findViewById(R.id.Tv_name);
			ImageView img = (ImageView) v.findViewById(R.id.img_shop);

			tvmun.setText("X " + product.getMun());
			if (product.getPrice() != null) {
				tvPrice.setText("￥" + product.getPrice());
			}
			if (product.getPv() != null) {
				tvPv.setText(product.getPv() + "PV");
			}
			tvmun.setText("X " + product.getMun());
			if (product.getNumber() != null) {
				tvNum.setText(product.getNumber());
			}
			if (product.getName() != null) {
				tvName.setText(product.getName());
			}
			if (product.getSimg() != null) {
				// imageLoader.displayImage(product.getSimg(), img, options,
				// animateFirstListener);
			}
		}

	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
