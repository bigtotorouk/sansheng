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
import com.sansheng.model.FormDetail.Product.shipment;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-4 下午7:03:43 declare:
 */
public class shopQingListView extends LinearLayout {

	private LayoutInflater layoutInflater;
	private List<Product> products;

	public shopQingListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// initData();
		// bindData();
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

	public void bindData(List<shipment> products) {
		setOrientation(VERTICAL);
		View view = (View) layoutInflater.inflate(
				R.layout.layout_payment_shop_qing_head, null);
		TextView tv = (TextView) view.findViewById(R.id.Tv_Product_Name);

		View item;
		addView(view);
		if (products == null) {
			tv.setText("暂无发货明细");
			return;
		}
		for (int i = 0; i < products.size(); i++) {
			if (i != (products.size() - 1)) {
				item = (View) layoutInflater.inflate(
						R.layout.layout_payment_qing_shop, null);
				LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT);
				addView(item, lp);
			} else {
				item = (View) layoutInflater.inflate(
						R.layout.layout_payment_shop_qing_bottom, null);
				addView(item);
			}
			shipment product = products.get(i);
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
				tvName.setText(product.getName() + "  " + "X"
						+ product.getMun());
			}
			if (product.getPrice() != null) {
				tvPrice.setText("￥" + product.getPrice());
			}
			if (product.getPv() != null) {
				tvPv.setText(product.getPv() + "pv");
			}

			tvNumber.setText(product.getNumber());
		}

	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
