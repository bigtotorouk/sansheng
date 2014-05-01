package com.activity.shop.address;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Address;
import com.util.ProgressDialogUtil;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-8 上午10:53:10 declare:
 */
public class AddAddressAdapter extends BaseAdapter {
	private CommonActivity commonActivity;
	private LayoutInflater inflater;
	public Address curAddress;
	List<Address> addresses;
	public int p;

	public AddAddressAdapter(CommonActivity context) {
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
			convertView = (View) inflater.inflate(R.layout.layout_address_edit,
					null);

		}
		bindView(convertView, position);
		return convertView;
	}

	public void bindView(View view, final int position) {
		final Address address = addresses.get(position);
		TextView tvType = (TextView) view.findViewById(R.id.Tv_Type);
		TextView tvInfrom = (TextView) view.findViewById(R.id.Tv_Row1);
		TextView tvAdds = (TextView) view.findViewById(R.id.Tv_Row2);
		TextView tvCall = (TextView) view.findViewById(R.id.Tv_Row3);

		Button btnEdit = (Button) view.findViewById(R.id.Btn_Edit);
		Button btnDelete = (Button) view.findViewById(R.id.Btn_Delete);
		ImageView imgSelect = (ImageView) view.findViewById(R.id.Img_Selected);
		View defview = (View) view.findViewById(R.id.Layout_Default);
		if (address.getDefaults().equals("1")) {
			imgSelect.setBackgroundResource(R.drawable.img_address_select);
		} else {
			imgSelect.setBackgroundResource(R.drawable.img_address_unselect);

		}
		btnEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Address address = addresses.get(position);
				Bundle bundle = new Bundle();
				bundle.putSerializable("address", address);
				Intent intent = new Intent(commonActivity,
						EditAddressActivity.class);
				intent.putExtras(bundle);
				commonActivity.startActivity(intent);
			}
		});

		defview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(commonActivity);
				builder.setMessage("设置为默认地址？");
				builder.setTitle("提示");
				p = position;
				builder.setPositiveButton("确认",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								curAddress = address;
								ProgressDialogUtil.show(commonActivity, "提示",
										"正在设置", true, true);
								BaseRequest request = commonActivity
										.createRequestWithUserId(ShopService.ADDRESS_DEFAULT);
								request.add("addsid", "" + address.getId());
								new ShopAsyncTask(commonActivity)
										.execute(request);

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

		btnDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(commonActivity);
				builder.setMessage("确认删除该地址？");
				builder.setTitle("提示");
				builder.setPositiveButton("确认",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								curAddress = address;
								ProgressDialogUtil.show(commonActivity, "提示",
										"正在删除", true, true);
								BaseRequest request = commonActivity
										.createRequestWithUserId(ShopService.ADDRESS_DELETE);
								request.add("pageno", "0");
								request.add("addsid", "" + address.getId());
								new ShopAsyncTask(commonActivity)
										.execute(request);

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

	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void setdefault() {
		for (int i = 0; i < addresses.size(); i++) {
			Address address = addresses.get(i);
			if (i == p) {
				address.setDefaults("1");
			} else {
				address.setDefaults("0");
			}
		}
		notifyDataSetChanged();
	}

}
