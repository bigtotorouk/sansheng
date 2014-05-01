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
public class RoomAdapter extends BaseAdapter {
	private CommonActivity commonActivity;
	private LayoutInflater inflater;

	public List<Address> addresses;

	public Address getHomeAddres() {
		if (addresses != null && addresses.size() >= 1) {
			return addresses.get(0);
		}
		return null;
	}

	public Address getRoomAddres() {
		if (addresses != null && addresses.size() >= 2) {
			return addresses.get(0);
		}
		return null;
	}

	public RoomAdapter(CommonActivity context) {
		commonActivity = context;
		inflater = context.getLayoutInflater();
	}

	@Override
	public int getCount() {
		return 1;
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
			convertView = (View) inflater.inflate(R.layout.room, null);

		}

		bindView(convertView, position);
		return convertView;

	}

	public void bindView(View view, int position) {
		Address address = addresses.get(position);

		TextView tvType = (TextView) view.findViewById(R.id.Tv_Type);
		TextView tvInfrom = (TextView) view.findViewById(R.id.Tv_Infrom);
		TextView tvAdds = (TextView) view.findViewById(R.id.Tv_Adds);
		TextView tvCall = (TextView) view.findViewById(R.id.Tv_Call);

		if (address.getName() != null) {
			tvType.setText("工作室地址");
			// tvCall.setText(address.getCall());
			tvInfrom.setText("工作室标号" + address.getId());
			if (address.getName() != null) {
				tvAdds.setText("店长:" + address.getName() + " "
						+ address.getCall());
			}
			if (address.getAdds() != null) {
				tvCall.setText("地址:" + address.getAdds());
			}
		}

	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
