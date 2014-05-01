package com.activity.custome;

import java.util.List;

import com.activity.custome.CustomeAdapter.ViewHolder;
import com.lekoko.sansheng.R;
import com.sansheng.model.Contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-9 下午3:58:32 declare:
 */
public class SearchCustomeAdapter extends BaseAdapter {

	List<Contact> contacts;
	private Context context;

	public SearchCustomeAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		if (contacts == null) {
			return 0;
		} else {
			return contacts.size();
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

	static class ViewHolder {
		TextView tvNick;// 昵称
		TextView tvIndex;// 字母检索
		ImageView ivUserAvatar;// 头像
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		Contact contact = contacts.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.contact_item, null);
			viewHolder = new ViewHolder();
			//
			// viewHolder.ivAvatar = (ImageView) convertView
			// .findViewById(R.id.contactitem_avatar_iv);
			viewHolder.tvNick = (TextView) convertView
					.findViewById(R.id.contactitem_nick);
			viewHolder.tvIndex = (TextView) convertView
					.findViewById(R.id.contactitem_catalog);
			viewHolder.ivUserAvatar = (ImageView) convertView
					.findViewById(R.id.iv_UserAvatar);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		convertView.findViewById(R.id.layout_head_alpha).setVisibility(
				View.GONE);

		viewHolder.tvNick.setText(contact.getName());

		// viewHolder.ivUserAvatar.setImageURI(uri); //设置头像

		return convertView;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

}
