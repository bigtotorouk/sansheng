package com.activity.custome;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.CommonActivity;
import com.http.BaseRequest;
import com.http.ViewCommonResponse;
import com.http.task.CustomeAsynctask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Contact;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-9 下午4:47:09 declare:
 */
public class CustomInfoActivity extends CommonActivity implements
		OnClickListener {

	private TextView tvCardNum; 
	private TextView tvTime;
	private TextView tvPhone;
	private TextView tvMsg;
	private TextView tvName;
	private TextView tvBirthday;

	private View layoutCall;
	private View layoutMsg;
	private View layoutBirthday;

	private static String INTENT_CUSTOM = "CUSTOM";

	private Contact custome;

	public static boolean needUpdate = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub 
		super.onCreate(savedInstanceState);
		setContentView(com.lekoko.sansheng.R.layout.layout_custom_detail);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("客户信息");
		headBar.setRightType(BtnType.image);
		headBar.setRightImg(R.drawable.btn_edit_mode);
		headBar.setWidgetClickListener(this);

		tvCardNum = (TextView) findViewById(R.id.Tv_Card_Num);
		tvTime = (TextView) findViewById(R.id.Tv_Time);
		tvPhone = (TextView) findViewById(R.id.Tv_Phone);
		tvMsg = (TextView) findViewById(R.id.Tv_Msg);
		tvName = (TextView) findViewById(R.id.Tv_Custom_Name);
		tvBirthday = (TextView) findViewById(R.id.Tv_Birthday);

		layoutCall = (View) findViewById(R.id.Layotu_Call);
		layoutMsg = (View) findViewById(R.id.Layotu_Msg);
		layoutBirthday = (View) findViewById(R.id.Layotu_Send_Birthday);

		layoutCall.setOnClickListener(this);
		layoutMsg.setOnClickListener(this);
		layoutBirthday.setOnClickListener(this);
		getData();

		if (custome != null) {
			BaseRequest baseRequest = createRequestWithUserId(CustomeAsynctask.CUSTOME_INFO);
			ProgressDialogUtil.show(this, "提示", "正在加载数据", true, true);
			baseRequest.add("clientid", custome.getId() + "");
			new CustomeAsynctask(this).execute(baseRequest);
		}
		// if (custome != null) {
		// bindData(custome);
		// }
		// custome = new Contact();
		// custome.setMobilephone("13567124034");
		// custome.setName("hdretyri");
		// custome.setAddtimer("2013-02-01");
		// custome.setCardNum("123");
		// bindData(custome);

	}

	
	public void getData() {
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				custome = (Contact) bundle.get("contact");

			}
		}
	}

	public void bindData(Contact contact) {
		if (contact.getCardNum() != null) {
			tvCardNum.setText("卡号:" + custome.getCardNum());
		}
		if (contact.getAddtimer() != null) {
			tvTime.setText("加入时间:" + contact.getAddtimer());
		}
		if (contact.getMobilephone() != null) {
			tvPhone.setText(contact.getMobilephone());
		}
		if (contact.getMobilephone() != null) {
			tvMsg.setText(contact.getMobilephone());
		}
		if (contact.getName() != null) {
			tvName.setText(contact.getName());
		}
		if (contact.getBirthday() != null) {
			tvBirthday.setText(contact.getBirthday());
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Uri uri;
		Intent intent;
		switch (id) {
		case R.id.Img_Right:
			intent = new Intent(this, CustomeDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt(CustomeDetailActivity.intentMode, 1);
			bundle.putSerializable(CustomeDetailActivity.intentCustome, custome);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		case R.id.Btn_Back:
			finish();
			break;
		case R.id.Layotu_Call:
			uri = Uri.parse("tel:" + custome.getMobilephone());
			intent = new Intent(Intent.ACTION_DIAL, uri);
			startActivity(intent);
			break;

		case R.id.Layotu_Msg:

			uri = Uri.parse("smsto:" + custome.getMobilephone());
			intent = new Intent(Intent.ACTION_SENDTO, uri);
			intent.putExtra("sms_body", "");
			startActivity(intent);
			break;

		case R.id.Layotu_Send_Birthday:
			uri = Uri.parse("smsto:" + custome.getMobilephone());
			intent = new Intent(Intent.ACTION_SENDTO, uri);
			intent.putExtra("sms_body", "生日快乐");
			startActivity(intent);
			break;
		}

	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		switch (action) {

		case CustomeAsynctask.CUSTOME_INFO:
			ProgressDialogUtil.close();
			if (viewCommonResponse.getRetcode() == 0) {
				Contact contactInfo = (Contact) viewCommonResponse.getData();
				contactInfo.setId(custome.getId());
				custome = contactInfo;
				bindData(custome);
			} else {
				Toast.makeText(this, viewCommonResponse.getRetmsg(),
						Toast.LENGTH_SHORT).show();
			}

			break;

		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (needUpdate == true) {
			CustomeIndexActivity.need = true;
			finish();
			needUpdate = false;
			
		}

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		CustomeIndexActivity.need = false;
	}

}
