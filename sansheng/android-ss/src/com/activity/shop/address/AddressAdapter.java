package com.activity.shop.address;

import java.util.List;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.Address;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-8 上午10:53:10 declare:
 */
public class AddressAdapter extends BaseAdapter {
	private CommonActivity commonActivity;
	private LayoutInflater inflater;

	List<Address> addresses;

	public Address getHomeAddres() {
		if (addresses != null && addresses.size() >= 1) {
			return addresses.get(0);
		}
		return null;
	}

	public Address getRoomAddres() {
		if (addresses != null && addresses.size() >= 2) {
			return addresses.get(1);
		}
		return null;
	}

	public AddressAdapter(CommonActivity context) {
		commonActivity = context;
		inflater = context.getLayoutInflater();
	}

	@Override
	public int getCount() {
		if (addresses == null) {
			return 0;
		} else {
			return addresses.size();
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
			convertView = (View) inflater.inflate(R.layout.layout_address_item,
					null);

		}

		bindView(convertView, position);
		return convertView;

	}

	public void bindView(View view, int position) {
		TextView tvType = (TextView) view.findViewById(R.id.Tv_Type);
		TextView tvInfrom = (TextView) view.findViewById(R.id.Tv_Infrom);
		TextView tvAdds = (TextView) view.findViewById(R.id.Tv_Adds);
		TextView tvCall = (TextView) view.findViewById(R.id.Tv_Call);

		Address address = addresses.get(position);
		if (address.getType() == 0) {
			if (address.getName() != null) {
				tvCall.setText("" + address.getName());
			}
			if (address.getCall() != null) {
				tvCall.setText(tvCall.getText().toString() + address.getCall());
			}
			if (address.getAdds() != null) {
				tvAdds.setText(address.getAdds());
			}
			if (address.getInfrom() != null) {
				tvInfrom.setText(address.getInfrom());
			}
		} else {
			tvType.setText("工作室地址");
			// tvCall.setText(address.getCall());
			tvInfrom.setText("工作室标号" + address.getId());
			tvAdds.setText("店长:" + address.getName() + " " + address.getCall());
			tvCall.setText("地址:" + address.getAdds());
		}
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
