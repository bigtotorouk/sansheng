package com.activity.custome;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.CommonActivity;
import com.http.BaseRequest;
import com.http.BaseRequestObj;
import com.http.ViewCommonResponse;
import com.http.task.CustomeAsynctask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Contact;
import com.umeng.socom.net.l;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.ListViewSearch;
import com.view.LoadingDilog;
import com.view.SearchView;
import com.view.SexDialog;

public class CustomeDetailActivity extends CommonActivity implements
		android.view.View.OnClickListener {

	private ListViewSearch lvCustome;
	private static final int MSG_NEW_DATA = 1;
	CustomeAdapter customeAdapter;
	private LoadingDilog lodingDilog;
	private SearchView searchView;
	private static final int resultCode = 0;
	private CommonActivity commonActivity;
	private Intent intent;

	private ListView lvSearch;
	private SearchCustomeAdapter searchAdapter;
	private View btnAddHome;
	private View Layout_Home;

	private EditText etName;
	private EditText etCardNum;
	private EditText etAddTime;
	private EditText etMobile;
	private EditText etHome;
	private EditText etMail;
	private EditText etAddress;
	private EditText etSex;
	private EditText etBirthday;
	private EditText etPosition;
	private EditText etMark;
	public static final String intentCustome = "custome";
	private Contact contact;
	// 0 add 1 edit;
	private int mode = 0;
	DatePickerDialog dlg;
	DatePickerDialog birthDlg;
	SexDialog sexDialog;
	public static final String intentMode = "Mode";
	private Button btnDelete;

	AlertDialog.Builder builder;
	private CommonActivity activity;
	AlertDialog dialog;

	private LinearLayout llyPhone;
	List<LinearLayout> layouts;
	HeadBar headBar;
	ImageView ivAvatar;
	File sdcardTempFile;
	File file;

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_select_custome_detail);
		activity = this;
		file = null;
		headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("添加客户");
		headBar.setRightType(BtnType.image);
		headBar.setRightImg(R.drawable.btn_ok_bg);
		headBar.setWidgetClickListener(this);
		btnAddHome = (View) findViewById(R.id.Btn_ADD_HOME);
		btnAddHome.setOnClickListener(this);
		Layout_Home = (View) findViewById(R.id.Layout_Home);

		etName = (EditText) findViewById(R.id.Et_Name);
		etCardNum = (EditText) findViewById(R.id.Et_Card_Number);
		etAddTime = (EditText) findViewById(R.id.Et_AddTime);
		etMobile = (EditText) findViewById(R.id.Et_Mobile);
		etHome = (EditText) findViewById(R.id.Et_Home);
		etMail = (EditText) findViewById(R.id.Et_Mail);
		etAddress = (EditText) findViewById(R.id.Et_AddTime);
		etSex = (EditText) findViewById(R.id.Et_Sex);
		etBirthday = (EditText) findViewById(R.id.Et_BirthDay);
		btnDelete = (Button) findViewById(R.id.Btn_DELETE);
		ivAvatar = (ImageView) findViewById(R.id.iv_Avatar);
		ivAvatar.setOnClickListener(this);
		llyPhone = (LinearLayout) findViewById(R.id.Layout_Phone);
		btnDelete.setOnClickListener(this);

		etBirthday.setOnClickListener(this);
		etSex.setOnClickListener(this);
		etAddTime.setOnClickListener(this);
		etPosition = (EditText) findViewById(R.id.Et_Position);
		etMark = (EditText) findViewById(R.id.Et_Mark);
		sdcardTempFile = new File("/mnt/sdcard/", "tmp_pic_"
				+ SystemClock.currentThreadTimeMillis() + ".jpg");
		layouts = new ArrayList<LinearLayout>();
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				contact = (Contact) bundle.get(intentCustome);
				if (bundle.containsKey(intentMode)) {
					mode = bundle.getInt(intentMode);
					if (mode == 1) {
						// getContactInfo();
						bindData();
						btnDelete.setVisibility(View.VISIBLE);
					} else {
						contact = new Contact();
						btnDelete.setVisibility(View.INVISIBLE);
					}
				}

			} else {
				contact = new Contact();
				contact.setSex("1");
			}
		}
		sexDialog = new SexDialog(this, contact, etSex);

		// if (contact == null) {
		// mode = 0;
		// } else {
		// mode = 1;
		// }
	}

	public void bindData() {
		headBar.setTitle("编辑客户");
		if (contact.getName() != null) {
			etName.setText(contact.getName());
		}
		if (contact.getCardNum() != null) {
			etCardNum.setText(contact.getCardNum());
		}
		if (contact.getAddtimer() != null) {
			etAddTime.setText(contact.getAddtimer());
		}
		if (contact.getMobilephone() != null) {
			etMobile.setText(contact.getMobilephone());
		}
		if (contact.getHomephone() != null) {
			bindHome();
		}
		if (contact.getEmail() != null) {
			etMail.setText(contact.getEmail());
		}
		if (contact.getAddress() != null) {
			etAddress.setText(contact.getAddress());
		}
		if (contact.getSex() != null) {
			if (contact.getSex().equals("1")) {
				etSex.setText("男");
			} else {
				etSex.setText("女");
			}
		}
		if (contact.getBirthday() != null) {
			etBirthday.setText(contact.getBirthday());
		}
		if (contact.getPosition() != null) {
			etPosition.setText(contact.getPosition());
		}
		if (contact.getMark() != null) {
			etMark.setText(contact.getMark());
		}
	}

	public void bindHome() {
		String[] homes = contact.getHomephone();
		LayoutInflater layoutInflater = getLayoutInflater();

		for (int i = 0; i < homes.length; i++) {
			final LinearLayout itemPhone = (LinearLayout) layoutInflater
					.inflate(R.layout.layout_phone, null);
			EditText et = (EditText) itemPhone.findViewById(R.id.Et_Home);
			et.setText(homes[i]);
			llyPhone.addView(itemPhone);
			layouts.add(itemPhone);
		}

	}

	public Contact getContact() {
		if (contact == null) {
			contact = new Contact();
			contact.setSex("1");
		}
		if (file != null) {
			contact.setAvatar(file);
		}
		contact.setName(etName.getText().toString());
		contact.setCardNum(etCardNum.getText().toString());
		contact.setAddtimer(etAddress.getText().toString());
		contact.setMobilephone(etMobile.getText().toString());
		// String content = etHome.getText().toString();
		// if (Layout_Home.getVisibility() == View.VISIBLE) {
		// String[] phone = content.replace("|", ",").split(",");
		// contact.setHomephone(phone);
		// } else {
		// String[] s = { "" };
		// contact.setHomephone(s);
		// }
		if (etSex.getText().toString().equals("男")) {
			contact.setSex("1");
		} else {
			contact.setSex("2");
		}
		contact.setEmail(etMail.getText().toString());
		contact.setAddress(etAddress.getText().toString());

		contact.setBirthday(etBirthday.getText().toString());
		contact.setPosition(etPosition.getText().toString());
		contact.setMark(etMark.getText().toString());
		return contact;

	}

	public boolean valideData() {
		contact = getContact();
		if (contact.getName().equals("")) {
			Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (contact.getCardNum().equals("")) {
			Toast.makeText(this, "卡号不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (contact.getAddtimer().equals("")) {
			Toast.makeText(this, "加入时间不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (contact.getMobilephone().equals("")) {
			Toast.makeText(this, "移动电话不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		// if (contact.getHomephone().equals("")) {
		// Toast.makeText(this, "家庭电话不能为空", Toast.LENGTH_SHORT).show();
		// return false;
		// }
		if (contact.getEmail().equals("")) {
			Toast.makeText(this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (contact.getAddress().equals("")) {
			Toast.makeText(this, "地址不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (contact.getSex().equals("")) {
			Toast.makeText(this, "性别不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (contact.getBirthday().equals("")) {
			Toast.makeText(this, "生日不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (contact.getPosition().equals("")) {
			Toast.makeText(this, "职业不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (contact.getMark().equals("")) {
			Toast.makeText(this, "备注不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	public void getContactInfo() {
		Log.e("debug", "addContact");
		BaseRequest baseRequest = createRequestWithUserId(CustomeAsynctask.CUSTOME_INFO);
		ProgressDialogUtil.show(this, "提示", "正在加载数据", true, true);
		baseRequest.add("clientid", contact.getId() + "");
		new CustomeAsynctask(this).execute(baseRequest);
	}

	public void addContact(Contact contact) {

		Log.e("debug", "addContact");
		BaseRequest baseRequest = createRequestWithUserId(CustomeAsynctask.CUSTOME_ADD);
		ProgressDialogUtil.show(this, "提示", "正在加载数据", true, true);
		baseRequest.add("name", contact.getName());
		baseRequest.add("cardno", contact.getCardNum());
		baseRequest.add("addtimer", contact.getAddtimer());
		baseRequest.add("mobilephone", contact.getMobilephone());

		baseRequest.add("homephone", getHomePhone());
		baseRequest.add("email", contact.getEmail());
		baseRequest.add("address", contact.getAddress());
		baseRequest.add("sex", contact.getSex());
		baseRequest.add("birthday", contact.getBirthday());
		baseRequest.add("work", contact.getWork());
		baseRequest.add("mark", contact.getMark());
//		baseRequest.add("headimg", contact.getAvatar());
		new CustomeAsynctask(this).execute(baseRequest);
	}

	public void updateContact() {
		Log.e("debug", "addContact");
		BaseRequest baseRequest = createRequestWithUserId(CustomeAsynctask.CUSTOME_EDIT);
		ProgressDialogUtil.show(this, "提示", "正在加载数据", true, true);
		baseRequest.add("clientid", "" + contact.getId());
		baseRequest.add("name", contact.getName());
		baseRequest.add("cardno", contact.getCardNum());
		baseRequest.add("addtimer", contact.getAddtimer());
		baseRequest.add("mobilephone", contact.getMobilephone());
		baseRequest.add("homephone", getHomePhone());
		baseRequest.add("email", contact.getEmail());
		baseRequest.add("address", contact.getAddress());
		baseRequest.add("sex", contact.getSex());
		baseRequest.add("birthday", contact.getBirthday());
		baseRequest.add("work", contact.getWork());
		baseRequest.add("mark", contact.getMark());
		new CustomeAsynctask(this).execute(baseRequest);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;

		case R.id.Btn_ADD_HOME:
			addPhone();
			break;
		case R.id.Btn_DELETE_HOME:
			break;
		case R.id.Img_Right:
			String home = getHomePhone();

			Log.e("debug", "" + home);

			if (mode == 0) {
				if (valideData() == true) {
					addContact(getContact());
				}
			} else {
				if (valideData() == true) {
					updateContact();
				}
			}
			break;
		case R.id.Et_AddTime:
			showAddTime();
			break;
		case R.id.Et_BirthDay:
			showBirthday();
			break;
		case R.id.Et_Sex:
			sexDialog.setContact(contact);
			sexDialog.show();
			break;
		case R.id.Btn_DELETE:
			delete();
			break;
		case R.id.iv_Avatar:
			// Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			// intent.addCategory(Intent.CATEGORY_OPENABLE);
			// intent.setType("image/*");
			// intent.putExtra("crop", "true");
			// intent.putExtra("aspectX", 1);
			// intent.putExtra("aspectY", 1);
			// intent.putExtra("outputX", 80);
			// intent.putExtra("outputY", 80);
			// intent.putExtra("return-data", true);
			// startActivityForResult(intent, 0);
			Intent intent = new Intent("android.intent.action.PICK");
			intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI,
					"image/*");
			intent.putExtra("output", Uri.fromFile(sdcardTempFile));
			intent.putExtra("crop", "true");
			intent.putExtra("aspectX", 1);// 裁剪框比例
			intent.putExtra("aspectY", 1);
			intent.putExtra("outputX", 80);// 输出图片大小
			intent.putExtra("outputY", 80);
			startActivityForResult(intent, 0);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Bitmap bmp = BitmapFactory.decodeFile(sdcardTempFile
					.getAbsolutePath());
			ivAvatar.setImageBitmap(bmp);
			file = sdcardTempFile;
		}
	}

	public void addPhone() {
		LayoutInflater layoutInflater = getLayoutInflater();
		final LinearLayout itemPhone = (LinearLayout) layoutInflater.inflate(
				R.layout.layout_phone, null);
		TextView tvNumber = (TextView) itemPhone.findViewById(R.id.tv_number);
		tvNumber.setText("电话 ");

		llyPhone.addView(itemPhone);
		layouts.add(itemPhone);
		ImageView btn = (ImageView) itemPhone
				.findViewById(R.id.Btn_DELETE_HOME);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				llyPhone.removeView(itemPhone);
				layouts.remove(itemPhone);
				// for (int i = 0; i < llyPhone.getChildCount() - 1; i++) {
				// TextView tvNum = (TextView) llyPhone.getChildAt(i)
				// .findViewById(R.id.tv_number);
				// tvNum.setText("电话 " + i);
				// }
			}
		});

	}

	public String getHomePhone() {
		String content = "";
		for (int i = 0; i < layouts.size(); i++) {
			View view = layouts.get(i);
			EditText et = (EditText) view.findViewById(R.id.Et_Home);
			// con
			if (i != (layouts.size() - 1)) {
				if (!et.getText().toString().equals("")) {
					content += et.getText().toString() + "|";
				}
			} else {
				content += et.getText().toString();
			}
		}
		return content;
	}

	public void delete() {
		builder = new Builder(this);
		builder.setMessage("确认删除该联系人?");

		builder.setTitle("提示");

		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				builder.create().show();
				if (contact != null) {
					BaseRequest baseRequest = createRequestWithUserId(CustomeAsynctask.CUSTOME_DELETE);
					ProgressDialogUtil.show(activity, "提示", "正在删除", true, true);
					baseRequest.add("clientid", contact.getId() + "");
					new CustomeAsynctask(activity).execute(baseRequest);
				}
			}
		});

		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});
		dialog = builder.create();
		dialog.show();
	}

	public void showAddTime() {
		final Calendar calendar = Calendar.getInstance(Locale.CHINA);
		Date myDate = null;
		myDate = new Date();
		// 创建一个Date实例
		calendar.setTime(myDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		// 获得日历中的 year month day
		dlg = new DatePickerDialog(this, 0, new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				Log.e("debyg", "day:" + dayOfMonth + "  month:" + monthOfYear
						+ 1 + "  year:" + year);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar day = Calendar.getInstance(Locale.CHINA);
				day.set(year, monthOfYear, dayOfMonth);
				String dayStr = sdf.format(day.getTime());
				etAddTime.setText(dayStr);
			}
		}, year, month, day);
		dlg.show();
		dlg.setButton(DatePickerDialog.BUTTON_NEGATIVE, "取消",
				new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dlg.dismiss();
					}
				});

	}

	public void showBirthday() {
		final Calendar calendar = Calendar.getInstance(Locale.CHINA);
		Date myDate = null;
		myDate = new Date();
		// 创建一个Date实例
		calendar.setTime(myDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		// 获得日历中的 year month day
		DatePickerDialog birthDlg = new DatePickerDialog(this, 0,
				new OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						Log.e("debyg", "day:" + dayOfMonth + "  month:"
								+ monthOfYear + 1 + "  year:" + year);
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						Calendar day = Calendar.getInstance(Locale.CHINA);
						day.set(year, monthOfYear, dayOfMonth);
						String dayStr = sdf.format(day.getTime());
						etBirthday.setText(dayStr);
					}
				}, year, month, day);

		birthDlg.show();
		birthDlg.setButton(DatePickerDialog.BUTTON_NEGATIVE, "取消",
				new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dlg.dismiss();
					}
				});

	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		switch (action) {
		case CustomeAsynctask.CUSTOME_ADD:
			ProgressDialogUtil.close();
			if (viewCommonResponse.getRetcode() == 0) {
				Intent intent = new Intent(this, CustomeIndexActivity.class);
				intent.putExtra(intentCustome, getContact());
				intent.putExtra(CustomeIndexActivity.INTENT_NEEDREFERSH, true);
				CustomeIndexActivity.need = true;
				this.setResult(RESULT_OK, intent);
				startActivity(intent);
				finish();
			} else {
				Toast.makeText(this, viewCommonResponse.getRetmsg(),
						Toast.LENGTH_SHORT).show();
			}

			break;

		case CustomeAsynctask.CUSTOME_INFO:
			ProgressDialogUtil.close();
			if (viewCommonResponse.getRetcode() == 0) {
				Contact contactInfo = (Contact) viewCommonResponse.getData();
				contact = contactInfo;
				bindData();
			} else {
				Toast.makeText(this, viewCommonResponse.getRetmsg(),
						Toast.LENGTH_SHORT).show();
			}

			break;

		case CustomeAsynctask.CUSTOME_EDIT:
			ProgressDialogUtil.close();
			if (viewCommonResponse.getRetcode() == 0) {
				CustomInfoActivity.needUpdate = true;
				finish();
			} else {
				Toast.makeText(this, viewCommonResponse.getRetmsg(),
						Toast.LENGTH_SHORT).show();
			}
			break;
		case CustomeAsynctask.CUSTOME_DELETE:
			ProgressDialogUtil.close();
			if (viewCommonResponse.getRetcode() == 0) {
				CustomInfoActivity.needUpdate = true;
				finish();
			} else {
				CustomInfoActivity.needUpdate = true;
				dialog.dismiss();
				Toast.makeText(this, viewCommonResponse.getRetmsg(),
						Toast.LENGTH_SHORT).show();
			}

			break;

		}
	}

}
