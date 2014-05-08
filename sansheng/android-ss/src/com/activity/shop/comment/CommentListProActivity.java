package com.activity.shop.comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.activity.company.news.news;
import com.google.gson.Gson;
import com.http.BaseNetService;
import com.http.HttpCommonResponse;
import com.http.HttpUtil;
import com.lekoko.sansheng.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 上午10:24:52 declare:
 */
public class CommentListProActivity extends Activity implements OnClickListener {
	private RatingBar rating1, rating2;
	private ListView listView;
	private CommentProAdapter mAdapter;
	
	private String pid = "", pageno = "" ;
	private String ordercode = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_shop_comment);
		super.onCreate(savedInstanceState);
		rating1 = (RatingBar)findViewById(R.id.ratingBar_1);
		rating2 = (RatingBar)findViewById(R.id.ratingBar_2);
		listView = (ListView)findViewById(R.id.list);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("用户评价");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);
		mAdapter = new CommentProAdapter(this);
		listView.setAdapter(mAdapter);
		pid = getIntent().getStringExtra("pid");
		loadData();
	}
	private void loadData(){
		AsyncHttpClient client = new AsyncHttpClient();
		String url = BaseNetService.PRODUCT_COMMENT+"&pid="+pid+"&pageno="+"0";
		client.get(url, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String content) {
				CommentResponse cResponse = new Gson().fromJson(content, CommentResponse.class);
				if(cResponse!=null){
					List<CommentPro> comments = cResponse.getList();
					rating1.setRating(cResponse.getNlogisticsok());
					rating2.setRating(cResponse.getNqualityint());
					mAdapter.update(comments);
				}
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.Btn_Back){
			this.finish();
		}
	}
	
	public static class CommentResponse{
		private int nlogisticsok;
		private int nqualityint;
		
		public int getNlogisticsok() {
			return nlogisticsok;
		}

		public void setNlogisticsok(int nlogisticsok) {
			this.nlogisticsok = nlogisticsok;
		}

		public int getNqualityint() {
			return nqualityint;
		}

		public void setNqualityint(int nqualityint) {
			this.nqualityint = nqualityint;
		}

		private List<CommentPro> list;

		public List<CommentPro> getList() {
			return list;
		}

		public void setList(List<CommentPro> list) {
			this.list = list;
		}
	}
	
	public static class CommentPro {
		private String number;
		private String userlevel;
		private String ordercode;
		private String time;// "2014-5-3 19:02:34",
		private String content;
		private String logisticsok;
		private String qualityint;
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public String getUserlevel() {
			return userlevel;
		}
		public void setUserlevel(String userlevel) {
			this.userlevel = userlevel;
		}
		public String getOrdercode() {
			return ordercode;
		}
		public void setOrdercode(String ordercode) {
			this.ordercode = ordercode;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getLogisticsok() {
			return logisticsok;
		}
		public void setLogisticsok(String logisticsok) {
			this.logisticsok = logisticsok;
		}
		public String getQualityint() {
			return qualityint;
		}
		public void setQualityint(String qualityint) {
			this.qualityint = qualityint;
		}
	}
	

}
