package com.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hp.hpl.sparta.Text;
import com.lekoko.sansheng.R;
import com.sansheng.model.Product;
import com.sansheng.model.pr;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-4 下午7:03:43 declare:
 */
public class shopListView extends LinearLayout {

	private LayoutInflater layoutInflater;
	private List<Product> products;

	// 是否需要显示价格和pv
	private boolean neeedShowPrice;

	public shopListView(Context context, AttributeSet attrs) {
		super(context, attrs);

		neeedShowPrice = true;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// initData();
		// bindData();
	}

	public boolean isNeeedShowPrice() {
		return neeedShowPrice;
	}

	public void setNeeedShowPrice(boolean neeedShowPrice) {
		this.neeedShowPrice = neeedShowPrice;
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
		View item;
		addView(view);
		float sumPrice = 0;
		float sumPv = 0;

		for (int i = 0; i < products.size(); i++) {
			if (i != (products.size() - 1)) {
				item = (View) layoutInflater.inflate(
						R.layout.layout_payment_shop, null);
				LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT);
				addView(item, lp);
			} else {
				item = (View) layoutInflater.inflate(
						R.layout.layout_payment_shop_bottom, null);
				addView(item);
			}
			Product product = products.get(i);

			float price = Float.parseFloat(product.getPrice());
			float pv = Float.parseFloat(product.getPv());
			sumPrice += price * product.getMun();
			sumPv += pv * product.getMun();

			TextView tvName = (TextView) item
					.findViewById(R.id.Tv_Product_Name);
			TextView tvNumber = (TextView) item
					.findViewById(R.id.Tv_Product_Number);
			TextView tvPrice = (TextView) item.findViewById(R.id.Tv_list_price);
			TextView tvPv = (TextView) item.findViewById(R.id.Tv_list_pv);
			if (product.getName() != null) {
				tvName.setText(product.getName() + "  " + "X"
						+ product.getMun());
			} else {
				tvName.setText(product.getTitle() + "  " + "X"
						+ product.getMun());
			}


			if (neeedShowPrice == true) {
				if (product.getPrice() != null) {
					tvPrice.setText("￥" + product.getPrice());
				}
				if (product.getPv() != null) {
					tvPv.setText(product.getPv() + "pv");
				}
			}

			tvNumber.setText(product.getNumber());
		}
		TextView tvPrice = (TextView) view.findViewById(R.id.Tv_Total_Price);
		tvPrice.setText("￥" + sumPrice + "");
		TextView tvPv = (TextView) view.findViewById(R.id.Tv_Total_Pv);
		tvPv.setText(sumPv + "pv");

	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
