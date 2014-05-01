package com.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lekoko.sansheng.R;
import com.sansheng.model.Contact;
import com.sansheng.model.Product;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-7 下午12:05:08 declare:
 */
public class SexDialog extends Dialog implements
		android.view.View.OnClickListener {

	private Product product;

	private EditText etSex;

	private ListView lvCity;

	private List<City> cites;
	private LayoutInflater layoutInflater;
	private CityAdapter cityAdapter;
	public EditText etRegion;
	private City curCity;
	private City preCity;

	private View btnDiss;

	private Contact contact;
	private Dialog dialog;

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public SexDialog(Context context, Contact c, EditText et) {

		super(context, R.style.NOTitleDialog);
		this.contact = c;
		etSex = et;
		dialog = this;
		setContentView(R.layout.layout_dialog_sex);
		initData();
		layoutInflater = getLayoutInflater();
		lvCity = (ListView) findViewById(R.id.Lv_Sex);
		btnDiss = (View) findViewById(R.id.Layout_Diss);
		cityAdapter = new CityAdapter();
		lvCity.setAdapter(cityAdapter);
		curCity = cites.get(0);
		lvCity.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					contact.setSex("1");
					etSex.setText("男");
					dialog.dismiss();
				} else {
					contact.setSex("2");
					etSex.setText("女");
					dialog.dismiss();
				}

			}
		});

		btnDiss.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}

	public void initData() {
		cites = new ArrayList<SexDialog.City>();
		City city = new City();
		city.setName("男");
		cites.add(city);

		city = new City();
		city.setName("女");
		cites.add(city);

	}

	public void show(Product p) {
		this.product = p;
		show();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		int number;
		switch (id) {
		// case R.id.Btn_Finish:
		// dismiss();
		// break;

		case R.id.Btn_Add_Number:
			// number = product.getMun();
			// number++;
			// product.setMun(number);
			// etNumber.setText(product.getMun() + "");
			// break;
			//
			// case R.id.Btn_Delete_Number:
			// number = product.getMun();
			// if (number > 0) {
			// number--;
			// product.setMun(number);
			// etNumber.setText(product.getMun() + "");
			// }

			break;
		}

	}

	class City {
		private String name;
		private boolean selected;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}

	}

	public class CityAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (cites == null) {
				return 0;
			} else {
				return cites.size();
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
				convertView = convertView.inflate(getContext(),
						R.layout.layout_city_item, null);
			}

			TextView tvCity = (TextView) convertView.findViewById(R.id.Tv_City);
			ImageView img = (ImageView) convertView
					.findViewById(R.id.img_radio);

			City city = cites.get(position);
			tvCity.setText(city.getName());

			if (city.isSelected() == true) {
				img.setBackgroundResource(R.drawable.radio_selected);
			} else {
				img.setBackgroundResource(R.drawable.radio_unselected);
			}

			return convertView;
		}

	}

}
