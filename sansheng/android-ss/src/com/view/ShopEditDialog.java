package com.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lekoko.sansheng.R;
import com.sansheng.model.Product;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-7 下午12:05:08 declare:
 */
public class ShopEditDialog extends Dialog implements
		android.view.View.OnClickListener {

	private Button btnFinish;
	private Button btnAdd;
	private Button btnDelete;
	private Product product;

	private EditText etNumber;

	private onDissmissListner onDissmissListner;
	private onDeleteListner onDeleteListner;

	public ShopEditDialog(Context context) {
		super(context, R.style.NOTitleDialog);
		setContentView(R.layout.layout_dialog_edit_shop_number);
		btnFinish = (Button) findViewById(R.id.Btn_Finish);
		btnAdd = (Button) findViewById(R.id.Btn_Add_Number);
		btnDelete = (Button) findViewById(R.id.Btn_Delete_Number);
		etNumber = (EditText) findViewById(R.id.Et_Number);
		etNumber.setText("1");
		btnDelete.setOnClickListener(this);
		btnFinish.setOnClickListener(this);
		btnAdd.setOnClickListener(this);
	}

	public void show(Product p) {
		this.product = p;
		if (p.getMun() == 0) {
			p.setMun(1);
			etNumber.setText("1");
		} else {
			etNumber.setText(p.getMun() + "");
		}
		show();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		int number = 1;
		switch (id) {
		// case R.id.Btn_Finish:
		// dismiss();
		// break;

		case R.id.Btn_Add_Number:
			number = product.getMun();
			number++;
			product.setMun(number);
			etNumber.setText(product.getMun() + "");
			break;

		case R.id.Btn_Delete_Number:
			number = product.getMun();
			if (number > 1) {
				number--;
				product.setMun(number);
				etNumber.setText(product.getMun() + "");
			}

			break;
		}

	}

	public Button getBtnFinish() {
		return btnFinish;
	}

	public void setBtnFinish(Button btnFinish) {
		this.btnFinish = btnFinish;
	}

	public onDissmissListner getOnDissmissListner() {
		return onDissmissListner;
	}

	public void setOnDissmissListner(onDissmissListner onDissmissListner) {
		this.onDissmissListner = onDissmissListner;
	}

	public interface onDissmissListner {
		public void OnDissMiss(Product product);

	}

	public interface onDeleteListner {
		public void OnDissMiss(Product product);

	}

	public onDeleteListner getOnDeleteListner() {
		return onDeleteListner;
	}

	public void setOnDeleteListner(onDeleteListner onDeleteListner) {
		this.onDeleteListner = onDeleteListner;
	}

	public Button getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(Button btnDelete) {
		this.btnDelete = btnDelete;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public EditText getEtNumber() {
		return etNumber;
	}

	public void setEtNumber(EditText etNumber) {
		this.etNumber = etNumber;
	}

}
