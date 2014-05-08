package com.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.lekoko.sansheng.R;
import com.sansheng.model.Product;
import com.sansheng.model.TransOrder;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-7 下午12:05:08 declare:
 */
public class ShopTypeDialog extends Dialog {

	private Product product;

	private ListView lvCity;

	private View btnDiss;

	private TransOrder order;

	private Dialog dialog;

	public Button btnLingshou;
	public Button btnFiuxiao;

	public TransOrder getOrder() {
		return order;
	}

	public void setOrder(TransOrder order) {
		this.order = order;
	}

	public ListView getLvCity() {
		return lvCity;
	}

	public void setLvCity(ListView lvCity) {
		this.lvCity = lvCity;
	}

	public ShopTypeDialog(Context context, TransOrder c) {

		super(context, R.style.Translucent_NoTitle);
		this.order = c;
		dialog = this;
		setContentView(R.layout.layout_shop_type);
		LayoutInflater layoutInflater = getLayoutInflater();
		btnLingshou = (Button) findViewById(R.id.Btn_Lingshou);
		btnFiuxiao = (Button) findViewById(R.id.Btn_Fu);
		findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(dialog.isShowing()){
					dialog.dismiss();
				}
				
			}
		});
	}

}
